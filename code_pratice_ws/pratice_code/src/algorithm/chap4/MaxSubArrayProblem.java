package algorithm.chap4;

public class MaxSubArrayProblem {

	// input --> array storing the difference from previous num
	public int getMaxSubArrayBruteForce(int[] num) {

		int start = -1;
		int end = -1;

		int best = Integer.MIN_VALUE;

		for (int i = 0; i < num.length; i++) {
			int sum = 0;
			for (int j = i; j < num.length; j++) {
				sum += num[j];
				if (sum > best) {
					best = sum;
					start = i;
					end = j;
				}
			}
		}

		System.out.println("max value is " + best);
		System.out.println("start is " + start + " and end is " + end);

		return best;
	}

	// see kaden's algorithm
	public int getMaxSubArrayLinear(int[] num) {
		int start = 0;
		int end = 0;

		int start_temp = 0;

		int max_end_here = num[0];
		int max_so_far = num[0];

		for (int i = 1; i < num.length; i++) {
			// reset max end here to present number if it was less than 0
			// we always try to take positive numbers only
			if (max_end_here < 0) {
				max_end_here = num[i];
				start_temp = i;
			} else {
				max_end_here += num[i];
			}

			if (max_end_here >= max_so_far) {
				max_so_far = Math.max(max_end_here, max_so_far);
				start = start_temp; // get the start used for calculating this max value.
				end = i;
			}
		}

		System.out.println("max value is " + max_so_far);
		System.out.println("start is " + start + " and end is " + end);

		return max_so_far;
	}
}
