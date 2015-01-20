package codechef.jan15.chefston;

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

	private int N;
	private int freeTime;

	private int[] times;
	private int[] profits;


	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			freeTime = in.nextInt();

			times = new int[N];
			for (int j = 0; j < N; j++) {
				times[j] = in.nextInt();
			}

			profits = new int[N];
			for (int j = 0; j < N; j++) {
				profits[j] = in.nextInt();
			}

			out.println(getMaxProfit());
		}

	}

	private long getMaxProfit() {
		long maxProfit = -1;

		for (int i = 0; i < N; i++) {
			int totalStones = freeTime / times[i];
			long totalProfit = (long)totalStones * profits[i];

			maxProfit = Math.max(maxProfit, totalProfit);
		}

		return maxProfit;
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
