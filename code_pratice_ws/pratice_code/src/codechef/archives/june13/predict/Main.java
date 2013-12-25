package codechef.archives.june13.predict;

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

	double total = 10000.00d;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			double px = in.nextDouble();
			double exp = getMaxExpectedValue(px);

			out.println(exp);
		}

	}

// expected value is (val)(2x-1) - (2x-1)(10000)(x)
	private double getMaxExpectedValue(double x) {
		double v1;
		if (x > 0.5) {
			v1 = total;
		} else {
			v1 = 0;
		}

		double inter = ( 2 * x - 1.0);
		double prod1 = v1 * inter;

		double prod2 = inter * total * x;

		double expectedValue = prod1 - prod2 + total;

		return expectedValue;
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
