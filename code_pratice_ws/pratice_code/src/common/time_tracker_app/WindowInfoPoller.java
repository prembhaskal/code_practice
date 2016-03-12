package common.time_tracker_app;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import static com.sun.jna.platform.win32.WinDef.*;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import common.time_tracker_app.winnative.Kernel32;
import common.time_tracker_app.winnative.User32DLL;

import static common.time_tracker_app.winnative.Kernel32.*;
import static common.time_tracker_app.winnative.Psapi.*;

import java.util.concurrent.BlockingQueue;

public class WindowInfoPoller implements Runnable {

	private BlockingQueue<WindowInfo> windowInfos;

	private static final int MAX_TITLE_LENGTH = 1024;

	public WindowInfoPoller(BlockingQueue<WindowInfo> windowInfos) {
		this.windowInfos = windowInfos;
	}

	@Override
	public void run() {
		try {
			HWND focussedWindowObj = getFocussedWindow();
			String rawWindowName = getCurrentFocussedWindowName(focussedWindowObj);
			String processName = getProcessNameForWindow(focussedWindowObj);
			WindowInfo windowInfo = new WindowInfo(rawWindowName, processName);
			windowInfos.put(windowInfo);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String getCurrentFocussedWindowName(HWND windowObj) {
		char[] buffer = new char[MAX_TITLE_LENGTH * 2];
		User32DLL.GetWindowTextW(windowObj, buffer, MAX_TITLE_LENGTH);
		String windowName = Native.toString(buffer);
		return windowName;
	}

	private String getProcessNameForWindow(HWND windowObj) throws InterruptedException {
		char[] buffer = new char[MAX_TITLE_LENGTH * 2];
		PointerByReference pointer = new PointerByReference();
		User32DLL.GetWindowThreadProcessId(windowObj, pointer);
		Pointer process = Kernel32.OpenProcess(PROCESS_QUERY_INFORMATION | PROCESS_VM_READ, false, pointer.getValue());
		GetModuleBaseNameW(process, null, buffer, MAX_TITLE_LENGTH);
		Pointer closeStatus = Kernel32.CloseHandle(process);
		// TODO how to read the close Status, does it return a pointer or an integer.
		return Native.toString(buffer);
	}

	private HWND getFocussedWindow() {
		return User32DLL.GetForegroundWindow();
	}
}
