package codechef.august14.crawa;

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
			int x = in.nextInt();
			int y = in.nextInt();
			if (isOnPath(x, y))
				out.println("YES");
			else
				out.println("NO");
		}

	}

	private boolean isOnPath(int x, int y) {

		if (x == 0 && y == 0)
			return true;

		// check if on vertical line on +x.
		if (x >0 && ((x&1) == 1)) {
			int n = x/2;
			int y1 = -2*n;
			int y2 = 2*n + 2;

			if (y1 <= y && y <= y2)
				return true;
		}

		// check if on vertical line on -x.
		if (x < 0 && ((x&1)==0)) {
			int n = x/2;
			int y1 = 2*n;
			int y2 = -2*n;
			if (y1 <= y && y <= y2)
				return true;
		}

		// check if on horizontal line on +y;
		if (y > 0 && ((y&1) == 0)) {
			int n = y/2;
			int x1 = -2*n;
			int x2 = 2*n - 1;
			if (x1 <= x && x <= x2)
				return true;
		}

		// check if on horizontal line on -y;
		if (y <0 && ((y&1) == 0)) {
			int n = y/2;
			int x1 = 2*n;
			int x2 = -2*n + 1;

			if (x1 <=x && x <= x2)
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
