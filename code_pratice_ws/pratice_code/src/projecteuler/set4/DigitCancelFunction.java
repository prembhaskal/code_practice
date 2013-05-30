package projecteuler.set4;

import java.util.ArrayList;
import java.util.List;

public class DigitCancelFunction {

	public void getAllDigitCancellingFunctions() {
		for (int i=11;i<100;i++) {
			for( int j=i+1;j<100;j++){
				if((i%10!=0) && (j%10!=0)) {
					boolean isDigCancel = isDigitCancelFunction(i,j);

					if (isDigCancel){
						System.out.println(i + " / " + j);
					}
				}
			}
		}
	}

	public boolean isDigitCancelFunction(int numerator, int denominator) {
		Integer commonDigit = hasCommonDigit(numerator, denominator);
		if (commonDigit == null)
			return false;

		int newNum = getNumAfterRemoveDigit(numerator, commonDigit);
		int newDenom = getNumAfterRemoveDigit(denominator, commonDigit);

		int prod1 = numerator * newDenom;
		int prod2 = denominator * newNum;

		if (prod1 == prod2)
			return true;
		else
			return false;
	}

	private int getNumAfterRemoveDigit(int num, int digit) {
		List<Integer> digits = getDigits(num);
		for (int i=0;i<2;i++) {
			if (digits.get(i)==digit) {
				digits.remove(i);
				break;
			}
		}

		return digits.get(0);
	}

	private Integer hasCommonDigit(int num1, int num2) {
		List<Integer> digits1 = getDigits(num1);
		List<Integer> digits2 = getDigits(num2);

		boolean hasCommon;

		for (int digit : digits1) {
			hasCommon = digits2.contains(digit);
			if (hasCommon)
				return digit;
		}

		return null;
	}

	private List<Integer> getDigits(int num) {
		List<Integer> digits = new ArrayList<Integer>();
		while (num > 0) {
			digits.add(num%10);
			num /= 10;
		}

		return digits;
	}
}
