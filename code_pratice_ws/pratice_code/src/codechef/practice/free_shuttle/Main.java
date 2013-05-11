package codechef.practice.free_shuttle;

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

/**
 * try to solve this http://ww2.codechef.com/problems/SHUTTLE
 * both with/without euler totient function or whatever crap it is.
 *
 * The editorial has a strange way of getting euler's totient. find out what is it.
 * see below. quite interesting actually.
 */
class TaskA {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			int N = in.nextInt();

//			int result = getNosWithGCD1(N);

			int result = getEulersTotient(N);

			out.println(result);
		}

	}

	// this approach passes without problem.
	private int getNosWithGCD1(int N) {
		int count = 1;

		for (int i=2;i<N;i++) {
			int gcd = getGCD(i, N);

			if (gcd==1)
				count++;
		}

		return count;
	}

	// or get number k, where k<N so that gcd(k,N)=1
	private int getEulersTotientSlowWay(int N) {
		int count = 1; // shuttle no. 1 will always reach

		for (int num = 2; num < N; num++) {
			boolean shareDivisor = false;

			for (int i=2;i<=num/i;i++) {
				while (num%i==0) {
					if (N%i==0) {
						shareDivisor = true;
						break;
					}

					num /= i;
				}
			}

			if (num > 1) {
				if (N%num==0) {
					shareDivisor = true;
				}
			}


			if (!shareDivisor) {
				count++;
			}
		}

		return count;
	}


	/**
	 * euler's totient phi(N) is multiplicative ... phi(mn) = phi(m) phi(n)  if gcd(m,n)=1
	 *
	 * and phi(prime^k) = prime^k - prime^(k-1)
	 *
	 * so phi(N) = phi(p1^k1  * p2^k1 * ...)
	 *           = n (p1-1)/p1 * (p2-1)/p2 * ....
	 * This solution is much faster....
	 */
	private int getEulersTotient(int N) {
		int phi = N;

		for (int i=2;i<=N/i;i++) {
			if (N%i==0) {
				phi = (phi/i) * (i-1);

				while (N%i==0)
					N /= i;
			}
		}

		if (N > 1) {
			phi = (phi/N) * ( N-1);
		}

		return phi;
	}

	public int getGCD(int a, int b) {
		int num =b;
		int div = a;

		while (num > div) {
			int rem = num%div;
			if (rem==0)
				break;
			num = div;
			div = rem;
		}

		return div;
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
