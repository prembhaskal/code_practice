package projecteuler.set5;

import java.util.*;

public class P50ConsPrimeSum {

	private List<Integer> primes;
	private Set<Integer> primeSet;

	public int getMaxPrimeWithConsPrimes(int range) {
		init(range);

		System.out.println("total primes in the range " + range + " are " + primes.size());

		// array storing the cumulative sum of primes starting from 2
		long[] sumFrom2 = new long[primes.size()];

		long sum = 0;
		for (int i = 0; i < sumFrom2.length; i++) {
			sum += primes.get(i);
			sumFrom2[i] = sum;
		}

		// check if value of sum is present in the primeSet
		int maxPrimePresent = -1;
		int maxAt = -1;
		for (int i = 0; i < sumFrom2.length; i++) {
			if (sumFrom2[i] > Integer.MAX_VALUE)
				break;
			if (primeSet.contains((int)sumFrom2[i])) {
				maxPrimePresent = (int) sumFrom2[i];
				maxAt = i;
			}
		}

		System.out.println("max prime present is " + maxPrimePresent + " which is sum of "
				+ maxAt + " cons.. primes (starting from 2)");

		// check with the remaining combinations.
		// remove a prime, then check with the remaining sum.
		for (int skipPrimes = 0; skipPrimes < sumFrom2.length; skipPrimes++) {
			long removeStartingSum = sumFrom2[skipPrimes];

			// we can skip a prime, see primeIdx += 2, since except 2 primes are odd, hence take only odd no. of primes.
			for (int primeIdx = skipPrimes + 1; primeIdx < sumFrom2.length; primeIdx+=2) {
				long primeSum = sumFrom2[primeIdx] - removeStartingSum;
				if (primeSum > range) // this is the KEY POINT....should just check if the sum is less than range, and not Int.MAX
					break;

				int noOfPrimes = primeIdx - skipPrimes;
				if (noOfPrimes > maxAt && primeSet.contains((int)primeSum)) {
					maxAt = noOfPrimes;
					maxPrimePresent = (int) primeSum;
				}
			}
		}

		System.out.println("max prime present is " + maxPrimePresent
							+ " which is sum of " + maxAt + " cons. primes");

		return 0;
	}

	private void init(int range) {
		int sqrt = (int) Math.sqrt(range) + 1;
		boolean[] flag = new boolean[range + 1];
		Arrays.fill(flag, true);

		for (int i = 2; i < sqrt; i++) {
			for (int j = i * i; j <= range; j += i) {
				flag[j] = false;
			}
		}

		primes  = new ArrayList<>();
		primes.add(2);

		for (int i = 3; i < flag.length; i+=2) {
			if (flag[i])
				primes.add(i);
		}

		primeSet = new HashSet<>(primes);
	}
}
