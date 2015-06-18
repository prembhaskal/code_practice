package common.time_tracker_app.parser;

import common.time_tracker_app.FileAggregator;
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
	private Map<String, Integer> mainWindowVsFrequency = new HashMap<>();
	private Map<String, Integer> processNameVsFrequency = new HashMap<>();

	private final int BATCH_SIZE = 10_000;

	private long interval;
	private TimeUnit timeUnit = TimeUnit.SECONDS;
	private final int TOP = 15;

	public static void main(String[] args) {
		new FileDataParser(5).parseAndDisplaySummaryForUserData(new File("D:/output/window_info_data_18-06-2015.txt"));
	}

	public FileDataParser(long interval) {
		this.interval = interval;
	}

	public void parseAndDisplaySummaryForUserData(File windowInfoFile) {

		createFrequencyMaps(windowInfoFile);
		List<ItemVsFrequency> windowVsFrequencies = getItemsInDescendingOrder(mainWindowVsFrequency);
		printTopWindowsAlongWithTime(windowVsFrequencies);

		List<ItemVsFrequency> processVsFrequencies = getItemsInDescendingOrder(processNameVsFrequency);
		printTopProcessAlongWithTime(processVsFrequencies);
	}

	private void printTopWindowsAlongWithTime(List<ItemVsFrequency> windowVsFrequencies) {

		int i = 0;
		for (ItemVsFrequency itemVsFrequency : windowVsFrequencies) {
			String windowName = itemVsFrequency.itemName;
			Integer count = itemVsFrequency.frequency;
			long netInterval = timeUnit.toMinutes(count * interval);

			System.out.print(windowName);
			System.out.print("\t\t\t");
			System.out.println(netInterval);

			if (++i >= TOP)
				break;
		}


	}

	private void printTopProcessAlongWithTime(List<ItemVsFrequency> windowVsFrequencies) {
		System.out.println("\n------------ PROCESS INFORMATION-----------\n");
		System.out.printf("%s \t\t\t\t\t\t %s\n", "Process Name", "duration (in minutes)");
		int i = 0;
		long totalIntervalMinutes = 0;
		for ( ItemVsFrequency itemVsFrequency : windowVsFrequencies) {
			String processName = itemVsFrequency.itemName;
			Integer count = itemVsFrequency.frequency;
			long intervalMinutes = timeUnit.toMinutes(count * interval);

			totalIntervalMinutes += intervalMinutes;

			System.out.format("%s %10d", processName, intervalMinutes);
			System.out.println("");

			if (++i >= TOP)
				break;
		}

		System.out.printf("total interval spent on top %d applications is %d minutes \n", TOP, totalIntervalMinutes);
	}

	private void createFrequencyMaps(File windowInfoFile) {
		try (BufferedReader bufferedReader = Files.newBufferedReader(windowInfoFile.toPath(), Charset.defaultCharset())) {

			String line;
			List<WindowInfo> windowInfos = new ArrayList<>();
			while ((line = bufferedReader.readLine())  != null) {
				WindowInfo windowInfo = parseWindowInfo(line);
				windowInfos.add(windowInfo);

				if (windowInfos.size() == BATCH_SIZE) {
					updateDataMaps(windowInfos);
					windowInfos.clear();
				}
			}

			updateDataMaps(windowInfos);
			windowInfos.clear();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateDataMaps(List<WindowInfo> windowInfos) {
		updateWindowVsInterval(windowInfos);
		updateProcessNameVsInterval(windowInfos);
	}

	private void updateWindowVsInterval(List<WindowInfo> windowInfos) {
		for (WindowInfo windowInfo : windowInfos) {
			String mainWindowName = getMainWindowName(windowInfo.getRawWindowName());
			updateFrequencyInMap(mainWindowVsFrequency, mainWindowName);
		}
	}

	private void updateProcessNameVsInterval(List<WindowInfo> windowInfos) {
		for (WindowInfo windowInfo : windowInfos) {
			String processName = windowInfo.getProcessName().isEmpty() ? SLEEP : windowInfo.getProcessName();
			updateFrequencyInMap(processNameVsFrequency, processName);
		}
	}

	private void updateFrequencyInMap(Map<String, Integer> map, String key) {
		Integer frequency = map.get(key);
		if (frequency == null) frequency = 0;
		frequency++;
		map.put(key, frequency);
	}

	private WindowInfo parseWindowInfo(String dataLine) {
		String[] splitLine = dataLine.split(FileAggregator.DATA_SEPARATOR);
		String windowName = splitLine.length == 0 ? "" : splitLine[0];
		String processName = splitLine.length < 2 ? "" : splitLine[1];

		return new WindowInfo(windowName, processName);
	}

	private String getMainWindowName(String rawWindowName) {
		if (rawWindowName.isEmpty())
			return SLEEP;

		int index = rawWindowName.lastIndexOf("-");
		if (index == -1 || index == rawWindowName.length())
			return rawWindowName;

		String mainWindowName = rawWindowName.substring(index + 1);
		mainWindowName = mainWindowName.trim();

		return mainWindowName;
	}

	private <K extends String,V extends Integer> List<ItemVsFrequency> getItemsInDescendingOrder(Map<K, V> map) {
		List<ItemVsFrequency> itemVsFrequencies = new ArrayList<>();
		for (Map.Entry<K,V> entry : map.entrySet()) {
			itemVsFrequencies.add(new ItemVsFrequency(entry.getKey(), entry.getValue()));
		}

		Collections.sort(itemVsFrequencies, new DescendingComparator<>());
		return itemVsFrequencies;
	}

	private class ItemVsFrequency {
		public String itemName;
		public Integer frequency;

		public ItemVsFrequency(String itemName, Integer frequency) {
			this.itemName = itemName;
			this.frequency = frequency;
		}

	}

	private class DescendingComparator<T extends ItemVsFrequency> implements Comparator<T> {

		@Override
		public int compare(T o1, T o2) {
			return -1 * o1.frequency.compareTo(o2.frequency);
		}
	}
}
