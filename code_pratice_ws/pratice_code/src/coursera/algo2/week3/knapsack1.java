package coursera.algo2.week3;

import common.util.InputReader;
import java.util.Arrays;

public class knapsack1 {

	private int[] values;
	private int[] weights;

	private int knapsackWeight;
	private int totalItems;

	private int[][] DP;

	public int getMaxValue(InputReader in) {

		knapsackWeight = in.nextInt();
		totalItems = in.nextInt();

		values = new int[totalItems];
		weights = new int[totalItems];

		for (int i = 0; i < totalItems; i++) {
			values[i] = in.nextInt();
			weights[i] = in.nextInt();
		}

//		return runKnapSack();
		return runKnapSackRecurse();
	}

	private int runKnapSack() {
		DP = new int[totalItems][knapsackWeight+1];

		// init, no elements means no values.
		Arrays.fill(DP[0], 0);

		for (int i = 0; i < totalItems; i++) {
			for (int j = 0; j <= knapsackWeight; j++) {
				if (i==0)
					DP[i][j] = (j>=weights[i] ? values[i] : 0);
 				else if (j - weights[i] < 0)
					DP[i][j] = DP[i-1][j];
				else
					DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-weights[i]] + values[i]);
			}
		}

		return DP[totalItems-1][knapsackWeight];
	}


	private int runKnapSackRecurse() {
		DP = new int[totalItems][knapsackWeight+1];

		for (int i = 0; i < totalItems; i++) {
			Arrays.fill(DP[i], -1);
		}

		return runKnapSackRecurse(knapsackWeight, totalItems-1);
	}

	private int runKnapSackRecurse(int weightRem, int node) {
		if (weightRem <= 0 || node < 0)
			return 0;

		if (DP[node][weightRem] >= 0)
			return DP[node][weightRem];

		int val1 = 0;
		// either we choose this item in the sack
		if (weightRem > weights[node])
			val1 = runKnapSackRecurse(weightRem - weights[node], node-1) + values[node];
		// or we don't choose this item in the sack.
		int val2 = runKnapSackRecurse(weightRem, node-1);

		int maxVal = Math.max(val1, val2);
		DP[node][weightRem] = maxVal;
		return maxVal;
	}
}
