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
		int[] maxReplaceDig = null;
		int maxScore = -1;
		int maxRemNum = -1;

		// generate all mask for 2,3 digits replacing.
		List<int[]> replaceDigits = get3ReplaceDigits(digits);
//		List<int[]> replaceDigits = get2ReplaceDigits(digits);

		// store the maxCount using this mask... replaceDig.
		for (int[] replaceDig : replaceDigits) {
			MaxRemNumMaxScore mrScore = getScore(replaceDig);
			if (mrScore.maxScore > maxScore) {
				maxScore = mrScore.maxScore;
				maxRemNum = mrScore.remNum;
				maxReplaceDig = replaceDig;
			}
		}

		System.out.print("max replace digs are ");
		for (int i = 0; i < maxReplaceDig.length; i++) {
			System.out.print(maxReplaceDig[i] + " ");
		}
		System.out.println("");
		System.out.println("with a max score of " + maxScore + " with rem num as " + maxRemNum);
	}

	private Map<Integer, Integer> positionsVsRemNum = new HashMap<>();

	// apply the max pos, and check the remaining of the number. store the max count.
	// this is done using map<remNumber, countofThat>.
	private MaxRemNumMaxScore getScore(int[] pos) {
		int maxScore = -1;
		int maxForRemNum = -1;
		Map<Integer, Integer> remNumVsCount = new HashMap<>();
		for (int[] prime : primeArrays) {
			boolean matching = true;
			for (int i = 1; i < pos.length; i++) {
				if (prime[pos[i-1]] != prime[pos[i]]) {
					matching = false;
					break;
				}
			}

			if (!matching)
				continue;

			int remNum = getRemNum(prime, pos);
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

		return new MaxRemNumMaxScore(maxForRemNum, maxScore);
	}

	// using the prime and replace digit, find the remaining num, so that we can easily use it in map.
	// this is analogous to NUMBER & BIT_MASK operation....
	private int getRemNum(int[] prime, int[] pos) {
		int num = 0;
		boolean [] notUseDigit = new boolean[prime.length];
		for (int i = 0; i < pos.length; i++) {
			notUseDigit[pos[i]] = true;
		}


		for (int i = prime.length - 1; i >= 0; i--) {
			if (notUseDigit[i])
				continue;
			num = num * 10 + prime[i];
		}
		return num;
	}

	// TODO write a fast, simple recursive function for it.
	private List<int[]> get3ReplaceDigits(int totalDigits) {
		List<int[]> replaceDigits = new ArrayList<>();
		for (int i = 1; i < totalDigits; i++) {
			for (int j = i + 1; j < totalDigits; j++) {
				for (int k = j + 1; k < totalDigits; k++) {
					int[] digits = new int[]{i, j, k};
					replaceDigits.add(digits);
				}
			}
		}

		return replaceDigits;
	}

	private List<int[]> get2ReplaceDigits(int totalDigits) {
		List<int[]> replaceDigits = new ArrayList<>();
		for (int i = 1; i < totalDigits; i++) {
			for (int j = i + 1; j < totalDigits; j++) {
				int[] digits = new int[]{i, j,};
				replaceDigits.add(digits);
			}
		}

		return replaceDigits;
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

	// STORING primes as arrays so that we easily mask them.
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
