package codechef.august13.chmod;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;
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

	int elements;
	int[] nums;
	int[][] elementMap;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		elements = in.nextInt();
		nums = new int[elements];

		for (int i = 0; i < elements; i++) {
			nums[i] = in.nextInt();
		}

		initialize();

		int queries = in.nextInt();

		for (int i = 0; i < queries; i++) {
			int left = in.nextInt();
			left++;
			
		}

	}

	private void initialize() {
		elementMap = new int[elements][101]; // values range are from 1 to 100.
		int[] counter = new int[101];

		for (int i = 0; i < elements; i++) {
			int idx = nums[i] + 1;
			counter[idx]++;
			elementMap[i][idx] = counter[idx];
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
