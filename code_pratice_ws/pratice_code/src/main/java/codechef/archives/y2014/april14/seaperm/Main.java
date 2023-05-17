package codechef.april14.seaperm;

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

	private int[] nums;
	private int N;
	private int k;
	private int S;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			k = in.nextInt();
			S = in.nextInt();

			nums = new int[N];

			for (int j = 0; j < N; j++) {
				nums[j] = in.nextInt();
			}

			int[] permutations;
//			permutations = getBruteForce();
			permutations = getSortedInput();

			for (int j = 0; j < N; j++) {
				out.print(permutations[j] + " ");
			}
		}

	}

	private int[] getBruteForce() {
		int[] per = new int[N];

		for (int i = 0; i < N; i++) {
			per[i] = i + 1;
		}

		return per;
	}

	// sorting gives the score as 0.986....kind of strange. but good :)
	private int[] getSortedInput() {
		NumIndex[] numIndexes = new NumIndex[N];

		for (int i = 0; i < N; i++) {
			numIndexes[i] = new NumIndex(nums[i], i + 1);
		}

		Arrays.sort(numIndexes);

		int[] per = new int[N];

		for (int i = 0; i < N; i++) {
			per[i] = numIndexes[i].index;
		}

		return per;
	}

	private class NumIndex implements Comparable<NumIndex>{
		int num;
		int index;

		public NumIndex(int num, int index) {
			this.num  = num;
			this.index = index;
		}


		@Override
		public int compareTo(NumIndex o) {
			int other = o.num;
			return (num < other) ? -1 : ((num == other) ? 0 : 1);
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
