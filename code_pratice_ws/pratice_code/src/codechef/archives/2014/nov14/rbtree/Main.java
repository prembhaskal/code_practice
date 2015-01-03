package codechef.nov14.rbtree;

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

// done... little tricky but doable..easy only by chef standards.
class TaskA {

	private boolean blackAtHead = true;
	private boolean blacksAtStart;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			String query = in.next();
			char ch = query.charAt(1);
			if (ch == 'i') {
				blackAtHead = !blackAtHead;
			}
			else if (ch == 'r') {
				int x = in.nextInt();
				int y = in.nextInt();
				int reds = getReds(x, y);
				out.println(reds);
			}
			else {
				int x = in.nextInt();
				int y = in.nextInt();
				int blacks = getBlacks(x, y);
				out.println(blacks);
			}
		}

	}

	private int getReds(int x, int y) {
		int gap = getGap(x, y);

		int totalBalls = gap + 1;
		int reds = totalBalls >> 1; // in case of even balls, both are equal
		if ((totalBalls&1) == 1 && !blacksAtStart) // if odd balls, only then it matters where we start.
			return reds + 1;
		return reds;
	}

	private int getBlacks(int x, int y) {
		int gap = getGap(x, y);

		int totalBalls = gap + 1;
		int blacks = totalBalls >> 1;
		if ((totalBalls&1) == 1 && blacksAtStart)
			return blacks + 1;
		else
			return blacks;
	}


	private int getGap(int x, int y) {
		// find the level... first level is odd.
		int level = 0;
		int copy = x;
		while (x != 0) {
			x = x >> 1;
			level++;
		}

		if ((level & 1) == 0) { // even level
			blacksAtStart = !blackAtHead;
		}
		else {
			blacksAtStart = blackAtHead;
		}

		x = copy;
		int gap = 0;
		while (x != y) {
			if (x < y) {
				y = y >> 1;
			}
			else {
				x = x >> 1;
			}
			gap++;
		}
		return gap;
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
