package codechef.november13.squ_digit_square;
import java.io.*;
import java.util.*;

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

	Set<Integer> perfectDigits = new HashSet<>();
	List<Long> perfectDigitSquares = new ArrayList<>();

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();
		createSet();
		initPerfectDigitSquare();

		for (int i=0;i<tests;i++) {
			long lower = in.nextLong();
			long upper = in.nextLong();

			int total = getNumberOfPerfectDigitSquares(lower, upper);

			out.println(total);
		}

	}

	private void initPerfectDigitSquare() {
		for (int i = 1; i < 100001; i++) {
			long square = (long)i * i;
			if (containsOnlyPerfectDigits(square))
				perfectDigitSquares.add(square);
		}
	}


	private void createSet() {
		perfectDigits.add(0);
		perfectDigits.add(1);
		perfectDigits.add(4);
		perfectDigits.add(9);
	}

	private int getNumberOfPerfectDigitSquares(long lower, long upper) {

		int total = 0;
		for (Long perfectDigSquare : perfectDigitSquares) {
			if (lower > perfectDigSquare)
				continue;
			else if (upper < perfectDigSquare)
				break;
			else
				total++;
		}

		return total;
	}

	private boolean containsOnlyPerfectDigits(long num) {

		while (num > 0) {
			int digit = (int) (num%10);

			if (!perfectDigits.contains(digit))
				return false;

			num /= 10;
		}

		return true;
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
