package codechef.july14.retpo;

import java.io.*;
import java.util.InputMismatchException;

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

	public void solve(FastInputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			int x = in.nextInt();
			int y = in.nextInt();

			x = Math.abs(x);
			y = Math.abs(y);

			int moves = 0;
			if (x > y) {
				moves = y * 2;
				int diff = x - y;
				moves = moves + (diff/2) * 4;
				if (diff % 2 == 1)
					moves += 3;
			}
			else {
				moves = x * 2;
				int diff = y - x;
				moves = moves + (diff/2) * 4;
				if (diff % 2 == 1)
					moves += 1;
			}

			out.println(moves);
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