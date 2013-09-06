package codechef.september13;

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

	int[] nums;
	int X;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int size = in.nextInt();
		nums = new int[size];

		for (int i = 0; i < size; i++) {
			nums[i] = in.nextInt();
		}

		X = in.nextInt();

		long totalCost = getMinCost();

		out.println(totalCost);
	}


	private long getMinCost() {
		if (X==0)
			return 0;

		Arrays.sort(nums);

		int negativeNos = 0;
		long negativeSum = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 0) {
				negativeNos++;
				negativeSum = negativeSum + (-1)*nums[i];
			}
		}

		if (X>=negativeNos)
			return negativeSum;

		long countTillX = 0;
		for (int i = 0; i < X; i++) {
			countTillX = countTillX + (-1)*nums[i];
		}

		return countTillX;
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
