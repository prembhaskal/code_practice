package codechef.nov14.fncs;

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
	private long[] nums;
	private int[][] funcs;

	private long[] sums;

	private long[] funcSum;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		N = in.nextInt();

		if (N > 10000) // added since using brute force to get some minimum marks.
			return;

		nums = new long[N];
		for (int i = 0; i < N; i++) {
			nums[i] = in.nextInt();
		}

		funcs = new int[N][2];
		for (int i = 0; i < N; i++) {
			funcs[i][0] = in.nextInt();
			funcs[i][1] = in.nextInt();
		}

		init();

		int queries = in.nextInt();

		for (int i = 0; i < queries; i++) {
			int type = in.nextInt();
			int x = in.nextInt();
			int y = in.nextInt();

			if (type == 1) {
				updateNum(x, y);
			}
			else {
				long total = getSum(x, y);
				out.println(total);
			}
		}

	}

	private void updateNum(int index, int newNum) {
		long oldNum = nums[index - 1];
		nums[index - 1] = newNum; // updating the actual array.

		// update only the function sums.
		for (int i = 0; i < N; i++) {
			int left = funcs[i][0];
			int right = funcs[i][1];

			if (left <= index && right >= index) {
				funcSum[i] = funcSum[i] - oldNum;
				funcSum[i] = funcSum[i] + newNum;
			}
		}
	}

	private long getSum(int from, int to) {
		from--;to--;

		long totalSum  = 0;
		for (int i = from; i <= to; i++) {
			totalSum += funcSum[i];
		}

		return totalSum;
	}

	private void init() {
		sums = new long[N];
		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum = sum + nums[i];
			sums[i] = sum;
		}

		funcSum = new long[N];
		// function sum
		for (int i = 0; i < N; i++) {
		// function sum = sums[R] - sums[L] + nums[L];
			funcSum[i] = sums[funcs[i][1] - 1] - sums[funcs[i][0] - 1] + nums[funcs[i][0] - 1];
		}
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
