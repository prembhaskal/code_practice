package projecteuler.set6;

public class P52PermuteMultiples {

	public int get123456Permutation() {
		// we will test only for 101 to 166...since 1000/6=166.67, otherwise multiplying by 6 will increase the no of digits.
		// similarly for the other digits.

		int lower = 100;
		int upper = 166;

		for (int i = 0; i < 5; i++) {
			int num = getMatchingPermutation(lower, upper);
			if (num > 0)
				return num;

			lower = lower * 10;
			upper = upper * 10 + 6;
		}

		return -1; // not found
	}

	// returns -1 if not match present.
	private int getMatchingPermutation(int lower, int upper) {
		for (int i = lower; i <= upper; i++) {
			int[] baseDigits = getDigits(i);

			boolean found = true;

			// try with all multipliers
			for (int j = 2; j <= 6; j++) {
				int num = i  * j;
				int[] newDigits = getDigits(num);

				if (!checkDigitsAreSame(baseDigits, newDigits)) {
					found = false;
					break;
				}
			}

			if (found)
				return i;
		}

		return -1;
	}

	private int[] getDigits(int num) {
		int[] digits = new int[10];

		while (num > 0) {
			int digit = num % 10;
			digits[digit]++;
			num /= 10;
		}

		return digits;
	}

	private boolean checkDigitsAreSame(int[] digitsCnt1, int[] digitsCnt2) {
		for (int i = 0; i < digitsCnt1.length; i++) {
			if (digitsCnt1[i] != digitsCnt2[i])
				return false;
		}

		return true;
	}
}
