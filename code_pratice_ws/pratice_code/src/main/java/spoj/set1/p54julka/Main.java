package spoj.set1.p54julka;

import java.io.*;
import java.math.BigInteger;
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

		int tests = 10;

		for (int i=0;i<tests;i++) {
			BigInteger total = new BigInteger(in.next());
			BigInteger excess = new BigInteger(in.next());

			BigInteger klaudia = (total.add(excess)).divide(BigInteger.valueOf(2));
			BigInteger natalia = total.subtract(klaudia);

			out.println(klaudia);
			out.println(natalia);
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
