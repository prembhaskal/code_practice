package projecteuler.set6;

import java.util.*;

public class P60PrimePairSets {

	private List<Integer> primes;
	private int TOTAL_PRIMES = 2000; // got this range by experimenting.. not the ideal way.
	private int DEFAULT_RANGE = 1000_000;

	private Set<Long> pairSet;

	public int  getPrimePairSetSum() {
		generatePrimes();
		generatePairSet();
		System.out.println("last prime checked is " + primes.get(TOTAL_PRIMES));
		int primeSetSum = 0;
//		primeSetSum = getFourPrimeSets();
		primeSetSum = getFivePrimeSets();
		return primeSetSum;
	}

	private int getFourPrimeSets() {
		boolean check = false;
		int sum = Integer.MAX_VALUE;
		// start with the prime 3 since 2 being even cannot participate in concatenate operations.
		start:
		for (int a = 1; a < TOTAL_PRIMES; a++) {
			int p1 = primes.get(a);
			for (int b = a + 1; b < TOTAL_PRIMES; b++) {
				int p2 = primes.get(b);
				check = isPairSet(p1, p2);
				if (!check)
					continue; // STOP EARLY.
				for (int c = b + 1; c < TOTAL_PRIMES; c++) {
					int p3 = primes.get(c);
					check = isPairSet(p1, p3) && isPairSet(p2, p3);
					if (!check)
						continue;
					for (int d = c + 1; d < TOTAL_PRIMES; d++) {
						int p4 = primes.get(d);
						check = isPairSet(p1, p4) && isPairSet(p2, p4) && isPairSet(p3, p4);
						if (check) {
							int thisSum = p1 + p2 + p3 + p4;
							sum = Math.min(sum, thisSum); // the first sum is not the smallest ;(, so keep searching.
						}
					}
				}
			}
		}

		return sum;
	}


	private int getFivePrimeSets() {
		boolean check = false;
		int sum = Integer.MAX_VALUE;
		// start with the prime 3 since 2 being even cannot participate in concatenate operations.
		start:
		for (int a = 1; a < TOTAL_PRIMES; a++) {
			int p1 = primes.get(a);
			if (p1 * 5 > sum) break;
			for (int b = a + 1; b < TOTAL_PRIMES; b++) {
				int p2 = primes.get(b);
				if (p1 + p2 *4 > sum)  break;
				check = isPairSet(p1, p2);
				if (!check)
					continue;
				for (int c = b + 1; c < TOTAL_PRIMES; c++) {
					int p3 = primes.get(c);
					if (p1 + p2 + p3 *3 > sum) break; // stop searching if we cannot find result lesser than sum.
					check = isPairSet(p1, p3) && isPairSet(p2, p3);
					if (!check)
						continue;
					for (int d = c + 1; d < TOTAL_PRIMES; d++) {
						int p4 = primes.get(d);
						if (p1 + p2 + p3 + p4 *2 > sum) break;
						check = isPairSet(p1, p4) && isPairSet(p2, p4) && isPairSet(p3, p4);
						if (!check)
							continue;
						for (int e = d + 1; e < TOTAL_PRIMES; e++) {
							int p5 = primes.get(e);
							check = isPairSet(p1, p5) && isPairSet(p2, p5) && isPairSet(p3, p5) && isPairSet(p4, p5);
							if (check) {
								int thisSum = p1 + p2 + p3 + p4 + p5;
								sum = Math.min(sum, thisSum);
								break; // we dont need to find more.
							}
						}
					}
				}
			}
		}

		return sum;
	}

	private boolean checkIfPairSet(int[] nos) {
		for (int i = 0; i < nos.length; i++) {
			for (int j = i + 1; j < nos.length; j++) {
				boolean check = isPairSet(nos[i], nos[j]);
				if (!check)
					return false;
			}
		}

		return true;
	}

	private boolean isPairSet(int a, int b) {
		long num = concatNumber(a, b);
		if (!pairSet.contains(num))
			return false;

		num = concatNumber(b, a);
		if (!pairSet.contains(num))
			return false;

		return true;
	}

	private long concatNumber(int a, int b) {
		long num = Long.parseLong(a + "" + b);
		return num;
	}

	private boolean isPrime(long num) {
		for (int prime : primes) {
			if (num % prime == 0)
				return false;

			if (prime > num / prime)
				break;
		}

		return true;
	}

	private void generatePrimes() {
		int range = DEFAULT_RANGE;
		boolean[] mark = new boolean[range+1];
		Arrays.fill(mark, true);

		// mark
		for (int i = 2; i * i < range; i++) {
			for (int j = i * i; j <= range; j += i) {
				mark[j] = false;
			}
		}

		primes = new ArrayList<>();
		primes.add(2);

		for (int i = 3; i <= range; i += 2) {
			if (mark[i]) {
				primes.add(i);
			}
		}
	}

	// TODO this look up creation takes a lot of time.
	private void generatePairSet() {
		pairSet = new HashSet<>();
		for (int i = 0; i < TOTAL_PRIMES; i++) {
			for (int j =  i + 1; j < TOTAL_PRIMES; j++) {
				int a = primes.get(i);
				int b = primes.get(j);
				long num = concatNumber(a, b);
				if (isPrime(num))
					pairSet.add(num);

				num = concatNumber(b, a);
				if (isPrime(num))
					pairSet.add(num);
			}
		}
	}
}
