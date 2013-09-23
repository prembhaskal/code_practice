package coursera.algo2.week3;

import common.util.InputReader;

public class KnapSack2 {

	int [] values;
	int [] weights;

	int totalItems;
	int knapsackWeight;

	public int getMaxValues(InputReader in) {
		knapsackWeight = in.nextInt();
		totalItems = in.nextInt();

		values = new int[totalItems];
		weights = new int[totalItems];

		for (int i = 0; i < totalItems; i++) {
			values[i] = in.nextInt();
			weights[i] = in.nextInt();
		}
		return runKnapSackStupidWay();
	}

	private int runKnapSackStupidWay() {
		int[] previousValues = new int[knapsackWeight+1];
		int[] presentValues = new int[knapsackWeight+1];

		for (int i = 0; i < totalItems; i++) {
			for (int j = 0; j <= knapsackWeight; j++) {
				if (i == 0) {
					presentValues[j] = (j >= weights[i]) ? values[i] : 0;
				}
				 else if (j < weights[i]) {
					presentValues[j] = previousValues[j];
				} else {
					presentValues[j] = Math.max(previousValues[j - weights[i]] + values[i],
												previousValues[j]);
				}
			}

			previousValues = presentValues;
			presentValues = new int[knapsackWeight+1];
		}

		return previousValues[knapsackWeight];
	}
}
