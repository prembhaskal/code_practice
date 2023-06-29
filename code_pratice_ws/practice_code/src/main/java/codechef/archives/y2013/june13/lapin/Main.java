package codechef.archives.june13.lapin;

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
			String result = isLapinDrome(in.next());
			out.println(result);
		}

	}

	private String isLapinDrome(String str) {
		char[] strArray = str.toCharArray();

		int len = strArray.length;

		int i=0;

		char[] count = new char[26];

		// read first half
		for (;i<len/2;i++) {
			char ch =  strArray[i];
			int idx = (int)ch - (int)'a';
			count[idx]++;
		}

		if (len%2==1) {
			i++;
		}

		// read next half
		for (;i<len;i++) {
			char ch = strArray[i];
			int idx = (int)ch - (int)'a';
			count[idx]--;
		}

		for (int cnt : count) {
			if (cnt != 0)
				return "NO";
		}

		return "YES";
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
