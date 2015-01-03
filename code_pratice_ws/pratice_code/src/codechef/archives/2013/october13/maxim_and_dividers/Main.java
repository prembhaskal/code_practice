package codechef.archives.october13.maxim_and_dividers;

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

// accepted :)
class TaskA {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			int num = in.nextInt();
			int count = getOverLuckyDividers(num);

			out.println(count);
		}

	}

	private int getOverLuckyDividers(int n) {
		int overLuckyCount = 0;
		for (int i = 1; i <= n/i; i++) {
			if (n % i == 0) {
				int other = n/i;
				if (isOverLuckyNumber(i))
					overLuckyCount++;
				if (isOverLuckyNumber(other))
					overLuckyCount++;
			}
		}

		return overLuckyCount;
	}

	private boolean isOverLuckyNumber(int num) {
		boolean has47 = false;

		while (num > 0) {
			int oneDigit = num%10;
			if (oneDigit==4 || oneDigit==7) {
				has47 = true;
				break;
			}
			num /= 10;
		}

		return has47;
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
