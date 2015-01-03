package codechef.archives.august13.split_candles;

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
			long n = in.nextLong();
			long k = in.nextLong();
			long[] splits = getSplitCandles(n, k);
			out.println(splits[0] + " " + splits[1]);
		}

	}

	private long[] getSplitCandles(long n, long k) {
		long[] splits = new long[2];

		if (n==0) {
			splits[0] = 0;
			splits[1] = 0;
		} else if (k==0) {
			splits[0] = 0;
			splits[1] = n;
		} else if (n < k) {
			splits[0] = 0;
			splits[1] = n;
		} else {
			splits[0] = n/k;
			splits[1] = n%k;
		}

		return splits;
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
