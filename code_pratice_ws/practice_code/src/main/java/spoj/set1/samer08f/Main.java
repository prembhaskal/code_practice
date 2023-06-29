package spoj.set1.samer08f;

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

		while (true) {
			int num = in.nextInt();
			if (num == 0)
				break;

			int squares  = getSquares(num);
			out.println(squares);
		}

	}
	// if side is 5, start with square of size=1 --> (5*5)
	// square of size = 2 --> (4*4)
	// square of size = 3 --> (3*3)
	private int getSquares(int num) {
		int squares = (num) * (num + 1) * (2*num + 1);
		squares /= 6;
		return squares;
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
