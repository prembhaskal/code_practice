package algorithm.chap4;

public class MaxSubArrayProblem {

	// input --> array storing the difference from previous num
	public void getMaxSubArrayBruteForce(int[] num) {

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
	}

	public void getMaxSubArrayLinear(int[] num) {
		int start = -1;
		int end = -1;
		int best = Integer.MIN_VALUE;

		// get first the max sub array for 1 to size.
		int sum = 0;
		start = 0;
		for (int i = 0; i < num.length; i++) {
			sum += num[i];

			if (sum > best) {
				best = sum;
				end = i;
			}
		}

		sum = best;
		for (int i=0;i<end;i++) {
			sum = sum - num[i];
			if (sum > best) {
				best = sum;
				start = i+1;
			}
		}


		for (int i=end+1;i<num.length;i++) {
			sum = sum + num[i];

			if (sum > best) {
				best = sum;
				end = i;
			}

			int sum1 = sum;
			for (int j=start;j<i;j++) {
				sum1 = sum1 - num[j];
				if (sum1 > best) {
					start = j+1;
					sum = best = sum1;
					end = i;
				}
			}
		}

		System.out.println("max value is " + best);
		System.out.println("start is " + start + " and end is " + end);
	}
}
