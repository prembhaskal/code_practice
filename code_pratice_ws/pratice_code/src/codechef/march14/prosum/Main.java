package codechef.march14.prosum;

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
	private int[] A;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();

			A = new int[N];
			for (int j = 0; j < N; j++) {
				A[j] = in.nextInt();
			}

			long pairs = findNumberOfPairs();
			out.println(pairs);
		}

	}

	private long findNumberOfPairs() {
		int exclude = 0; // exclude 1 and 0
		int twos = 0; // exclude 2 with 2's..

		for (int i = 0; i < N; i++) {
			if (A[i] < 2)
				exclude++;
			else if (A[i] == 2)
				twos++;
		}


		long actual = N - exclude - twos;

		long pairs = actual * (actual - 1);
		pairs /= 2;

		pairs = pairs + (actual * twos);

		return pairs;
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
