package projecteuler.set3;

import java.util.HashMap;
import java.util.Map;

public class P26ReciprocalCycles {

	public void findNumberWithMaxReciprocalCycles() {
		int maxDigits = 0;
		int mxNum = 1;

		for (int i=2;i<1000;i++) {
			int repDigits = getRepeatingDigitsInReciprocal(i);

			if (repDigits > maxDigits) {
				maxDigits = repDigits;
				mxNum = i;
			}
		}

		System.out.println("max digits = " + maxDigits + " for the number " + mxNum);
	}

	public int getRepeatingDigitsInReciprocal(int divisor) {

		boolean found = false;
		Map<Integer, Integer> numberDivisorMap = new HashMap<Integer, Integer>();

		int num = 10;
		int length = 0;
		while (true) {

			if (num == 0)
				break;

			Integer divInMap = numberDivisorMap.get(num);

			if (divInMap!=null && divInMap==divisor)
				break; // digits start repeating when number and divisor have occurred earlier.

			numberDivisorMap.put(num, divisor);

			int rem = num % divisor;

			num = rem;
			num *= 10;
			length++;


		}

		return length;
	}
}
