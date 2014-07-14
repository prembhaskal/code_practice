package codechef.july14.dishown;

import java.io.*;
import java.util.InputMismatchException;
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

// EPIC, union find of Objects, with hierarchical leaders.
class TaskA {

	private int N;
	private int[] scores;

	private LeaderNode[] leaders;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt() + 1; // 1 based index

			// read scores
			scores = new int[N];
			for (int  j= 1; j < N; j++) {
				scores[j] = in.nextInt();
			}

			// init leaders;
			leaders = new LeaderNode[N];
			for (int j = 1; j < N; j++) {
				leaders[j] = new LeaderNode();
				leaders[j].id = j;
			}

			int Q = in.nextInt();

			for (int j = 0; j < Q; j++) {
				int f = in.nextInt();
				if (f == 0) {
					int dishA = in.nextInt();
					int dishB = in.nextInt();

					compete(dishA, dishB, out);
				}
				else {
					int dish = in.nextInt();
					int owner = getOwner(dish).id;

					out.println(owner);
				}
			}
		}

	}

	private void compete(int dishA, int dishB, PrintWriter out) {
		LeaderNode leaderA = getOwner(dishA);
		LeaderNode leaderB = getOwner(dishB);

		if (leaderA.id == leaderB.id) {
			out.println("Invalid query!");
			return;
		}

		int winnerId;

		if (scores[leaderA.id] > scores[leaderB.id]) {
			winnerId = leaderA.id;
		}
		else if (scores[leaderA.id] < scores[leaderB.id]) {
			winnerId = leaderB.id;
		}
		else {
			return; // do nothing for a tie.
		}

		merge(leaderA, leaderB, winnerId);
	}

	private void merge(LeaderNode a, LeaderNode b, int winnerId) {
		if (a.size > b.size) {
			b.leader = a;
			a.id = winnerId; // this is where it is different from a usual union find.
			a.size += b.size;
		}
		else {
			a.leader = b;
			b.id = winnerId;
			b.size += a.size;
		}
	}

	private LeaderNode getOwner(int dish) {
		LeaderNode leaderNode = leaders[dish];

		while (leaderNode.leader != null) {
			leaderNode = leaderNode.leader;
		}

		return leaderNode;
	}

	private class LeaderNode {
		int id;
		int size = 1;
		LeaderNode leader;
	}


}

class InputReader {

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public InputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int nextInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			if (Character.isValidCodePoint(c))
				res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return isWhitespace(c);
	}

	public static boolean isWhitespace(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public String next() {
		return readString();
	}

	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}