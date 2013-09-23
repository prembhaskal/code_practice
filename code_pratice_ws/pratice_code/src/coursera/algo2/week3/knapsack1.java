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

		return runKnapSack();
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
}
