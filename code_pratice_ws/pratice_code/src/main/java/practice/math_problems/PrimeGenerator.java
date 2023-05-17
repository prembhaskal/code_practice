package practice.math_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeGenerator {

	// TODO implement the code given in http://code.google.com/p/primesieve/, it seems much faster.

	int PRECOMPUTE_LIMIT = 1000_000;

	public List<Integer> generatePrimeUsingOptimalTrialDivision(int end) {
		List<Integer> primes = new ArrayList<>();
		primes.add(2);

		int num = 3;

		while (num <= end) {
			boolean isPrime = true;

			for (int prime : primes) {
				if (prime > num / prime)
					break;

				if (num % prime == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime)
				primes.add(num);

			num += 2;
		}

		System.out.println("total primes are " + primes.size());

		return primes;
	}

	public void generatePrimesInRangeTrialDivision(long lower, long upper) {
		// pre-compute primes
		int sqrt = (int) Math.sqrt(upper) + 1;
		int limit = Math.min(PRECOMPUTE_LIMIT, sqrt);

		List<Integer> primes = generatePrimeUsingOptimalTrialDivision(limit);

		long num = lower;

		if (num%2 == 0)
			num++;

		List<Long> newPrimes = new ArrayList<>();

		while (num <= upper) {
			boolean isPrime = true;

			for (int prime : primes) {
				if (prime > num / prime)
					break;
				if (num % prime == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime)
				newPrimes.add(num);

			num += 2;
		}

		System.out.println("total primes in the range " + lower + " to " + upper + " are " + newPrimes.size());
	}

	public List<Integer> generatePrimesSieveOfEratNormal(int end) {
		int sqrt = (int) Math.sqrt(end);

		boolean [] primes = new boolean[end+1];

		// mark all as prime in beginning.
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;

		// find primes till the 'end'.
		for (int i = 2; i * i <= end; i++) {
			for (int j = i * i; j <= end; j += i) {
				primes[j] = false;
			}
		}

		List<Integer> primesList = new ArrayList<>();
		primesList.add(2);
		for (int i = 3; i < primes.length; i += 2) {
			if (primes[i])
				primesList.add(i);
		}

		System.out.println("total primes are " + primesList.size());

		return primesList;
	}

	public void generatePrimesInRangeUsingSeive(long lower, long upper) {
		lower = Math.max(lower, 2);
		int sqrt = (int) Math.sqrt(upper) + 1;
		int limit = Math.min(sqrt, PRECOMPUTE_LIMIT);

		List<Integer> primeList = generatePrimesSieveOfEratNormal(limit);

		List<Long> newPrimeList = new ArrayList<>();

		int arrSize = (int)(upper - lower + 1);
		boolean [] newPrimes = new boolean[arrSize];
		Arrays.fill(newPrimes, true);

		for (int prime : primeList) {
			if (prime > limit)
				break; // STOP we got only this many primes
			// find the number >= lower, such that number is multiple of prime.
			long start = lower;
			int rem = (int) (start % prime);
			if (rem != 0) {
				start = start + prime - rem;
			}

			for ( ; start <= upper; start+= prime)
				newPrimes[((int) (start - lower))] = false;
		}

		for (int i = 0; i < newPrimes.length; i++) {
			if (newPrimes[i]) {
				newPrimeList.add(lower+i);
			}

		}

		System.out.println("total primes in the range " + lower + " to " + upper + " are " + newPrimeList.size());
	}
}
