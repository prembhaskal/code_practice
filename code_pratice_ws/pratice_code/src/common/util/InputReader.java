package common.util;

import java.io.*;
import java.util.StringTokenizer;

/**
 * User: premkumar.bhaskal
 * Date: 4/30/13
 * Time: 3:53 PM
 *
 *
 * Class to read the input faster
 */
public class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public void close() {
		try {
			if (reader!=null) {
				reader.close();
			}
		} catch (IOException e) {
			// ignore error.
		}
	}

	// returns null if end of stream has reached.
	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				String line = reader.readLine();
				if (line == null)
					return null;
				tokenizer = new StringTokenizer(line);
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
