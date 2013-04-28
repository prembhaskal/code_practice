package codechef.template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] s) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			InputReader in = new InputReader(reader);
			PrintWriter writer = new PrintWriter(System.out);

			TaskA solution = new TaskA();
			solution.solve(in,writer);

			writer.close();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

// product of divisors..... here is the http://ww2.codechef.com/problems/D1/
class TaskA {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {

		}

	}


}

class InputReader {
	StreamTokenizer tokenizer;

	public InputReader(Reader reader) {
		tokenizer = new StreamTokenizer(reader);
	}

	public int nextInt() throws IOException {
		read();
		return (int) tokenizer.nval;
	}

	public long nextLong() throws IOException {
		read();
		return (long) tokenizer.nval;
	}

	public String nextString() throws IOException {
		read();
		return tokenizer.sval;
	}

	public double nextDouble() throws IOException {
		read();
		return tokenizer.nval;
	}

	private void read() throws IOException {
		tokenizer.nextToken();
	}
}

