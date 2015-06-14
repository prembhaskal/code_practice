package common.time_tracker_app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileAggregator {

	private File windowInfoRawFile;
	private final int BATCH_SIZE = 10;

	public static final String DATA_SEPARATOR = "_******_";

	private List<WindowInfo> windowInfoList = new ArrayList<>();

	public FileAggregator(File windowInfoRawFile) {
		this.windowInfoRawFile = windowInfoRawFile;
	}

	public void aggregate(WindowInfo windowInfo) {
		windowInfoList.add(windowInfo);

		if (windowInfoList.size() >= BATCH_SIZE) {
			flushDataAndAppendToFile();
			windowInfoList.clear();
		}
	}

	private void flushDataAndAppendToFile() {

		System.out.println("flushing data to the file " + windowInfoRawFile.getName());

		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(windowInfoRawFile.toPath(), Charset.defaultCharset(), StandardOpenOption.APPEND)) {

			for (WindowInfo windowInfo : windowInfoList) {
				String windowInfoStr = windowInfo.getRawWindowName() + DATA_SEPARATOR + windowInfo.getProcessName();
				bufferedWriter.write(windowInfoStr);
				bufferedWriter.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} ;
	}
}
