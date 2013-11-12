package projecteuler.set5;

import java.util.*;

public class P46GoldbachConjecture {

	public int getNonSatisfyingNumber() {
		generatePrimes(100000);

		int nonSatisfying = -1;

		for (int i = 3; i < 100000; i+=2) {

			boolean foundSatisfying = false;

			if (primeSet.contains(i))
				continue;

			for (int j = 1; j < i/j; j++) {
				int num = i - 2 * j * j;
				if (primeSet.contains(num)) {
					foundSatisfying = true;
					break;
				}
			}

			if (!foundSatisfying) {
				nonSatisfying = i;
				break;
			}
		}

		return nonSatisfying;
	}

	private List<Integer> primes;
	Set<Integer> primeSet;

	private void generatePrimes(int range) {
		int sqrt = (int) Math.sqrt(range) + 1;

		boolean [] flagPrimes = new boolean[range+1];
		Arrays.fill(flagPrimes, true);

		flagPrimes[0] = flagPrimes[1] = false;
		for (int i = 2; i < sqrt; i++) {
			for (int j = i * i; j <= range; j += i) {
				flagPrimes[j] = false;
			}
		}

		primes = new ArrayList<>();
		primes.add(2);
		for (int i = 3; i <= range; i+=2) {
			if (flagPrimes[i])
				primes.add(i);
		}

		primeSet = new HashSet<>(primes);
	}
}
