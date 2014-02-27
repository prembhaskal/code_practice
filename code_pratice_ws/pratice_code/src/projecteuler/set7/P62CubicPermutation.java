package projecteuler.set7;

import java.util.HashMap;
import java.util.Map;

public class P62CubicPermutation {

	private Map<String, Integer> digitRepVsCount;
	private Map<String, Long> digitRepVsMinNumebr;

	// we may get a smaller number with a more wider range.
	public void solve(int limit, int matchingCubes) {

		digitRepVsCount = new HashMap<>();
		digitRepVsMinNumebr = new HashMap<>();

		// first find the cubes of nos from 100 to 999 and store them
		for (long i = 0; i < limit; i++) {
			long cube = i * i * i;
			String digitRep = transformToDigitCountRepresentation(cube);
			updateMapCount(digitRep);
			updateMinMap(digitRep, cube);
		}

		// get the minValue for count = 5;
		long minValue = Long.MAX_VALUE;
		boolean matchFound = false;
		for (String digitRep : digitRepVsCount.keySet()) {
			int count = digitRepVsCount.get(digitRep);
			if (count == matchingCubes) {
				long minCube = digitRepVsMinNumebr.get(digitRep);
				minValue = Math.min(minCube, minValue);
				matchFound = true;
			}
		}


		if (matchFound)
			System.out.println("min value for matching " +  matchingCubes + " nos is -- " + minValue);
		else
			System.out.println("no match found :( :( ");
	}

	private void updateMinMap(String digitRep, long cube) {
		Long minValue = digitRepVsMinNumebr.get(digitRep);

		if (minValue == null) {
			minValue = Long.MAX_VALUE;
		}

		minValue = Math.min(cube, minValue);
		digitRepVsMinNumebr.put(digitRep, minValue);
	}

	private void updateMapCount(String digitRep) {
		Integer sum = digitRepVsCount.get(digitRep);
		if (sum == null) {
			sum = 0;
		}
		sum = sum + 1;
		digitRepVsCount.put(digitRep, sum);
	}

	public String transformToDigitCountRepresentation(long number) {
		int[] digCount = new int[10];

		while (number > 0){
			int digit = (int)(number % 10);
			digCount[digit]++;
			number /= 10;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append(digCount[i]);
			sb.append("-");
		}

		return sb.toString();
	}
}
