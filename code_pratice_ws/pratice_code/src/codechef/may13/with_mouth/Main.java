package codechef.may13.with_mouth;

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

	// this certainty is enough for the range required in question
	static int certainty = 25;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			long num = in.nextLong();

			long result = getMaxTotientFunction(num);

			out.println(result);
		}

	}

	// max totient will be for immediate previous prime.
	private long getMaxTotientFunction(long num) {

		if (num==2L)
			return num;

		while (true) {
			boolean isPrime = isPrime(num);
			if (isPrime) {
				break;
			}

			num--;
		}


		return num;

	}

	private boolean isPrime(long prime) {
		certainty = Math.max(10,certainty);

		BigInteger number = BigInteger.valueOf(prime);
		boolean isPrime = number.isProbablePrime(certainty);
		return isPrime;
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
