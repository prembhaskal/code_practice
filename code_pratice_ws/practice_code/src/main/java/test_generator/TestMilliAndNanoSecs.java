package test_generator;

import java.util.ArrayList;
import java.util.List;

public class TestMilliAndNanoSecs {

	public static void main(String[] s) {
		TestMilliAndNanoSecs testMilliAndNanoSecs = new TestMilliAndNanoSecs();
		testMilliAndNanoSecs.testSettingMilliSecs(20);
		testMilliAndNanoSecs.testSettingNanoSecs(20);
	}

	private void testSettingMilliSecs(int range) {
		long time;
		List<Long> milliSecsTime = new ArrayList<>();

		for (int i = 0; i < range; i++) {
			time = System.currentTimeMillis();
			milliSecsTime.add(time);
		}

		for (long timeMil : milliSecsTime) {
			System.out.println("milli time --> " + timeMil);
		}
	}

	private void testSettingNanoSecs(int range) {
		List<Long> nanoSecsTime = new ArrayList<>();
		long time;

		for (int i = 0; i < range; i++) {
			time = System.nanoTime();
			nanoSecsTime.add(time);
		}

		for (long nanoTime : nanoSecsTime) {
			System.out.println("nano time --> " + nanoTime);
		}
	}
}