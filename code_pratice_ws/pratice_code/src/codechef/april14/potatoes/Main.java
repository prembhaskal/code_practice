package codechef.april14.potatoes;

import java.io.*;
import java.util.Arrays;
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

	private boolean[] primes;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		initPrimes(10000);

		for (int i=0;i<tests;i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int z = findThirdNumber(x, y);
			out.println(z);
		}

	}
	
	private int findThirdNumber(int x, int y) {
		int sum = x + y;

		int i;
		for (i = 1; i < 1000_000; i++) {
			int total = sum + i;
			if (primes[total])
				break;
		}

		return i;
	}

	private void initPrimes(int range) {
		int root = (int) (Math.sqrt(range)) + 1;
		primes = new boolean[range+1];

		// mark all as prime.
		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;

		for (int i = 2; i < root; i++) {
			for (int j = i * i; j <= range ; j+= i) {
				primes[j] = false;
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
