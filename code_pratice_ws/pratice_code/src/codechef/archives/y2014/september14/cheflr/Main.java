package codechef.september14.cheflr;

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

	private int MOD = 1000_000_007;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			String input = in.next();
			int lastNode = getLastNode(input);
			out.println(lastNode);
		}

	}

	private int getLastNode(String input) {
		char[] chars = input.toCharArray();

		long node = 1;
		boolean oddline = true;
		for (int i = 0; i < chars.length; i++) {
			if (oddline) { // odd
				if (chars[i] == 'l') {
					node = node << 1;
				}
				else {
					node = node << 1;
					node += 2;
				}
			}
			else { // even
				if (chars[i] == 'l') {
					node = node << 1;
					node--;
				}
				else {
					node = node << 1;
					node++;
				}
			}

			node = (node % MOD);
			oddline = !oddline;
		}

		return (int) node;
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
