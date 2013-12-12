package projecteuler.set6;

import algorithm.UtilitiesClass;
import java.util.Arrays;

public class P57SquareRootConvergent {

	// if the term is x/y... then the next term is (x+2y)/(x+y)....do this on paper.
	public int getReqdFractions() {
		int[] numer = new int[]{1};
		int[] denominator = new int[]{1};
		int count = 0;

		for (int i = 1; i <= 1000; i++) {
			int[] y = addArray(numer, denominator);
			int[] x = addArray(numer, addArray(denominator, denominator));
			numer = x;
			denominator = y;

//			UtilitiesClass.printArrayNoSpaces(numer);
//			UtilitiesClass.printArrayNoSpaces(denominator);

			if (numer.length > denominator.length)
				count++;
		}

		return count;
	}

	private int[] addArray(int[] a, int[] b) {
		int[] smaller = (a.length <= b.length ? a : b);
		int[] bigger = (b.length >= a.length ? b: a);

		int[] num = new int[bigger.length + 1];
		int carry = 0;
		int i;

		// first add the smaller length number to bigger length number.
		for (i = 0; i < smaller.length; i++) {
			num[i] = smaller[i] + bigger[i] + carry;
			carry = num[i]/10;
			num[i] %= 10;
		}

		// then once smaller number is exhausted, consider only the bigger number.
		for (i = smaller.length; i < bigger.length; i++) {
			num[i] = bigger[i] + carry;
			carry = num[i]/10;
			num[i] %= 10;
		}

		// store the last carry.
		num[bigger.length] = carry;

		num = removeLeadingZeroes(num);
		return num;
	}

	private int[] removeLeadingZeroes(int[] num) {
		int zeroes = 0;
		for (int i = num.length - 1; i >= 0 ; i--) {
			if (num[i] == 0)
				zeroes++;
			else
				break;
		}

		if (zeroes == 0)
			return num;

		return Arrays.copyOfRange(num, 0, num.length - zeroes);
	}

	private int getDigits(int num) {
		int digits = 0;
		while (num > 0) {
			num /= 10;
			digits++;
		}
		
		return digits;
	}
}
