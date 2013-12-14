package topcoder.srm.s600.div2;

public class ORSolitaireDiv2 {

	// this approach is wrong
	// TODO find the correct approach later.
	public int getMinimum(int[] numbers, int goal) {

		String finalStr = Integer.toBinaryString(goal);

		int[] finalArray = convertToNumArray(finalStr);

		int[] sumArray = new int[64];

		for (int i = 0; i < numbers.length; i++) {
			String str = Integer.toBinaryString(numbers[i]);
			int[] num = convertToNumArray(str);

			for (int j = 0; j < num.length; j++) {
				sumArray[j] += num[j];
			}
		}

		// we got the final array, check which digits cannot be achieved


		return 0;
	}

	private int[] convertToNumArray(String str) {
		char[] finalChars = str.toCharArray();
		int[] finalArray = new int[finalChars.length];
		for (int i = 0; i < finalChars.length; i++) {
			char ch = finalChars[i];
			if (ch =='0')
				finalArray[i] = 0;
			else
				finalArray[i] = 1;
		}
		return finalArray;
	}
}
