package codechef.august14.prgift;

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
			int n = in.nextInt();
			int k = in.nextInt();
			int[] nums = new int[n];

			for (int j = 0; j < n; j++) {
				nums[j] = in.nextInt();
			}

			int even = 0;
			// check the number of even nos.
			for (int j = 0; j < n; j++) {
				if ((nums[j]&1) == 0)
					even++;
			}


			if (isPossible(n, k, even)) {
				out.println("YES");
			}
			else {
				out.println("NO");
			}
		}

	}

	private boolean isPossible(int n, int k, int even) {
		if (k == 0 && even == n)
			return false;

		return k <= even;
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
