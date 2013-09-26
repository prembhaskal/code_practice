package coursera.algo2.week3;

import common.util.InputReader;

public class OptimalBinaryStructure {

	private double [][] DP;

	public double getOptimalSearchTime(InputReader in) {
		int nodes = in.nextInt();
		double [] weights = new double[nodes];

		for (int i = 0; i < nodes; i++) {
			weights[i] = in.nextDouble();
		}

		DP = new double[nodes][nodes];

		for (int sum = 0; sum < nodes; sum++) {
			for (int i = 0; i < nodes; i++) {

				// calculate constant
				double probSum = 0;
				for (int root = i; root < i; root++) {
					probSum += weights[root];
				}

				double minVal = probSum;

				for (int root = i; root < i; root++) {
					double newVal = probSum + getDP(i, root-1) + getDP(root+1, i + sum);
					minVal = Math.min(newVal, minVal);
					DP[i][i + sum] = minVal;
				}
			}
		}

		return DP[0][nodes-1];
	}

	private double getDP(int i, int j) {
		if (i>=j)
			return 0;
		return DP[i][j];
	}
}
