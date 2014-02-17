package codechef.february14.submin;

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
		int size = in.nextInt();
		int[] nums = new int[size];

		for (int i = 0; i < size; i++) {
			nums[i] = in.nextInt();
		}

		int totalQueries = in.nextInt();
		int[] queries = new int[totalQueries];

		for (int i = 0; i < totalQueries; i++) {
			queries[i] = in.nextInt();
		}

		int[] subArraysValues = getRequiredSubArrays(nums, queries);
		for (int i = 0; i < subArraysValues.length; i++) {
			out.println(subArraysValues[i]);
		}
	}

	private int[] getRequiredSubArrays(int[] nums, int[] queries) {
		int len = nums.length;
		int[][] minMap = new int[len][len];

		for (int i = 0; i < len; i++) {
			int min = nums[i];
			for (int j = i; j < len; j++) {
				min = Math.min(min, nums[j]);
				minMap[i][j] = min;
			}
		}

		int[] subArrays = new int[queries.length];

		for (int n = 0; n < queries.length; n++) {
			int requiredMinValue = queries[n];
			int matchingSubArray = 0;

			// just brute force for each of the sub array as the constraints are quite small
			for (int i = 0; i < len; i++) {
				for (int j = i; j < len; j++) {
					int minValue = minMap[i][j];
					if (minValue == requiredMinValue)
						matchingSubArray++;
				}
			}


			subArrays[n] = matchingSubArray;
		}

		return subArrays;
	}


	private int computeValue(int start, int min, int end) {
		if (min > 0 && end > 0) {
			return (min - start + 1) * ( end - min + 1);
		}
		return 0;
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
