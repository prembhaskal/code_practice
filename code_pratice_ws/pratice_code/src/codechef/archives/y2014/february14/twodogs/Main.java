package codechef.february14.twodogs;

import java.io.*;
import java.util.Arrays;
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

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int len = in.nextInt();
		int reqdSum = in.nextInt();

		int[] types = new int[len];

		for (int i = 0; i < len; i++) {
			types[i] = in.nextInt();
		}

		int minSeconds = getMinSeconds(types, reqdSum);

		out.println(minSeconds);
	}


	private int getMinSeconds(int[] types, int sumReqd) {
		// look up array for min index of a number from both sides.
		int[] lookupIdx = new int[1000001];
		Arrays.fill(lookupIdx, Integer.MAX_VALUE);

		for (int i = 0; i <= types.length / 2; i++) {
			int type = types[i];
			lookupIdx[type] = Math.min(lookupIdx[type], i);
		}

		for (int i = types.length - 1; i >= types.length / 2 ; i--) {
			int type = types[i];
			lookupIdx[type] = Math.min(lookupIdx[type], types.length - 1 - i);
		}


		// try to get the sum from the lookup.
		int minTimeReqd = Integer.MAX_VALUE;
		for (int a = 1; a < sumReqd; a++) {
			int b = sumReqd - a;
			if (a == b)
				continue;

			// max time required by either of the dog.
			int timeReqd = Math.max(lookupIdx[a], lookupIdx[b]);

			// min found till now.
			minTimeReqd = Math.min(timeReqd, minTimeReqd);
		}

		// if it is more than the limit, means we did not find the type.
		if (minTimeReqd > 1000000) {
			return -1;
		}
		else {
			return minTimeReqd + 1; // +1 since we store 0 based index.
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
