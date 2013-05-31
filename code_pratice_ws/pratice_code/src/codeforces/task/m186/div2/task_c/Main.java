package codeforces.task.m186.div2.task_c;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			Task solution = new Task();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class Task {

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int size = in.nextInt();

		int[] num  = new int[size];

		for (int i = 0; i < size; i++) {
			num[i] = in.nextInt();
		}

	}

	private long getMaxBeauty(int[] num, int size) {
		if (size==1) {
			return num[0];
		}

		long totalSum = 0;

		for (int i = 0; i < size; i++) {
			totalSum += num[i];
		}

		Arrays.sort(num);

		int maxCount = getMaxCountUsed(size);


	}

	private int getMaxCountUsed(int size) {
		size = size/4;

		int maxCount = 0;

		while (size > 0) {
			maxCount += size;
			size /= 4;
		}

		return maxCount;
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
