package codechef.april14.cnpiim;

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
			int trace = in.nextInt();

			long matricesCount = getPossibleMatrices(trace);
			out.println(matricesCount);
		}

	}

	/*
	say matrix is a1,a2 /n a3, a4
	then trace = a1+a4 and D = a1*a4 - a2*a3;
	now count of matrices = no_of_ways_to_get (a2*a3 < a1*a4)  (*2 if a1!=a4)
	i.e do this for all possible combination of a1 & a4
	 */
	// THIS GOT ACCEPTED.
	private long getPossibleMatrices(int trace) {
		long matricesSum = 0;
		for (int i = 1; i < trace; i++) {
			int a1 = i;
			int a4 = trace - i;

			int prod = a1 * a4;
			int divSum = findDivisorSummatory(prod - 1);

			matricesSum = matricesSum + divSum;
		}

		return matricesSum;
	}

	// http://en.wikipedia.org/wiki/Divisor_summatory_function
	// it return sums over divisor function, Sum(x=1 to n) (factor_count(x))
	private int findDivisorSummatory(int n)  {
		int root = (int) Math.sqrt(n);

		int sum = 0;
		for (int i = 1; i <= root; i++) {
			sum = sum + (n/i);
		}

		sum = (sum + sum) - (root*root);

		return sum;
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
