package codechef.september14.rainbowb;

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

	private int MOD = 1000_000_007;

	private int[] facts = new int[1000_000];

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int N = in.nextInt();
		initFacts();
		int total = getDifferentArrays(N);
		out.println(total);

	}

	// solving using different solutions for linear equations.
	// see http://www.ping.be/math/tel.htm#Properties-of-combin last problem for more detailts.
	// combination with repetition.
	private int getDifferentArrays(int N) {

		if (N < 13)
			return 0;
		if (N < 15)
			return 1;

		// 2*sum(a1-a6) + a7 = N;

		int M = (N-1)/2; // since a7 > 1.

		// sum(a1-6) <= M;
		// non-negative solutions for sum(a1-6) +extra = M - 6;
		// eqn. a1+a2+...+a6+extra < d

		int d = M - 6;
		int choices = 7;

		// combination with repetition (n+k-1)/(k)
		// d+choices-1 C d ==> d+choices-1 C choices-1
		int n = d + choices - 1;
		int r = 6;

		int total = getNCR(n, r);
		return total;
	}

	private int getNCR(int n, int r) {
		if (r == 0)
			return 1;
		if (r == 1)
			return n;

		long num = ((long)facts[n] * getModInverse(facts[r])) % MOD;
		num = (num * getModInverse(facts[n-r])) % MOD;
		return (int) num;
	}

	private int initFacts() {
		int fact = 1;

		for (int i = 1; i < facts.length; i++) {
			fact = (int) (((long)fact * i) % MOD);
			facts[i] = fact;
		}

		return fact;
	}

	private int getModInverse(int n) {
		return powMod(n, MOD - 2);
	}

	private int powMod(long n, long p) {
		long pow = 1;

		while (p > 0) {
			if ((p&1)==1) {
				pow = (pow * n) % MOD;
			}

			p /= 2;

			n = (n * n) % MOD;
		}

		return (int) pow;
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
