package codechef.august13.shiro;

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

	int levels;
	int[] flags;
	double[] probabilities;
	int total;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			levels = in.nextInt();
			flags = new int[levels];
			probabilities = new double[levels];

			for (int j = 0; j < levels; j++) {
				flags[j] = in.nextInt();
			}

			for (int j = 0; j < levels; j++) {
				probabilities[j] = in.nextInt();
				probabilities[j] = probabilities[j] * 0.01;
			}

			double probability = getProbability();
			out.println(probability);
		}

	}

	private double getProbability() {
		total = 0;

		for (int i = 0; i < levels; i++) {
			total += flags[i];
		}

		double probability = getProbabilities(0, 0, 1);
		return probability;
	}

// BRUTE FORCE
	private double getProbabilities(int level, int flagsTillNow, double prob) {
		// check in last level
		if (level == levels) {
			if (2 * flagsTillNow >= total) {
//				System.out.println("-->" + prob + "<--");
				return prob;
			} else {
				return 0;
			}
		}

		if (2 * flagsTillNow >= total) {
			return 1.0;
		}

		// assume we get proper flags in this level
		double probGet = getProbabilities(level + 1, flagsTillNow + flags[level], prob * probabilities[level]);

		double probNotGet = getProbabilities(level+1, flagsTillNow, prob * (1-probabilities[level]));

		return probGet + probNotGet;
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
