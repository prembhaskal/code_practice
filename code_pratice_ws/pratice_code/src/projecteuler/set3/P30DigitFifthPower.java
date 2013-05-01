package projecteuler.set3;

import java.util.ArrayList;
import java.util.List;

public class P30DigitFifthPower {

	public int getDigitFifthPowerNumbersSum() {

		int numSum = 0;

		// single digit numbers are not considered
		for (int i=10;i<=1000000;i++) {

			List<Integer> digits = getDigits(i);
			int sum = getDigitsPowerSum(digits);

			if (sum == i) {
				System.out.println("number is " + i);
				numSum += sum;
			}
		}

		return numSum;
	}

	private List<Integer> getDigits(int num) {
		List<Integer> digits = new ArrayList<Integer>();

		while (num > 0) {
			digits.add(num%10);
			num /= 10;
		}

		return digits;
	}

	private int getDigitsPowerSum(List<Integer> digits) {
		int sum = 0;

		for (int digit : digits) {
			int pow = 1;

			for (int i=1;i<=5;i++) {
				pow = pow * digit;
			}

			sum += pow;
		}

		return sum;
	}
}
