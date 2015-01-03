package codechef.april14.anucbc;

import java.io.*;
import java.util.InputMismatchException;

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
	private int[] nums;

	private int Q;
	private int[] queries;

	private int MOD = 1000_000_009;

	private int[] facts;
	private int[] factModInv;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		preCalculateFactValues();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			Q = in.nextInt();

			nums = new int[N];
			for (int j = 0; j < N; j++) {
				nums[j] = in.nextInt();
			}

			queries = new int[Q];
			for (int j = 0; j < Q; j++) {
				queries[j] = in.nextInt();
			}

			for (int j = 0; j < Q; j++) {
				int count;
//				count = findSubsetCount(queries[j]);
//				out.println(count);
				count = findSubSetCount2(queries[j]);
				out.println(count);
			}

		}

	}


	private void preCalculateFactValues() {
		int n = 100001;
		int factorial = 1;

		facts = new int[n];
		facts[0] = 1;
		factModInv = new int[n];
		factModInv[0] = 1;

		for (int i = 1; i < n; i++) {
			factorial = (int) (((long)factorial * i) % MOD);
			facts[i] = factorial;
			factModInv[i] = modInverse(factorial);
		}
	}


	private int getNCR(int n, int r) {
		if (r == 0)
			return 1;
		if (r == 1)
			return n;

		int num = (int) ((facts[n] * (long)factModInv[r]) % MOD);
		num = (int)((num * (long)factModInv[n-r]) % MOD);

		return num;
	}


	// DP but a normal one.
	private int findSubsetCount(int M) {
		int[] previous = new int[M];
		previous[0] = 1;

		int[] present = new int[M];

		int[] temp;

		for (int i = 0; i < N; i++) {

			for (int j = 0; j < M; j++) {
				present[j] = previous[j];
			}

			for (int j = 0; j < M; j++) {
				int sum = (int) ((j + (long) nums[i]) % M);
				if (sum < 0) { // handling negative nos.
					sum = (sum + M) % M;
				}
				present[sum] = (int) ((present[sum] + (long)previous[j]) % MOD);
			}

			temp = previous;
			previous = present;

			present = temp;
//			Arrays.fill(present, 0);
		}


		return previous[0];
	}

	// DP but taking mod for each of the number, then using it further....
	// THIS got accepted :)
	// finding ways for each of the number (0 to M), then matching with each other.
	private int findSubSetCount2(int M) {
		int[] numsCount = new int[M];

		for (int i = 0; i < N; i++) {
			int num = nums[i];
			num = num % M;
			if (num < 0) {
				num = num + M;
			}
			numsCount[num]++;
		}

		int[][] ways = new int[M][M];
		for (int i = 0; i < M; i++) {
			ways[i] = getWays(i, numsCount[i], M);
		}

		int[] totalWays = new int[M];
		int[] newTotalWays = new int[M];

		// find the total ways.
		for (int num = 0; num < M; num++) {

			if (numsCount[num] == 0)
				continue;

			for (int i = 0; i < M; i++) {
				newTotalWays[i] = (int) ((totalWays[i] + (long)ways[num][i]) % MOD);
			}

			for (int j = 0; j < M; j++)
				for (int k = 0; k < M; k++) {
					int sum = (j + k) % M;
					int prod = (int) ((totalWays[j] * (long) ways[num][k]) % MOD);
					newTotalWays[sum] = (int) ((newTotalWays[sum] + (long) prod) % MOD);
				}

			totalWays = newTotalWays;
			newTotalWays = new int[M];
		}

		return totalWays[0] + 1;
	}

	private int[] getWays(int num, int count, int M) {
		int[] ways = new int[M];
		if (count == 0)
			return ways;
		for (int i = 1; i <= count; i++) {
			int prod = (int) ((num * (long)i) % M);
			ways[prod] = (int) ((ways[prod] + (long)getNCR(count,i)) % MOD);
		}

		return ways;
	}


	private int modInverse(long n) {
		return pow(n , MOD - 2);
	}

	private int pow(long n, int p) {
		long power = 1;
		while (p > 0) {
			if ((p&1) == 1) {
				power = (power * n) % MOD;
			}

			n = (n * n) % MOD;
			p /= 2;
		}

		return (int) power;
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
