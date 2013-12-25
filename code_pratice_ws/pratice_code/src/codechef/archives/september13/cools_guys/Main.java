package codechef.archives.september13.cools_guys;

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

			int num = in.nextInt();
			int factors= findFactors(num);

			out.println(factors);
		}

		for (int i=0;i<1000_000;i++) {
			int factors = findFactors(1000_000_000);
		}

	}

	// trial division
	private int findFactors(int num) {
		int factors = 1;

		for (int i=2;i<=num;i++) {
			if (num %i==0) {
				int exp = 0;
				while (num%i==0) {
					num /= i;
					exp++;
				}

				factors = factors * (exp+1);
			}
		}

		if (num > 1)
			factors *= 2;

		return factors;

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
