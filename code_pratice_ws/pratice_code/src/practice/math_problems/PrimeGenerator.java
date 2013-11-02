package practice.math_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimeGenerator {

	public void generatePrimeUsingOptimalTrialDivision(int end) {
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
	}

	public void generatePrimesSieveOfEratNormal(int end) {
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

		// list used to compare the timings properly.
		List<Integer> primesList = new ArrayList<>();

		for (int i = 2; i < primes.length; i++) {
			if (primes[i])
				primesList.add(i);
		}

		System.out.println("total primes are " + primesList.size());
	}
}
