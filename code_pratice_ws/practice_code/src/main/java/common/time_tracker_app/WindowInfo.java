package common.time_tracker_app;

public class WindowInfo {

	private String rawWindowName;
	private String processName;

	public WindowInfo(String rawWindowName, String processName) {
		this.rawWindowName = rawWindowName;
		this.processName = processName;
	}

	public String getRawWindowName() {
		return rawWindowName;
	}

	public String getProcessName() {
		return processName;
	}

}
