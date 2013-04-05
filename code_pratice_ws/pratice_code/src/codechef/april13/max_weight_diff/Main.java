package codechef.april13.max_weight_diff;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskA solution = new TaskA();
		solution.solve(in,writer);

		writer.close();
		in.close();
	}

}

class TaskA {

	int[] weights;
	int k;
	int n;

	public void solve(Scanner in, PrintWriter out) {
		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			n = in.nextInt();
			k = in.nextInt();
			weights = new int[n];

			for (int j=0;j<n;j++) {
				weights[j] = in.nextInt();
			}

			int maxWeightDiff = getMaximumWeightedDifference();
			out.println(maxWeightDiff);

		}

	}

	private int getMaximumWeightedDifference() {

		Arrays.sort(weights);

		int weight1 = 0;
		int weight2 = 0;

		int min = Math.min(k, n-k);

		for (int i=0;i<min;i++) {
			weight1 += weights[i];
		}

		for (int i=min;i<n;i++) {
			weight2 += weights[i];
		}

		return Math.abs(weight2 - weight1);
	}


}

