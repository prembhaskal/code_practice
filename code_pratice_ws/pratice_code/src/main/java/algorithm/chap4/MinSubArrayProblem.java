package algorithm.chap4;

public class MinSubArrayProblem {

	public int getMinSubArrayBruteForce(int[] num, boolean print) {
		int best = Integer.MAX_VALUE;

		int start = -1;
		int end = -1;

		for (int i = 0; i < num.length; i++) {
			int sum = 0;
			for (int j = i; j < num.length; j++) {
				sum += num[j];
				if (sum < best) {
					best = sum;
					start = i;
					end = j;
				}
			}
		}

		if (print) {
			System.out.println("min value is " + best);
			System.out.println("min start is --> " + start + " and min end is --> " + end);
		}

		return best;
	}

	public int getMinSubArrayLinear(int[] num, boolean print) {
		int best;

		int start = 0;
		int start_temp = 0;
		int end = 0;

		int min_till_here = num[0];
		int min_so_far = num[0];

		for (int i=1;i<num.length;i++) {
			// reset if positive, we need only negative numbers
			if (min_till_here > 0) {
				min_till_here = num[i];
				start_temp = i;
			} else {
				min_till_here += num[i];
			}

			if (min_till_here <= min_so_far) {
				start = start_temp;
				end = i;
				min_so_far = min_till_here;
			}
		}

		best = min_so_far;

		if (print) {
			System.out.println("min value is " + min_so_far);
			System.out.println("min start is --> " + start + " and min end is --> " + end);
		}

		return best;
	}
}
