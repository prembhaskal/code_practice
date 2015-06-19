package codechef.august14.tshirts;

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

	private int[][] DP;
	private int N;
	private boolean[][] personShirts;

	private int MOD = 1000_000_007;


	// solved after reading the editorial...
	// approach is 2^N * N
	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();

			personShirts = new boolean[N][101];

			for (int j = 0; j < N; j++) {
				String[] shirts = in.next().split(" ");

				for (int k = 0; k < shirts.length; k++) {
					int shirt = Integer.parseInt(shirts[k]);
					personShirts[j][shirt] = true;
				}
			}

			DP = new int[1025][110];

			for (int j = 0; j < 1025; j++) {
				for (int k = 0; k < 110; k++) {
					DP[j][k] = -1;
				}
			}

			int ways = findTotalUniqueWay(0, 1);
			out.println(ways);
		}

	}

	private int findTotalUniqueWay(int bitMask, int shirt) {
		// base case.
		if (bitMask == ((1<<N) - 1))
			return 1;

		if (shirt == 101) {
			return 0;
		}

		// DP
		if (DP[bitMask][shirt] != -1)
			return DP[bitMask][shirt];

		long ways = 0;
		// try not to use the shirt, CASE when we use some other shirt to get the distinct arrangement.
		ways = findTotalUniqueWay(bitMask, shirt + 1);

		// try to use the shirt for all persons, in case they are not wearing something already.
		// and Of course if they have this shirt
		int mask = 1;
		for (int i = 0; i < N; i++) {
			// if person has a shirt.
			if (personShirts[i][shirt]) {
				if ((bitMask & mask) == 0) {
					ways += findTotalUniqueWay((bitMask | mask), shirt + 1);
					if (ways > MOD)
						ways -= MOD;
				}
			}

			// if the person is not wearing something already
			mask = mask << 1; // move to check next person
		}

		DP[bitMask][shirt] = (int) ways;

		return (int) ways;
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
		try {
			String line = reader.readLine();
			return line;
		} catch (Exception e) {
			throw new RuntimeException();
		}
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
