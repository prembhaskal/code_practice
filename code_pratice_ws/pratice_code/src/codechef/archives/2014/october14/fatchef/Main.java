package codechef.october14.fatchef;

import java.io.*;
import java.util.*;

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
	private int M;

	private int[][] buckets;

	private final int MOD = 1000000009;//1000000009

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();

			M = in.nextInt();
			buckets = new int[N][2];

			// -1 means no buckets exist.
			for (int j = 0; j < N; j++) {
				buckets[j][0] = -1;
				buckets[j][1] = -1;
			}

			for (int j = 0; j < M; j++) {
				int color = in.next().toCharArray()[0];
				int idx = in.nextInt() - 1;

				buckets[idx][0] = color;
				buckets[idx][1] = 1; // 1 means color present.
			}

			int ways;
			ways = getDifferentColors2();
			out.println(ways);

//			ways = getDifferentColors1();
//			out.println(ways);

			if (ways < 0)
				throw new RuntimeException("Error in calculation");
		}

	}

	// way 1 ----color1 ----- color2
	// assume k ways till color1. then ways till color2 = k * (diff(color1-color2) * dist(color1-color2)).
	// TODO this is wrong solution, checkout the other fatchef2 solution. little amiguity in problem statement.
	private int getDifferentColors1() {

		if (M == 1)
			return 1;

		if (M == N)
			return 1;

		long ways1 = 1;

		int prevColor = -1;
		int prevIdx  = -1;

		// find first bucket from left.
		int i;
		for (i = 0; i < N; i++) {
			if (buckets[i][1] == 1)
				break;
		}

		prevColor = buckets[i][0];
		prevIdx = i;

		int leftBucketAt = i;

		i++; // start searching from next index.
		int bkts = 1; // one bucket already found.

		boolean isFirst = true;

		long middleWays = 1;

		for (; i < N; i++) {
			if (buckets[i][1] != 1) // only for planks with buckets.
				continue;


			bkts++;

			if (bkts == M && i==N-1) {
				break; // last bucket at rightmost corner will not contribute to any new combination.
			}

			int currColor = buckets[i][0];
			int currIdx = i;

			int distDiff = currIdx - prevIdx;

			if (currColor != prevColor)
				ways1 = (ways1 * distDiff);

			if (!isFirst) {
				if (currColor != prevColor)
					middleWays = (middleWays * distDiff);

				if (middleWays >= MOD) {
					middleWays = middleWays % MOD;
				}
			}

			isFirst = false;

			if (ways1 >= MOD) {
				ways1 = ways1 % MOD;
			}

			prevColor = currColor;
			prevIdx = currIdx;
		}



		// start searching from right.
		long ways2 = 1;
		i = N-1;

		for (;i >= 0; i--) {
			if (buckets[i][1] == 1) {
				break;
			}
		}

		prevIdx = i;
		prevColor = buckets[i][0];

		int rightBucketAt = i;

		i--; // start from next idx
		bkts = 1; // 1 bucket found already.


		for (; i >=0; i--) {
			if (buckets[i][1] != 1) {
				continue;
			}

			bkts++;
			if (bkts == M && i == 0) {
				break;  // last bucket at leftmost corner will not contribute to any new combination.
			}

			int currColor = buckets[i][0];
			int currIdx = i;

			int distDiff = prevIdx - currIdx;

			if (distDiff == 1)
				continue;

			if (currColor != prevColor) {
				ways2 = ways2 * distDiff;
			}

			if (ways2 >= MOD) {
				ways2 = ways2 % MOD;
			}

			prevColor = currColor;
			prevIdx = currIdx;
		}

		long totalWays = (ways1 + ways2) % MOD;

		if (leftBucketAt == 0 && rightBucketAt == N-1) {
			if (M == 2) {
				if (buckets[0][0] != buckets[N-1][0])
					return (int) totalWays;
				else
					return 1;
			}
			else {
				totalWays -= middleWays;
				if (totalWays < 0) {
					totalWays += MOD;
				}

				return (int) totalWays;
			}
		}
		else if (leftBucketAt == 0)
			return (int) ways1;
		else if (rightBucketAt == N-1)
			return (int) ways2;

		return (int) ways1; // if none of buckets are at boundary, return any of the value, both would be same.
	}

	// showing time limit exceed when doing the % MOD instead of multiply.
	private int getDifferentColors2() {

		// special stuff.
		if (M == 1)
			return 1;

		if (M == N)
			return 1;

		if (M == 2) {
			return getDifferentColorsM_2();
		}

		List<Integer> parts = new ArrayList<Integer>();

		int prevIdx = -1;
		int prevColor = -1;

//		if (buckets[0][1] == 1)
//			parts.add(1);

		for (int i = 0; i < N; i++) {
			if (buckets[i][1] != 1)
				continue;

			int currIdx = i;
			int currColor = buckets[i][0];

			if (currColor != prevColor) {
				int diff = currIdx - prevIdx;
//				if (diff > 1)
				parts.add(diff);
			}
//			else {
//				parts.add(1);
//			}

			prevIdx = currIdx;
			prevColor = currColor;
		}


		int partsLength = parts.size();

		// calculate middle part
		long middleWays = 1;
		// skip first two and last one.
		for (int i = 2; i < partsLength - 1; i++) {
			if (parts.get(i) == 1)
				continue;
			middleWays = middleWays * parts.get(i);

			if (middleWays > MOD) {
				middleWays -= MOD;

				if (middleWays > MOD)
					middleWays = middleWays % MOD;
			}

			if (middleWays > MOD) {
				throw new RuntimeException("MOD failed");
			}
		}

		if (buckets[0][1] == 1 && buckets[N-1][1] == 1) { // bucket at ends.
			long ways1 = (parts.get(1) * middleWays) % MOD;

			long ways2 = (middleWays * parts.get(partsLength - 1)) % MOD;

			long total = (ways1 + ways2 - middleWays + MOD) % MOD;

			return (int) total;
		}
		else {
			long total = (parts.get(1) * middleWays) % MOD;
			total = (total * parts.get(partsLength - 1)) % MOD;

			return (int) total;
		}

	}

	private int getDifferentColorsM_2() {

		if (buckets[0][1] == 1 && buckets[N-1][1] == 1) { // buckets at boundary.
			if (buckets[0][0] != buckets[N-1][1]) {
				return 2;
			}
			else
				return 1;
		}

		// get distance between 2 buckets.
		int bkt1 = -1;
		for (int i = 0; i < N; i++) {
			if (buckets[i][1] == 1) {
				bkt1 = i;
				break;
			}
		}

		int bkt2 = -1;
		for (int i = bkt1 + 1; i < N; i++) {
			if (buckets[i][1] == 1) {
				bkt2 = i;
				break;
			}
		}

		int dist = bkt2 - bkt1;

		return dist;
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