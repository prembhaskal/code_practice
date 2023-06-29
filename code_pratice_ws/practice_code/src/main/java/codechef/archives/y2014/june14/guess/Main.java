package codechef.june14.guess;

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
			int N = in.nextInt();
			int M = in.nextInt();

			int n_odd = (N + 1)/2;
			int n_even = N - n_odd;

			int m_odd = (M + 1)/2;
			int m_even = M - m_odd;

			// odd probability
			// n_odd && m_even || n_even && m_odd

			long probNum = n_odd * (long)m_even + n_even * (long)m_odd;
			long probDenom = N * (long)M;

			long gcd = getGCD(probNum, probDenom);

			probNum = probNum / gcd;
			probDenom = probDenom / gcd;

			out.println(probNum + "/" + probDenom);
		}

	}

	private long getGCD(long a, long b) {
		long num = a;
		long denom = b;
		while (true) {
			long res = num % denom;
			if (res == 0)
				return denom;
			num = denom;
			denom = res;
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
