package projecteuler.set4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// TODO write a solution by appending 3 9 7 to the number generated
public class TruncatablePrimes {

	List<Integer> primes;
	Set<Integer> primeSet;
	public int findTruncatablePrimes(int range) {
		int sum = 0;
		generatePrimes(range);
		primeSet = new HashSet<>(primes);

		for (int prime : primes) {
			boolean isTrunPrime = isTruncatablePrime(prime);
			if (isTrunPrime) {
				System.out.println(prime);
				sum += prime;
			}

		}

		return sum;
	}

	public boolean isTruncatablePrime(int prime) {

		if (prime < 10)
			return false;

		// truncate left to right
		int copy = prime;

		int div = 10;
		while (div < prime) {
			int newNum = prime%div;
			div *= 10;
			if (!primeSet.contains(newNum)) {
				return false;
			}
		}

		// truncate right to left
		div = 10;
		while (prime > 0) {
			if (!primeSet.contains(prime)) {
				return false;
			}
			int newNum = prime/div;
			prime = newNum;
		}


		return true;
	}

	private void generatePrimes(int range) {
		primes = new ArrayList<>();
		primes.add(2);

		for (int i=3;i<range;i+=2) {
			boolean isPrime = true;

			for (int prime : primes) {
				if (i%prime==0) {
					isPrime = false;
					break;
				}

				if (prime > i/prime) {
					break;
				}
			}

			if (isPrime) {
				System.out.print(i + ",");
				primes.add(i);
			}

		}
	}
}
