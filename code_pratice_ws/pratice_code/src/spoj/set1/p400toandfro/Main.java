package spoj.set1.p400toandfro;

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

		while (true) {
			int cols = in.nextInt();
			if (cols == 0)
				break;
			String encodedMsg = in.next();
			String decodeMsg = getDecodedMsg(encodedMsg, cols);
			out.println(decodeMsg);
		}
	}

	private String getDecodedMsg(String msg, int cols) {
		int rows = msg.length()/cols;
		char[] msgArray = msg.toCharArray();

		// store the message in 2-D array
		char[][] chars = new char[rows][cols];
		int idx = 0;
		for (int i = 0; i < rows; i++) {
			if (i % 2 == 0) { // left to right
				for (int j = 0; j < cols; j++) {
					chars[i][j] = msgArray[idx++];
				}
			}
			else { // right to left
				for (int j = cols - 1; j >= 0 ; j--) {
					chars[i][j] = msgArray[idx++];
				}
			}
		}

		// start reading the message from the 2-D array
		StringBuilder encode = new StringBuilder("");
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				encode.append(chars[j][i]);
			}
		}

		return encode.toString();
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
