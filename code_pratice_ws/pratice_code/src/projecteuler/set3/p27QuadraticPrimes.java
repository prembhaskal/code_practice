package projecteuler.set3;

import java.util.ArrayList;
import java.util.List;

public class p27QuadraticPrimes {

	List<Integer> primes;

	private void generatePrimes(int totalPrimes) {
		primes = new ArrayList<Integer>();

		primes.add(2);

		int num = 3;

		while (true) {
			boolean isPrime = true;

			for (int prime : primes) {

				if (prime > num/prime)
					break;

				if (num % prime==0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				primes.add(num);
			}

			num += 2;
		}
	}
}
