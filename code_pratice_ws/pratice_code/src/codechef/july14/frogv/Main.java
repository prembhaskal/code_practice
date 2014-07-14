package codechef.july14.frogv;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			FastInputReader in = new FastInputReader(inputStream);
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
	private int K;
	private int P;

	private int[] nums;

	private Frog[] frogs;

	private int[] leaders;

	public void solve(FastInputReader in, PrintWriter out) throws IOException {
		N = in.nextInt();
		K = in.nextInt();
		P = in.nextInt();

		nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = in.nextInt();
		}

		frogs = new Frog[N];
		for (int i = 0; i < N; i++) {
			frogs[i] = new Frog();
			frogs[i].id = i;
			frogs[i].distance = nums[i];
		}

		createUnionFind();

		for (int i = 0; i < P; i++) {
			int id1 = in.nextInt() - 1;
			int id2 = in.nextInt() - 1;

			if (leaders[id1] == leaders[id2])
				out.println("Yes");
			else
				out.println("No");
		}
	}

	private void createUnionFind() {
		Arrays.sort(frogs);
		leaders = new int[N];

		int prevDist = frogs[0].distance;
		int prevLeader = frogs[0].id;
		leaders[frogs[0].id] = prevLeader;

		for (int i = 1; i < N; i++) {
			if ((frogs[i].distance - prevDist) <= K) {
				leaders[frogs[i].id] = prevLeader;
			}
			else {
				prevLeader = frogs[i].id;
				leaders[frogs[i].id] = prevLeader;
			}

			prevDist = frogs[i].distance;
		}
	}

	private class Frog implements Comparable<Frog>{
		int id;
		int distance;


		@Override
		public int compareTo(Frog o) {
			if (distance < o.distance) return -1;
			if (distance > o.distance) return 1;
			return 0;
		}
	}


}
class FastInputReader {

	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;

	public FastInputReader(InputStream stream) {
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