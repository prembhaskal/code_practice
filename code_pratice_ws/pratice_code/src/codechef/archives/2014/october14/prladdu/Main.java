package codechef.october14.prladdu;

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


	private int[] D;
	private int n;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			n = in.nextInt();
			D = new int[n];

			for (int j = 0; j < n; j++) {
				D[j] = in.nextInt();
			}

			long minGrass = getMinimumGrass();
			out.println(minGrass);
		}

	}

	// wasted time thinking of sorting and stuff.....good question.
	private long getMinimumGrass() {
		// separate the positive and negative nos. in the given order itself. NO sorting required
		// then combine them and keep adding the result for each combination.

		int posIdx = -1;
		int negIdx = -1;

		// first positive number.
		for (int i = 0; i < n; i++) {
			if (D[i] > 0) {
				posIdx = i;
				break;
			}
		}

		// first negative number.
		for (int i = 0; i < n; i++) {
			if (D[i] < 0) {
				negIdx = i;
				break;
			}
		}

		if (negIdx == -1) {
			return 0; // all nos are zero
		}

		long totalGrass = 0; // long as the questions ranges are actually tricky. :)

		// two indexes pointing at positive and negative number.
		while (true) {
			int pos = D[posIdx];
			int neg = -D[negIdx];

			int common = Math.min(pos, neg);
			totalGrass = totalGrass + (common * ( Math.abs(posIdx - negIdx)));

			// reduce the common from both the numbers.
			D[posIdx] -= common;
			D[negIdx] += common;

			if (D[posIdx] == 0) {
				posIdx = getNextPositiveIdx(posIdx);
				if (posIdx == -1){
					break;
				}
			}

			if (D[negIdx] == 0) {
				negIdx = getNextNegativeIdx(negIdx);
				if (negIdx == -1) {
					break;
				}
			}
		}

		return totalGrass;
	}

	private int getNextPositiveIdx(int startIdx) {
		for (int i = startIdx; i < n; i++) {
			if (D[i] > 0) {
				return i;
			}
		}
		return -1;
	}

	private int getNextNegativeIdx(int startIdx) {
		for (int i = startIdx; i < n; i++) {
			if (D[i] < 0) {
				return i;
			}
		}

		return -1;
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
