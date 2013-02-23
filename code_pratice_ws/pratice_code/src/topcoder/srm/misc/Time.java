package topcoder.srm.misc;

// SRM 144 div 2
public class Time {

	public String whatTime(int seconds) {
		int hours = seconds/3600;
		int minutes = (seconds - hours*3600)/60;
		int second = (seconds - hours*3600 - minutes*60);

		return hours + ":" + minutes + ":" + second;
	}
}
