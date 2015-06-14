package common.time_tracker_app;

import com.sun.jna.Native;
import common.time_tracker_app.winnative.User32DLL;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.*;

public class FindFocussedWindow {


	public static void main(String[] args) throws IOException, InterruptedException {
//		new FindFocussedWindow().runSchedulerJob();
		new FindFocussedWindow().startInfoCollection();
	}


	public void runSchedulerJob() throws IOException, InterruptedException {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		Runnable printOpenWindows = new PrintOpenWindows(new PrintWriter(System.out));
		executorService.scheduleAtFixedRate(printOpenWindows, 0, 5, TimeUnit.SECONDS);

		new ArrayBlockingQueue<Integer>(1).take();
	}

	public void startInfoCollection() throws IOException {
		BlockingQueue<WindowInfo> windowInfos = new ArrayBlockingQueue<>(10);
		Runnable windowInfoPoller = new WindowInfoPoller(windowInfos);

		File windowInfoDataFile = new File("output/window_info_data.txt");
//		windowInfoDataFile.createNewFile();
		FileAggregator fileAggregator = new FileAggregator(windowInfoDataFile);
		WindowInfoCollector windowInfoCollector = new WindowInfoCollector(windowInfos, fileAggregator);

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scheduledExecutorService.scheduleAtFixedRate(windowInfoPoller, 0, 5, TimeUnit.SECONDS);

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(windowInfoCollector);

		// wait forever.
		while (true) {
			try {
				new ArrayBlockingQueue<Integer>(1).take();
			} catch (InterruptedException e) {
				e.printStackTrace(); // print log and continue;
			}
		}
	}


	private class PrintOpenWindows implements Runnable {

//		private File file = new File("openWindowsFile");
		private static final int MAX_TITLE_LENGTH = 1024;

		private final PrintWriter out;
		public PrintOpenWindows(PrintWriter out) throws IOException {
//			file.createNewFile();
			this.out = out;
		}

		@Override
		public void run() {
			out.print("WindowName --> ");
			out.println(getWindowName());
			out.flush();
		}

		private String getWindowName() {
			char[] buffer = new char[MAX_TITLE_LENGTH * 2];
			User32DLL.GetWindowTextW(User32DLL.GetForegroundWindow(), buffer, MAX_TITLE_LENGTH);
			String windowName = Native.toString(buffer);
			return windowName;
		}
	}

}


