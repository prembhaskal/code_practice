package common.time_tracker_app.parser;

import common.time_tracker_app.FileAggregator;
import common.time_tracker_app.FindFocussedWindow;
import common.time_tracker_app.WindowInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class FileDataParser {

	public static final String SLEEP = "SLEEP";
	private Map<String, Integer> mainWindowVsFrequency = new TreeMap<>();

	private final int BATCH_SIZE = 10_000;

	private long interval;
	private final int TOP = 10;

	public static void main(String[] args) {
		new FileDataParser(5).parseAndDisplaySummaryForUserData(new File("D:/output/window_info_data_15-06-2015.txt"));
	}

	public FileDataParser(long interval) {
		this.interval = interval;
	}

	public void parseAndDisplaySummaryForUserData(File windowInfoFile) {

		createWindowVsFrequency(windowInfoFile);
		List<WindowVsFrequency> windowVsFrequencies = getWindowsInDescendingOrder();

		printTopWindowsAlongWithTime(windowVsFrequencies);
	}

	private void printTopWindowsAlongWithTime(List<WindowVsFrequency> windowVsFrequencies) {

		int i = 0;
		for (WindowVsFrequency windowVsFrequency : windowVsFrequencies) {
			String windowName = windowVsFrequency.windowName;
			Integer count = windowVsFrequency.frequency;
			long netInterval = count * interval;

			System.out.print(windowName);
			System.out.print("\t\t\t");
			System.out.println(netInterval);
			i++;

			if (i >= TOP)
				break;
		}
	}

	private void createWindowVsFrequency(File windowInfoFile) {
		try (BufferedReader bufferedReader = Files.newBufferedReader(windowInfoFile.toPath(), Charset.defaultCharset())) {

			String line;
			List<WindowInfo> windowInfos = new ArrayList<>();
			while ((line = bufferedReader.readLine())  != null) {
				WindowInfo windowInfo = parseWindowInfo(line);
				windowInfos.add(windowInfo);

				if (windowInfos.size() == BATCH_SIZE) {
					updateWindowVsInterval(windowInfos);
					windowInfos.clear();
				}
			}

			updateWindowVsInterval(windowInfos);
			windowInfos.clear();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateWindowVsInterval(List<WindowInfo> windowInfos) {
		for (WindowInfo windowInfo : windowInfos) {
			String mainWindowName = getMainWindowName(windowInfo.getRawWindowName());
			Integer frequency = mainWindowVsFrequency.get(mainWindowName);
			if (frequency == null) {
				frequency = 0;
			}
			frequency = frequency + 1;
			mainWindowVsFrequency.put(mainWindowName, frequency);
		}
	}

	private WindowInfo parseWindowInfo(String dataLine) {
		String[] splitLine = dataLine.split(FileAggregator.DATA_SEPARATOR);
		String windowName = splitLine.length == 0 ? "" : splitLine[0];
		String processName = splitLine.length < 3 ? "" : splitLine[2];

		return new WindowInfo(windowName, processName);
	}

	private String getMainWindowName(String rawWindowName) {
		if (rawWindowName == null || rawWindowName.isEmpty())
			return SLEEP;

		int index = rawWindowName.lastIndexOf("-");
		if (index == -1 || index == rawWindowName.length())
			return rawWindowName;

		String mainWindowName = rawWindowName.substring(index + 1);
		mainWindowName = mainWindowName.trim();

		return mainWindowName;
	}

	private List<WindowVsFrequency> getWindowsInDescendingOrder() {
		List<WindowVsFrequency> windowVsFrequencies = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : mainWindowVsFrequency.entrySet()) {
			windowVsFrequencies.add(new WindowVsFrequency(entry.getKey(), entry.getValue()));
		}

		Collections.sort(windowVsFrequencies, new Comparator<WindowVsFrequency>() {
			@Override
			public int compare(WindowVsFrequency o1, WindowVsFrequency o2) {
				return -1 * o1.frequency.compareTo(o2.frequency);
			}
		});

		return windowVsFrequencies;
	}

	private class WindowVsFrequency {
		public String windowName;
		public Integer frequency;

		public WindowVsFrequency(String windowName, Integer frequency) {
			this.windowName = windowName;
			this.frequency = frequency;
		}

	}
}
