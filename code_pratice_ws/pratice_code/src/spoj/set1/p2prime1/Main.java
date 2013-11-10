package spoj.set1.p2prime1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	private List<Integer> primes = new ArrayList<Integer>();

	public void solve(InputReader in, PrintWriter out) throws IOException {

		initPrimes(100000);

//		out.println("total primes in the 100000 are " + primes.size());

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			int lower = in.nextInt();
			int upper = in.nextInt();

			List<Integer> primesInRange = generatePrimesInRange(lower, upper);
			printPrimesInRange(primesInRange, out);
			out.println();
		}

	}

	private void printPrimesInRange(List<Integer> primesInRange, PrintWriter out) {
		for (int prime : primesInRange)
			out.println(prime);
	}

	private void initPrimes(int range) {
		int sqrt = (int) Math.sqrt(range) + 1;

		boolean[] marked = new boolean[range + 1];
		Arrays.fill(marked, true);

		marked[0] = false;
		marked[1] = false;

		marked[2] = true;

		// sieve
		for (int i = 2; i < sqrt; i++) {
			for (int j = i*i; j <= range; j += i) {
				marked[j] = false;
			}
		}

		primes.add(2);
		for (int i = 3; i < marked.length; i+=2) {
			if (marked[i])
				primes.add(i);
		}
	}

	private List<Integer> generatePrimesInRange(int lower, int upper) {
		int sqrt = (int) Math.sqrt(upper) + 1;

		boolean[] marked = new boolean[upper - lower + 1];
		Arrays.fill(marked, true);

		for (int prime : primes) {
			if (prime > sqrt)
				break; // we have so many initially generated primes.

			int start = lower;
			// find lowest number > start such that it is divisible by rem.
			int rem = start % prime;
			if (rem != 0) {
				start = lower + prime - rem;
			}

			if (prime == start)
				start += prime;

			for ( ; start <= upper; start+= prime) {
				marked[start - lower] = false;
			}
		}

		List<Integer> primesInRange = new ArrayList<Integer>();

		for (int i = 0; i < marked.length; i++) {
			if (marked[i]) {
				int num = lower + i;
				if (num > 1)
					primesInRange.add(num);
			}
		}

		return primesInRange;
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
