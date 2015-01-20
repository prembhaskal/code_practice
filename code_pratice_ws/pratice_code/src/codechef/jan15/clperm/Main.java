package codechef.jan15.clperm;

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

	private int[] missing;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			K = in.nextInt();

			missing = new int[K];

			for (int j = 0; j < K; j++) {
				missing[j] = in.nextInt();
			}

			if ( K == 0) {
				if (isMomFirstKZero()) {
					out.println("Mom");
				}
				else {
					out.println("Chef");
				}
			}
			else {
				out.println("Chef");
			}
		}

	}

	private boolean isMomFirstKZero() {
		long totalSum = (long) N * (N+1);
		totalSum = totalSum / 2;

		if (totalSum % 2 == 0) { // even ni.
			return false;
		}
		else {
			return true;
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
