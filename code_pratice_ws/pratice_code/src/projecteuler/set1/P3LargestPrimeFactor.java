package projecteuler.set1;

public class P3LargestPrimeFactor {

	/**
	 * TRIAL DIVISION FOR FINDING FACTORS
	 * @param num
	 * @return
	 */
	public long findLargestPrimeFactor(long num) {
		long prime = 2;

		for (;prime <= num/prime; prime++) {

			while (num%prime == 0) {
				num /= prime;
			}
		}

		if (num > 1)
			prime = num;

		return prime;
	}
}
