package projecteuler.set4;

import java.util.*;

public class PanDigitalProducts {

	public long getPanDigitalProductSum() {

		long sum = 0;

		for (int i=2;i<10000;i++) {
			boolean passPreCheck = passPreCheck(i);

			if (!passPreCheck) {
				continue;
			}

			boolean isPanDigital = isPanDigitalProduct(i);
			if (isPanDigital) {
				System.out.println(i);
				sum += i;
			}
		}

		return sum;
	}

	public boolean isPanDigitalProduct(int num) {
		for (int i=2;i<=num/i;i++) {
			if (num%i==0) {
				int mul1 = i;
				int mul2 = num/i;

				boolean isPanDigital = checkIfPanDigital(num, mul1, mul2);

				if (isPanDigital) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean checkIfPanDigital(int num1, int num2, int num3) {
		Set<Integer> digitSet= new HashSet<Integer>();

		List<Integer> list1 = getDigits(num1);
		List<Integer> list2 = getDigits(num2);
		List<Integer> list3 = getDigits(num3);

		digitSet.addAll(list1);
		digitSet.addAll(list2);
		digitSet.addAll(list3);

		int digits = list1.size() + list2.size() + list3.size();

		if (digits==9 && digitSet.size()==9 && !digitSet.contains(0)) {
			return true;
		}

		return false;
	}

	private boolean passPreCheck(int num) {
		List<Integer> digits = getDigits(num);

		Set<Integer> digSet = new HashSet<Integer>(digits);

		if (digits.size() != digSet.size() || digSet.contains(0))
			return false;

		return true;
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
