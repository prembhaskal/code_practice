package spoj.set1.p1029matsum;

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

// this gets time out for some reason most probably due to large input.
class TaskA {

	private int[][] nos;
	private int N;

	private Bit2D bit2D;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			nos = new int[N][N];
			bit2D = new Bit2D(N);

			while (true) {
				String operation = in.next();
				if (operation.equals("SET")) {
					int x = in.nextInt();
					int y = in.nextInt();
					int num = in.nextInt();

					int diff = num - nos[x][y];
					bit2D.update2D(x, y, diff);
				}
				else if (operation.equals("SUM")) {
					int x1 = in.nextInt();
					int y1 = in.nextInt();
					int x2 = in.nextInt();
					int y2 = in.nextInt();

					int sum = bit2D.sum2D(y1, y2, x1, x2);
					out.println(sum);
				}
				else {
					break;
				}
			}
		}

	}


	// 2 dimension
	private class Bit2D {

		private int[][] nums;

		public Bit2D(int N) {
			nums = new int[N + 1][N + 1]; // bit is 1 indexed.
		}

		// methods to update/query the 2D bit
		public void update2D(int x, int y, int val) {
			y++;

			while (y < nums.length) {
				update(y, x, val);
				y += (y & -y);
			}
		}

		public int sum2D(int y1, int y2, int x1, int x2) {
			y1++; // 1 based index.
			y2++;

			int sum = sumTill2D(y2, x1, x2);
			sum -= sumTill2D(y1 - 1, x1, x2);

			return sum;
		}

		public int sumTill2D(int y, int x1, int x2) {
			int sum = 0;
			while (y > 0) {
				sum += getSum(y, x1, x2);
				y -= (y & -y);
			}

			return sum;
		}


		// methods to update/query the 1D bit.
		public void update(int indexOfBIT, int idx, int val) {
			idx++;

			int[] bit1D = nums[indexOfBIT];

			while (idx < bit1D.length) {
				bit1D[idx] += val;
				idx += (idx & -idx);
			}
		}

		public int getSum(int indexOfBIT, int low, int high) {
			low++; // 1 based index.
			high++;

			int sum = getSumTill(indexOfBIT, high);
			sum -= getSumTill(indexOfBIT, low - 1);

			return sum;
		}

		public int getSumTill(int indexOfBIT, int idx) {
			int[] bit1D = nums[indexOfBIT];
			int sum = 0;
			while (idx > 0) {
				sum += bit1D[idx];
				idx -= (idx & -idx);
			}

			return sum;
		}

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
	}}
