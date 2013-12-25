package spoj.set1.nsteps;

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
			int x = in.nextInt();
			int y = in.nextInt();

			String getNumber = getNumber(x, y);
			out.println(getNumber);
		}

	}

	// TODO simplify the logic.
	private String getNumber(int x, int y) {
		String number;
		if (x == y) {
			if ((x&1) == 1) {
				int num = 4 * (x-1)/2 + 1;
				number = num + "";
			}
			else {
				number = 4 * x/2 + "";
			}
		}
		else if (x == y + 2) {
			if ((y&1) == 1) {
				int num = 2 + 4 * (y-1)/2 + 1;
				number = num + "";
			}
			else {
				int num = 2 + 4 * y/2;
				number = num + "";
			}
		}
		else {
			number = "No Number";
		}

		return number;
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
