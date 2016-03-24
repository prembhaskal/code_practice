package codechef.may14.rrstone;

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
	private int K;
	private long[] nums;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		N = in.nextInt();
		K = in.nextInt();

		nums = new long[N];

		for (int i = 0; i < N; i++) {
			nums[i] = in.nextInt();
		}

		getAfterK();

		for (int i = 0; i < N; i++) {
			out.print(nums[i]);
			out.print(" ");
		}

	}

	private void getAfterK() {
		if (K == 0) {
			return;
		}

		K = K % 2;

		long max = Integer.MIN_VALUE;
		long min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, nums[i]);
			min = Math.min(min, nums[i]);
		}

		if (K == 1) {
			for (int i = 0; i < N; i++) {
				nums[i] = max - nums[i];
			}
		}
		else {
			for (int i = 0; i < N; i++) {
				nums[i] = nums[i] - min;
			}
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
