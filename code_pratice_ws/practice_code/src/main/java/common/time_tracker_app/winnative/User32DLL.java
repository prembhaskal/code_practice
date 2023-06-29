package common.time_tracker_app.winnative;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.PointerByReference;

public class User32DLL {
	static { Native.register("user32"); }
	public static native int GetWindowThreadProcessId(WinDef.HWND hWnd, PointerByReference pref);
	public static native WinDef.HWND GetForegroundWindow();
	public static native int GetWindowTextW(WinDef.HWND hWnd, char[] lpString, int nMaxCount);
}
