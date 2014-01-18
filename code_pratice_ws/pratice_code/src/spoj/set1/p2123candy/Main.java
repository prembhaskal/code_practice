package spoj.set1.p2123candy;

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

	public void solve(InputReader in, PrintWriter out) throws IOException {

		while (true) {
			int size = in.nextInt();
			if (size < 0)
				break;

			int[] nums = new int[size];
			for (int i = 0; i < size; i++) {
				nums[i] = in.nextInt();
			}

			int minMoves = getMinMoves(nums);
			out.println(minMoves);
		}

	}

	private int getMinMoves(int[] nums) {
		int total = 0;
		for (int i = 0; i < nums.length; i++) {
			total += nums[i];
		}

		if (total % nums.length != 0)
			return -1;

		int candiesInEach = total / nums.length;

		int moves = 0;
		for (int i = 0; i < nums.length; i++) {
			moves += Math.abs(nums[i] - candiesInEach);
		}

		return moves/2;
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
