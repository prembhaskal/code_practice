package codechef.february14.lcpesy;

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

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			String a = in.next();
			String b = in.next();
			int len = getCommonLength(a, b);
			out.println(len);
		}

	}

	private int getCommonLength(String a, String b) {
		int[] values1 = new int[128];
		for (int ch : a.toCharArray()) {
			values1[ch]++;
		}

		int[] values2 = new int[128];
		for (int ch : b.toCharArray()) {
			values2[ch]++;
		}

		int commonLength = 0;

		for (int i = 0; i < 128; i++) {
			commonLength = commonLength + Math.min(values1[i], values2[i]);
		}

		return commonLength;
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
