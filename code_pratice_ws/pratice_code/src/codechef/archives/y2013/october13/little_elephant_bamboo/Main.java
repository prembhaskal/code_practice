package codechef.archives.october13.little_elephant_bamboo;

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

	private int totalTrees;
	private int[] actualHeights;
	private int[] newHeights;


	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			totalTrees = in.nextInt();

			actualHeights = new int[totalTrees];
			newHeights = new int[totalTrees];

			for (int j = 0; j < totalTrees; j++) {
				actualHeights[j] = in.nextInt();
			}

			for (int j = 0; j < totalTrees; j++) {
				newHeights[j] = in.nextInt();
			}

			int substance = getMinimumSubstanceRequired();

			out.println(substance);
		}

	}

	// approach by linear equations.
	private int getMinimumSubstanceRequired() {
		int htDiffSum = 0;

		for (int i = 0; i < totalTrees; i++) {
			htDiffSum += (newHeights[i] - actualHeights[i]);
		}

		if (totalTrees == 1) {
			if (htDiffSum > 0) {
				return -1;
			} else {
				return Math.abs(htDiffSum);
			}
		} else if (totalTrees == 2) {
			if (htDiffSum != 0)
				return -1;
			else
				return Math.abs(newHeights[0] - actualHeights[0]); // can take 0 or 1 does not matter.

		} else {
			if (htDiffSum < 0)
				return -1;
			if (htDiffSum%(totalTrees-2) != 0)
				return -1;

			int totalSubstance = htDiffSum/(totalTrees-2);
			boolean valid = validate(totalSubstance);

			if (!valid)
				return -1;

			return totalSubstance;
		}
	}

	// substance added on each stem should be > 0 and an integer.

	// newHeight = actualHeight + totalSubstance - 2* substanceAddedOnThis.
	private boolean validate(int totalSubstance) {
		boolean valid = true;

		for (int i = 0; i < totalTrees; i++) {
			int diff1 = actualHeights[i] - newHeights[i] + totalSubstance;
			if (diff1 < 0 || diff1%2 !=0) {
				valid = false;
				break;
			}
		}

		return valid;
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
