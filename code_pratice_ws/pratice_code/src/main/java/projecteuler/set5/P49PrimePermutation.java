package projecteuler.set5;

import java.util.*;

public class P49PrimePermutation {

	public String get4DigitPrimePermutation() {
		initPrimes();
		Map<String, List<Integer>> digitsVsPrimes = new HashMap<>();

		String digits;
		for (int prime : fourDigPrimes) {
			digits = getDigits(prime);
			List<Integer> primes = digitsVsPrimes.get(digits);
			if (primes == null) {
				primes = new ArrayList<>();
				digitsVsPrimes.put(digits, primes);
			}

			primes.add(prime);
		}

		for (List<Integer> primes : digitsVsPrimes.values()) {
			if (primes.size() >= 3) {
				List<Integer> foundPrimes = checkIfSequencePresent(primes);
				if (foundPrimes != null)
					System.out.println("found sequence at "
					+ foundPrimes.get(0)
					+ "" + foundPrimes.get(1)
					+ "" + foundPrimes.get(2));
			}
		}

		return "";
	}

	private List<Integer> checkIfSequencePresent(List<Integer> sortNos) {
		for (int i = 0; i < sortNos.size() - 2; i++) {
			for (int j = i + 1; j < sortNos.size() - 1; j++) {
				for (int k = j + 1; k < sortNos.size(); k++) {
					if (sortNos.get(i) + sortNos.get(k) == 2 * sortNos.get(j))
						return Arrays.asList(sortNos.get(i), sortNos.get(j), sortNos.get(k));
				}
			}
		}

		return null;
	}

	private String getDigits(int num) {
		List<Integer> digits = new ArrayList<>();

		while (num > 0) {
			int dig = num % 10;
			num /= 10;
			digits.add(dig);
		}

		Collections.sort(digits);
		String str = "";
		for (int dig : digits)
			str += dig;

		return str;
	}

	private List<Integer> fourDigPrimes;

	private void initPrimes() {
		int range = 10000;
		int sqrt = (int) Math.sqrt(range) + 1;
		boolean [] flag = new boolean[range + 1];
		Arrays.fill(flag, true);

		for (int i = 2; i < sqrt; i++) {
			for (int j = i * i; j <= range; j+=i) {
				flag[j] = false;
			}
		}

		fourDigPrimes = new ArrayList<>();
		for (int i = 1001; i < range; i+=2) {
			if (flag[i])
				fourDigPrimes.add(i);
		}
	}
}
