package projecteuler.set6;

import common.util.InputReader;
import java.util.*;

public class P58SpiralPrime {

	private Set<Integer> primes;
	private List<Integer> preComputeList;

	private int generateTill = 1000_000;
	private List<Integer> newList = new ArrayList<>();

	public int getSideLength() {
		generatePrimes(1000_000);

		int total = 1; // consider the 0 at the centre.
		int totalPrimes = 0;
		int minRatio = 100;
		for (int sideLength = 3; sideLength <= 30000; sideLength += 2) {
			int no1 = sideLength * sideLength;
			// no1 cannot be a prime, since it is a square.
			int no2 = no1 - sideLength + 1;
			int no3 = no2 - sideLength + 1;
			int no4 = no3 - sideLength + 1;
//			COMMENTED PART GIVES the answer but is way slower and hell complicated.
//			TRIAL division is much simpler.


//			if (generateTill < no1) {
//				addMorePrimes(no4, generateTill + 1000_000); // generate for the required range.
//				generateTill = generateTill + 1000_000;
//			}
//
//			if (isPrime(no2) && !primes.contains(no2)) {
//				System.out.println("discrepancy at 2 for " + no2);
//				break;
//			}
//
//			if (isPrime(no3) && !primes.contains(no3)) {
//				System.out.println("discrepancy at 3 for " + no3);
//				break;
//			}
//
//			if (isPrime(no4) && !primes.contains(no4)) {
//				System.out.println("discrepancy at 4 for " + no4);
//				break;
//			}

			if (isPrime(no4))
				totalPrimes++;
			if (isPrime(no3))
				totalPrimes++;
			if (isPrime(no2))
				totalPrimes++;

			total += 4;

			int ratio = totalPrimes * 100 / total;
			minRatio = (ratio < minRatio ? ratio : minRatio);
			if (ratio < 10)
				return sideLength;
		}
		System.out.println("min ratio found was " + minRatio);
		System.out.println("NOT FOUND");
		return -1;
	}

	private boolean isPrime(int num) {
		boolean isPrime = true;

		for (int prime : preComputeList) {
			if (num % prime == 0)
				return false;

			if (prime > num/prime)
				break;
		}

		return true;
	}


	private void generatePrimes(int range) {
		int sqrt = (int) Math.sqrt(range) + 1;
		boolean[] sieve = new boolean[range + 1];
		Arrays.fill(sieve, true);

		for (int i = 2; i < sqrt; i++) {
			for (int j = i * i; j <= range; j += i) {
				sieve[j] = false;
			}
		}

		preComputeList = new ArrayList<>();
		preComputeList.add(2);

		for (int i = 3; i < range; i += 2) {
			if (sieve[i])
				preComputeList.add(i);
		}
		primes = new HashSet<>(preComputeList);
	}

	private void addMorePrimes(int low, int high) {
		if (low < 2)
			low = 2;

		int sqrt = (int) Math.sqrt(high) + 1;

		boolean[] sieve = new boolean[high - low + 1];
		Arrays.fill(sieve, true);

		for (int prime : preComputeList) {
			if (prime > sqrt)
				break;

			int rem = low % prime;
			int start = low;
			if (rem > 0) {
				start = start + prime - rem;
			}

			for (int i = start; i <= high; i += prime)
				sieve[i - low] = false;
		}

		// remove older primes.
		primes.clear();
		newList.clear();

		// get the actual primes
		for (int i = 0; i < sieve.length; i++) {
			if (sieve[i])
				newList.add(low + i);
		}

		primes.addAll(newList);
	}
}
