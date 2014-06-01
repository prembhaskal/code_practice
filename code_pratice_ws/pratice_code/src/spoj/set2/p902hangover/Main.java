package spoj.set2.p902hangover;

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

	private double[] cardsVsHangOver;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		initHangOver();

		while (true){
			String search = in.next();
			if (search.equals("0.00"))
				break;

			int minCards = binarySearch(Double.parseDouble(search));
			out.println(minCards + " card(s)");
		}

	}

	private int binarySearch(double search) {
		int low = 0;
		int high = cardsVsHangOver.length - 1;

		while (low < high) {
			int mid = (low + high) / 2;
			double val = cardsVsHangOver[mid];

			if (mid == 0)
				throw new RuntimeException("should not happen");

			if ((cardsVsHangOver[mid - 1] < search) && (search < cardsVsHangOver[mid])) {
				// found
				return mid;
			}

			if (val < search) {
				low = mid;
			}
			else {
				high = mid;
			}
		}

		throw new RuntimeException("should not happen");
	}

	private void initHangOver() {
		cardsVsHangOver = new double[1000];
		double hangover = 0;

		for (int i = 2; i < 1000; i++) {
			hangover += (1/(double)i);
			cardsVsHangOver[i-1] = hangover;
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
