package projecteuler.set5;

import java.util.ArrayList;
import java.util.List;

public class ChampernowneConstant {

	public int getResult(){
		List<Integer> digitList = new ArrayList<>();
		// counter for digits in a number.
		int digitCount = 1;
		int totalDigits = 0;
		// marker to increase number of digits.
		int marker = 10;
		// the digits to be captured
		int captureDigits = 1;

		for (int i=1;i<1000000;i++) {
			if (i == marker) {
				digitCount++;
				marker *= 10;
			}

			totalDigits += digitCount;

			if (totalDigits >= captureDigits) {
				int offset = totalDigits-captureDigits;
				int getDigit = getDigitFromNumber(i, offset);

				digitList.add(getDigit);

				// reset the captureDigits
				captureDigits *= 10;

				if (digitList.size()==7)
					break;
			}
		}

		int prod = 1;
		for (int i : digitList) {
			prod *= i;
		}

		return prod;
	}

	// returns digit from left LSB (0 based index)
	public int getDigitFromNumber(int num, int digit) {
		String str = num + "";
		char[] chars = str.toCharArray();
		int len = chars.length;

		return Integer.parseInt(chars[len-1-digit] + "");
	}
}
