package projecteuler.set6;

import java.util.*;

public class P51PrimDigReplace {

	private List<Integer> primes;
	private List<int[]> primeArrays;

	public int getSmallestPrime(int primeValue, int primeDigits) {
		initPrimes(1000_000);
		initPrimeAsArrays(primeDigits);
		searchForPairs(primeDigits);
		return 0;
	}

	private void searchForPairs(int digits) {
		// digit 0 will not be searched as it has only odd nos.
		int maxi = -1;
		int maxj = -1;
		int maxScore = -1;
		int maxRemNum = -1;
		for (int i = 1; i < digits; i++) {
			for (int j = i + 1; j < digits; j++) {
				MaxRemNumMaxScore mrScore = getScore(i, j);
				if (mrScore.maxScore > maxScore) {
					maxScore = mrScore.maxScore;
					maxi = i;
					maxj = j;
					maxRemNum = mrScore.remNum;
				}
			}
		}

		System.out.println("max was for digits " + maxi + " and " + maxj + " with a max score of " + maxScore
							+ " with rem num as " + maxRemNum);
	}

	private Map<Integer, Integer> positionsVsRemNum = new HashMap<>();

	private MaxRemNumMaxScore getScore(int pos1, int pos2) {
		int maxScore = -1;
		int maxForRemNum = -1;
		Map<Integer, Integer> remNumVsCount = new HashMap<>();
		for (int[] prime : primeArrays) {
			if (prime[pos1] == prime[pos2]) {
				int remNum = getRemNum(prime, pos1, pos2);
				// put in the map
				Integer count = remNumVsCount.get(remNum);
				if (count == null) {
					count = 0;
				}
				count += 1;
				remNumVsCount.put(remNum, count);
				if (count > maxScore) {
					maxScore = count;
					maxForRemNum = remNum;
				}
			}

		}

		return new MaxRemNumMaxScore(maxForRemNum, maxScore);
	}

	private int getRemNum(int[] prime, int p1, int p2) {
		int num = 0;
		for (int i = prime.length -1; i >= 0; i--) {
			if (i == p1 || i == p2)
				continue;
			num = num * 10 + prime[i];
		}
		return num;
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

	private class MaxRemNumMaxScore {
		int remNum;
		int maxScore;
		public MaxRemNumMaxScore(int remNum, int maxScore) {
			this.remNum = remNum;
			this.maxScore = maxScore;
		}
	}
}
