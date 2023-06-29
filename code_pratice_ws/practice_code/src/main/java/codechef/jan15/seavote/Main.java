package codechef.jan15.seavote;

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
	private int[] nums;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();

			nums = new int[N];

			for (int j = 0; j < N; j++) {
				nums[j] = in.nextInt();
			}

			if (isPossible()) {
				out.println("YES");
			}
			else {
				out.println("NO");
			}
		}

	}

	private boolean isPossible() {
		int totalSum = 0;

		for (int i = 0; i < N; i++) {
			totalSum += nums[i];
		}

		if (totalSum < 100)
			return false;

		if (totalSum == 100)
			return true;

		int asum = 0;
		for (int i = 0; i < N; i++) {
			if (nums[i] > 0)
				asum += (nums[i] - 1);
		}

		if (asum < 100)
			return true;

		return false;
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
