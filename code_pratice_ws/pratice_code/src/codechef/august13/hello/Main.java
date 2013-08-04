package codechef.august13.hello;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			TaskA solution = new TaskA();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class TaskA {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			double baseRate = in.nextDouble();
			int talktime = in.nextInt();
			int plans = in.nextInt();

			int[] validity = new int[plans];
			double[] callingRate = new double[plans];
			int[] planCost = new int[plans];

			for (int j = 0; j < plans; j++) {
				validity[j] = in.nextInt();
				callingRate[j] = in.nextDouble();
				planCost[j] = in.nextInt();
			}

			double baseCost = baseRate * talktime;

			int bestPlan = getPlanWithBestRate(baseCost, talktime, validity, callingRate, planCost);
			bestPlan++;

			out.println(bestPlan);
		}

	}

	private int getPlanWithBestRate(double baseCost, int talktime, int[] validity, double[] callingRate, int[] planCost) {
		int bestPlanIndex = -1;

		for (int i = 0; i < validity.length; i++) {
			// find cost for this plan
			double costPerMonth = ((double)planCost[i])/validity[i];
			double talkTimeCost = talktime * callingRate[i];
			double totalCost = talkTimeCost + costPerMonth;

			if (totalCost < baseCost) {
				bestPlanIndex= i;
				baseCost = totalCost;
			}
		}

		return bestPlanIndex;
	}


}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

}
