package codechef.nov14.prpaln;

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
			boolean possible = canMakePalin(in.next());
			if (possible) {
				out.println("YES");
			}
			else {
				out.println("NO");
			}
		}

	}


	private boolean canMakePalin(String word) {
		char[] chars = word.toCharArray();

		if (chars.length < 3)
			return true;

		// try one way

		int start = 0;
		int end = chars.length - 1;

		int totalChanges = 0;
		while (start < end) {
			if (chars[start] == chars[end]) {
				start++;
				end--;
			}
			else { // ignoring the che
				totalChanges++;
				end--;
			}
		}

		if (totalChanges <= 1) {
			return true;
		}

		// try other way.
		start = 0;
		end = chars.length - 1;
		totalChanges = 0;
		while (start < end) {
			if (chars[start] == chars[end]) {
				start++;
				end--;
			}
			else { // ignoring the che
				totalChanges++;
				start++;
			}
		}

		if (totalChanges <= 1) {
			return true;
		}

		return false;
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
