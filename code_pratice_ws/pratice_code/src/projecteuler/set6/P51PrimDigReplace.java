package projecteuler.set6;

import java.util.*;

public class P51PrimDigReplace {

	private List<Integer> primes;
	private List<int[]> primeArrays;

	public int getSmallestPrime(int primeValue, int primeDigits) {
		initPrimes(1000_000);
		initPrimeAsArrays(primeDigits);
		return 0;
	}

	private void searchForPairs(int digits) {
		// digit 0 will not be searched as it has only odd nos.
		for (int i = 1; i < digits; i++) {
			for (int j = i + 1; j < digits; j++) {
				
			}
		}
	}

	private int getScore(int pos1, int pos2) {
		Set<Integer> score = new HashSet<>();
		for (int[] prime : primeArrays) {
			if (prime[pos1] == prime[pos2])
				score.add(prime[pos1]);
		}

		return score.size();
	}

	private void initPrimes(int range) {
		int sqrt = (int) Math.sqrt(range) + 1;
		boolean [] flags = new boolean[range + 1];
		Arrays.fill(flags, true);
		for (int i = 2; i < sqrt; i++) {
			for (int j = i * i; j <= range; j += i) {
				flags[j] = false;
			}
		}
		primes = new ArrayList<>();
		primes.add(2);
		for (int i = 3; i < flags.length; i += 2) {
			if (flags[i])
				primes.add(i);
		}
	}

	private void initPrimeAsArrays(int digits) {
		int low = 1;
		for (int i = 0; i < digits - 1; i++) {
			low *= 10;
		}
		int high = low * 10 - 1;

		primeArrays = new ArrayList<>();

		for (int prime : primes) {
			if (prime < low)
				continue;
			if (prime > high)
				break;

			int[] primeToArray = convertToArray(prime, digits);
			primeArrays.add(primeToArray);
		}
	}

	private int[] convertToArray(int num, int digit) {
		int[] digits = new int[digit];

		for (int i = 0; i < digit; i++) {
			digits[i] = num % 10;
			num /= 10;
		}

		return digits;
	}
}
