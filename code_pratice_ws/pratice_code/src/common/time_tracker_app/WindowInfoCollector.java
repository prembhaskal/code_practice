package common.time_tracker_app;

import java.util.concurrent.BlockingQueue;

public class WindowInfoCollector implements Runnable {

	private BlockingQueue<WindowInfo> windowInfos;
	private FileAggregator fileAggregator;

	public WindowInfoCollector(BlockingQueue<WindowInfo> windowInfos, FileAggregator fileAggregator) {
		this.windowInfos = windowInfos;
		this.fileAggregator = fileAggregator;
	}

	@Override
	public void run() {
		try {
			// keep running forever
			while (true) {
				WindowInfo windowInfo = windowInfos.take();
				fileAggregator.aggregate(windowInfo);

				System.out.println("Window Name: " + windowInfo.getRawWindowName() + "\n"
								+  "Process Id: " + windowInfo.getProcessName());
			}
		} catch (InterruptedException e) {
			System.err.println("ERROR while waiting for windows information.");
		}
	}
}
