package spoj.set2.p346coins;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
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

	private Map<Integer, Long> coinsVsMax;
	public void solve(InputReader in, PrintWriter out) throws IOException {
		String line;
		while ((line = in.next()) != null) {
			coinsVsMax = new HashMap<Integer, Long>();
			int n = Integer.parseInt(line);
			long maxDollars = maxDollars(n);
			out.println(maxDollars);
		}
	}


	private long maxDollars(int n) {
		if (n == 0)
			return 0;
		// get the max for this coin, if it exists.
		if (coinsVsMax.containsKey(n)) {
			return coinsVsMax.get(n);
		}

		// else divide into multiple coins and try.
		long maxVal = maxDollars(n/2) + maxDollars(n/3) + maxDollars(n/4);
		maxVal = Math.max(maxVal, (long)n);

//		System.out.println("coin " + n + " value " + maxVal);
		coinsVsMax.put(n, maxVal);
		return maxVal;
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
				String line = reader.readLine();
				if (line == null)
					return line;
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
