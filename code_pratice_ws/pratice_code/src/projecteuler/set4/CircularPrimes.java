package projecteuler.set4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CircularPrimes {
	List<Integer> primes;
	Set<Integer> primeSet;

	public int findCircularPrimes(int range) {
		List<Integer> circularPrimes = new ArrayList<>();
		generatePrimes(range);
		primeSet = new HashSet<>(primes);

		for (int prime : primes) {
			List<Integer> allRotations = getAllRotations(prime);
			boolean isCirPrime = isCircularPrime(allRotations);
			if (isCirPrime) {
				circularPrimes.add(prime);
			}
		}
		return circularPrimes.size();
	}

	private boolean isCircularPrime(List<Integer> rotations) {
		boolean isCirPrime = true;
		for (int num : rotations) {
			if (!primeSet.contains(num)) {
				isCirPrime = false;
				break;
			}
		}

		return isCirPrime;
	}

	private void generatePrimes(int range) {
		primes = new ArrayList<>();
		primes.add(2);
		int num = 3;
		while (num < range) {
			boolean isPrime = true;

			for (int prime : primes) {
				if (num%prime==0) {
					isPrime = false;
					break;
				}

				if (prime > num/prime)
					break;
			}

			if (isPrime) {
				primes.add(num);
			}

			num += 2;
		}

	}

	private List<Integer> getAllRotations(int num) {
		List<Integer> rotNos = new ArrayList<>();
		int copy = num;
		int rotations = 0;

		while (num > 0) {
			num /= 10;
			rotations++;
		}

		num = copy;
		int factor = 1;
		for (int i = 0; i < rotations - 1; i++) {
			factor *= 10;
		}
		for (int i = 0; i < rotations; i++) {
			int ones = num%10;
			num /= 10;
			ones = factor*ones;
			num += ones;
			rotNos.add(num);
		}

		return rotNos;
	}

	// 356 635 563
	public void rotateNumber(int num) {
		int copy = num;

		int rotations = 0;

		while (num > 0) {
			num /= 10;
			rotations++;
		}

		num = copy;

		int factor = 1;
		for (int i = 0; i < rotations-1; i++) {
			factor *= 10;
		}
		for (int i = 0; i < rotations; i++) {
			int ones = num%10;
			num /= 10;
			ones *= factor;
			num = ones + num;
			System.out.println("rotation --> " + num);
		}
	}

}
