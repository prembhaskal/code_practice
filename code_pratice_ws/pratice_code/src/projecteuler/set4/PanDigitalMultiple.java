package projecteuler.set4;

import java.util.*;

public class PanDigitalMultiple {

	public int getLargest9digitPanMultiple() {
		int maxPan = 0;

		maxPan = getPandigitalMultiple(9); // we know that there exists a pan for 9.

		// we will get largest when starting with 9 and 90 and 900 and so on.
		for (int i=90;i<100;i++) {
			Integer pan = getPandigitalMultiple(i);

			if (pan != null) {
				System.out.println(pan);
				maxPan = Math.max(pan, maxPan);
			}
		}

		for (int i=900;i<1000;i++) {
			Integer pan = getPandigitalMultiple(i);

			if (pan != null) {
				System.out.println(pan);
				maxPan = Math.max(pan, maxPan);
			}
		}

		for (int i=9000;i<10000;i++) {
			Integer pan = getPandigitalMultiple(i);

			if (pan != null) {
				System.out.println(pan);
				maxPan = Math.max(pan, maxPan);
			}
		}

		return maxPan;
	}

	// pandigital number or null if none exits.
	private Integer getPandigitalMultiple(int num) {
		List<Integer> prodConcat = new ArrayList<>();
		for (int i = 1; i < 9; i++) {
			List<Integer> prod = getDigits(num * i);
			prodConcat.addAll(prod);

			if (prodConcat.size()==9) {
				boolean isPan = isPanDigitalNumber(prodConcat);
				if (isPan) {
					return getNumberFromList(prodConcat);
				} else {
					return null;
				}

			}

			if (prodConcat.size() > 9) {
				return null;
			}
		}

		// we should definitely get a pan multiple number is there exists one.
		return null;
	}

	private int getNumberFromList(List<Integer> digits) {
		int num = 0;
		for (int digit : digits) {
			num = num*10 + digit;
		}

		return num;
	}

	private boolean isPanDigitalNumber(List<Integer> num) {
		Set<Integer> digits = new HashSet<>(num);

		if (digits.size()==9 && !digits.contains(0))
			return true;

		return false;
	}

	// get in order as a number is represented.
	public List<Integer> getDigits(int num) {
		List<Integer> digits = new ArrayList<>();
		while (num > 0) {
			digits.add(num%10);
			num /= 10;
		}

		Collections.reverse(digits);
		return digits;
	}
}
