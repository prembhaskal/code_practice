package codechef.november13.johnny;

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

	private int totalSongs;
	private int[] lengths;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			totalSongs = in.nextInt();
			lengths = new int[totalSongs];

			for (int j = 0; j < totalSongs; j++) {
				lengths[j] = in.nextInt();
			}

			int origPos = in.nextInt();

			int newPos = findNewPosition(origPos);

			out.println(newPos);
		}

	}

	private int findNewPosition(int origPos) {
		int origLength = lengths[origPos-1];// input is 1 based

		// find all numbers less than this length
		int totalLess = 0;
		for (int i = 0; i < totalSongs; i++) {
			if (lengths[i] < origLength)
				totalLess++;
		}

		return totalLess+1;
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
