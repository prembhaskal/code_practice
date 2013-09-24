package coursera.algo2.week3;

import common.util.InputReader;
import java.util.HashMap;

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
//		return runKnapSackStupidWay();
		return runKnapSackSingleArray();
//		return runKnapSackRecurse();
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

	private int runKnapSackSingleArray() {
		int[] knapSackValues = new int[knapsackWeight+1];

		for (int i = 0; i < totalItems; i++) {
			for (int j = knapsackWeight; j >= 0; j--) {
				if (i==0) {
					knapSackValues[j] = (j >= weights[i]) ? values[i] : 0;
				}
				else if (j < weights[i]) {
					// do nothing as same array
				} else {
					knapSackValues[j] = Math.max(knapSackValues[j-weights[i]] + values[i],
												knapSackValues[j]);
				}
			}
		}

		return knapSackValues[knapsackWeight];
	}

	HashMap<KnapSackPair, Integer> dp;
	private int runKnapSackRecurse() {
		 dp = new HashMap<>();
		return runKnapSackRecurse(knapsackWeight, totalItems-1);
	}

	private int runKnapSackRecurse(int weightRem, int node) {
		if (weightRem < 0 || node < 0)
			return 0;

		KnapSackPair knapSackPair = new KnapSackPair(node, weightRem);
		if (dp.containsKey(knapSackPair))
			return dp.get(knapSackPair);

		int val1 = 0;
		if (weightRem > weights[node])
			val1 = runKnapSackRecurse(weightRem - weights[node], node-1) + values[node];
		int val2 = runKnapSackRecurse(weightRem, node-1);

		int maxVal = Math.max(val1, val2);

		dp.put(knapSackPair, maxVal);

		return maxVal;
	}

	private class KnapSackPair {
		int node;
		int weight;

		public KnapSackPair(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			KnapSackPair that = (KnapSackPair) o;

			if (node != that.node) return false;
			if (weight != that.weight) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = node;
			result = 31 * result + weight;
			return result;
		}
	}
}
