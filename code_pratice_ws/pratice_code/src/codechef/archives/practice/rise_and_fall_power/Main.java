package codechef.archives.practice.rise_and_fall_power;

import java.io.*;
import java.math.BigDecimal;
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

//                  http://www.codechef.com/problems/A4
// http://stackoverflow.com/questions/635183/fast-exponentiation-when-only-first-k-digits-are-required
// http://discuss.codechef.com/questions/15398/first-k-digits-of-nn
// to get the first k digits of the exponent 10(x).

// TODO somehow java solutions not seem to be working some precision issue.
// try C++.
class TaskA {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			int n = in.nextInt();
			int k = in.nextInt();
			String[] firstLastDigits = getFirstLastKDigitsOfPower(n, k);
			out.print(firstLastDigits[0] + " ");
			out.println(firstLastDigits[1]);
		}

	}

	private String[] getFirstLastKDigitsOfPower(int n, int k) {
		String[] firstLastDigits = new String[2];
		// get the last k digits.
		int mod = 1000000000;

		int lastkDigits = powMod(n, n, mod);
		String numStr = "000000000" + lastkDigits;
		numStr = numStr.substring(numStr.length()-k);
		firstLastDigits[1] = numStr;

		String firstDigits = getFirstDigits(n, k);
		firstLastDigits[0] = firstDigits;
		// get the first k digits
		return firstLastDigits;
	}

	private int powMod(long num, long p, int mod) {
		long pow = 1;
		while (p > 0) {
			if (p%2==1) {
				pow = (pow * num) % mod;
			}

			num = (num * num) % mod;
			p /= 2;
		}

		return (int) pow;
	}

	private String getFirstDigits(int n, int k) {
		double x = n * 1.0 * Math.log10(n * 1.0);
		double fracX = x - Math.floor(x);

		double allDigits = Math.pow(10, fracX + k + 1);
		BigDecimal bigDecimal = BigDecimal.valueOf(allDigits);
		int allDigitsNum = bigDecimal.intValue();

		String allDigitsStr = allDigitsNum + "";

		return allDigitsStr.substring(0, k);
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
