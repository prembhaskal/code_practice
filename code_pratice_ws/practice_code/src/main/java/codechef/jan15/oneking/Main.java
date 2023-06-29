package codechef.jan15.oneking;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
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

	private int N;
	private Segment[] segments;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();

			segments = new Segment[N];

			for (int j = 0; j < N; j++) {
				int a = in.nextInt();
				int b = in.nextInt();

				segments[j] = new Segment(a, b);
			}


			out.println(getMinBombs());
		}

	}

	// go greedy ... just like max scheduling.... works like charm.
	private int getMinBombs() {
		Arrays.sort(segments);

		int bombs = 0;
		int lastBombAt = -1; // keeping bomb at end of segment.

		for (int i = 0; i < N; i++) {
			Segment current = segments[i];
			int start = current.a;
			int end = current.b;

			if (start <= lastBombAt) { // skipping intersecting segments.
				continue;
			}
			else {
				bombs++;
				lastBombAt = end;
			}
		}

		return bombs;
	}


	private class Segment implements Comparable<Segment> {
		int a;
		int b;

		Segment(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Segment other) {
			// ending first.
			// break tie, starting first.
			if (this.b < other.b) return -1;
			if (this.b > other.b) return 1;

			if (this.a < other.a) return -1;
			if (this.a > other.a) return 1;
			return 0;
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
