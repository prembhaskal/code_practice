package codechef.may13.your_name_mine;

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
			String A = in.next();
			String B = in.next();

			boolean possible = isPossible(A, B);

			if (possible)
				out.println("YES");
			else
				out.println("NO");
		}

	}


	private boolean isPossible(String A, String B) {
		String smaller;
		String bigger;

		if (A.length() >= B.length()) {
			smaller = B;
			bigger = A;
		} else {
			smaller = A;
			bigger = B;
		}

		char[] smallerArr = smaller.toCharArray();
		char[] biggerArr = bigger.toCharArray();

		int i = 0;
		for (int j=0;j<biggerArr.length && i<smallerArr.length; j++) {

			char bigCh = biggerArr[j];
			char smallCh = smallerArr[i];

			if (bigCh==smallCh) {
				i++;
			}
		}

		if (i>=smallerArr.length)
			return true;
		else
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
