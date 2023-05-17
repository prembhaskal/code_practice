package spoj.set1.p4300aeoo;

import java.io.*;
import java.util.Set;
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

	private int limit = 10000;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int n = in.nextInt();
		int distinctRects = getDistinctMultiples(n);
		out.println(distinctRects);
	}

	// approach using divisor sum function,  though normal factors finding would have worked fine too.
	private int getDistinctMultiples(int n) {
		int divisorsSum = getDivisorsSum(n);
		int perfectSquares = getPerfectSquaresTill(n);
		return (divisorsSum + perfectSquares)/2;
	}

	private int getPerfectSquaresTill(int n) {
		int total = 0;
		for (int i = 1; i * i <= limit; i++) {
			int square = i * i;
			if (square > n)
				break;
			total++;
		}

		return total;
	}

	// refer wiki divisor summatory function.
	private int getDivisorsSum(int n) {
		int root = (int) Math.sqrt(n);
		int sum = 0;
		for (int i = 1; i <= root; i++) {
			sum += (n/i);
		}

		return sum + sum - root*root;
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
