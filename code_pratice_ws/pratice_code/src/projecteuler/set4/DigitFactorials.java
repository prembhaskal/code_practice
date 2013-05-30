package projecteuler.set4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DigitFactorials {

	private Map<Integer, Integer> factorials = new HashMap<Integer, Integer>();
	public int getDigitFactorialSum() {
		int range = getFactorial(9) * 9;

		int numSum = 0;

		initFactMap();

		for (int i=10;i<=range;i++) {
			List<Integer> digits = getDigits(i);

			int sum = 0;
			for (int digit: digits) {
//				sum += getFactorial(digit);
				sum += factorials.get(digit);
			}

			if (sum == i) {
				System.out.println("found number --> " + i);
				numSum += i;
			}
		}

		return numSum;
	}

	private void initFactMap() {
		for (int i = 0; i < 10; i++) {
			int fact = getFactorial(i);
			factorials.put(i,fact);
		}
	}


	private List<Integer> getDigits(int num) {
		List<Integer> digits = new ArrayList<Integer>();

		while (num > 0) {
			digits.add(num%10);
			num /= 10;
		}

		return digits;
	}

	private int getFactorial(int num) {
		int prod = 1;

		while (num > 1) {
			prod *= num;
			num--;
		}

		return prod;
	}
}
