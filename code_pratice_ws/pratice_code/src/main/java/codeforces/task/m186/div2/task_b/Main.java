package codeforces.task.m186.div2.task_b;

import java.io.*;
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

	private int[] range;


	public void solve(InputReader in, PrintWriter out) throws IOException {

		String str = in.next();
		preProcess(str);

		int query = in.nextInt();

		// answer each query

		for (int i=0;i<query;i++) {
			int left = in.nextInt();
			int right = in.nextInt();

			int match = range[right-1] - range[left-1];

			out.println(match);
		}
	}


	private void preProcess(String str) {
		char[] strArray = str.toCharArray();

		range = new int[strArray.length];

		range[0] = 0;

		for (int i=1;i<strArray.length;i++) {
			if (strArray[i] == strArray[i-1]) {
				range[i] = range[i-1] + 1;
			} else {
				range[i] = range[i-1];
			}
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
