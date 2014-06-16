package codechef.june14.forgetpw;

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

	private char[]rule = new char[128];

	// TODO try to make it faster
	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i = 0;i < tests; i++) {
			int N = in.nextInt();

			// init rule to self first.
			for (int j = 0; j < rule.length; j++) {
				rule[j] = (char) j;
			}

			for (int j = 0; j < N; j++) {
				char ch = in.next().toCharArray()[0];
				rule[(int)ch] = in.next().toCharArray()[0];
			}

			String encrypted = in.next();
			char[] encryptChars = encrypted.toCharArray();
			char[] decryptChars = new char[encryptChars.length];

			boolean hasDecimal = false;
			// decrypt password;
			for (int j = 0; j < encryptChars.length; j++) {
				char actChar = encryptChars[j];
				decryptChars[j] = rule[actChar];
				if (decryptChars[j] == '.')
					hasDecimal =  true;
			}

			// remove starting 0.
			int start = -1;
			for (int j = 0; j < decryptChars.length; j++) {
				char ch = decryptChars[j];
				if (ch != '0') {
					start = j;
					break;
				}
			}

			if (start == -1) {
				out.println('0');
				continue;
			}

			// remove trailing zeroes after .
			int end = decryptChars.length - 1;
			if (hasDecimal) {
				for (int j = end; j >= 0; j--) {
					char ch = decryptChars[j];
					if (ch != '0') {
						end = j;
						break;
					}
				}
			}

			if (decryptChars[end] == '.')
				end--;

			// copy from start and end.
			char[] finalString = Arrays.copyOfRange(decryptChars, start, end + 1);
			if (finalString.length != 0)
				out.println(new String(finalString));
			else
				out.println('0');
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
