package codechef.archives.practice.small_factorial;

import java.io.*;
import java.math.BigInteger;
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
			int num = in.nextInt();
			String fact = getFactorial(num);
			String factB = getFactUsingBigInteger(num);
			if (!fact.equals(factB))
				out.println("problem");
			out.println(fact);
//			out.println(factB);
		}

	}

	private String getFactUsingBigInteger(int num) {
		BigInteger one = BigInteger.ONE;
		BigInteger fact = one;

		for (int i = 2; i <= num; i++) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}

		return fact.toString();
	}

	private String getFactorial(int n) {
		int[] factorialArr = new int[]{1};

		for (int i = 2; i <= n; i++) {
			int[] numArr = numToArray(i);
			factorialArr = multiply(factorialArr, numArr);
		}

		return arrayToString(factorialArr);
	}

	private String arrayToString(int[] a) {
		StringBuilder sb = new StringBuilder("");
		for (int i = a.length - 1; i >= 0; i--) {
			sb.append(a[i]);
		}

		return sb.toString();
	}
	
	private int[] multiply(int[] a, int[] b) {
		int len1 = a.length;
		int len2 = b.length;
		
		int[] product = new int[len1 + len2];
		
		for (int i = 0; i < len2; i++) {
			int digit = b[i];
			int[] intermediate = new int[len1 + len2];
			int carry = 0;
			for (int j = 0; j < len1; j++) {
				int prod = a[j] * digit + carry;
				carry = prod / 10;
				intermediate[i + j] = prod % 10;
			}
			intermediate[i + len1] = carry;
			
			addArray(product, intermediate);
		}

		product = correctLength(product);
		
		return product;
	}

	private int[] correctLength(int[] a) {
		int length = a.length;
		int zeroes = 0;
		for (int i = length - 1; i >= 0; i--) {
			if (a[i] > 0)
				break;
			zeroes++;
		}
		int newLength = length - zeroes;
		int[] newArray = new int[newLength];
		System.arraycopy(a, 0, newArray, 0, newLength);
		return newArray;
	}

	// does a[] = a[] + b[]; AND a[] should be big enough to store the sum.
	// a[0] stores the ones digit. b should be of length at most a.len - 1;
	private void addArray(int[] a, int [] b) {
		int carry = 0;
		for (int i = 0; i < b.length; i++) {
			int digSum = a[i] + b[i] + carry;
			carry = digSum / 10;
			a[i] = digSum % 10;
		}

		if (carry > 0)
			a[b.length] = carry;
	}
	
	// works only for at max a 3 digits number
	private int[] numToArray(int num) {
		int[] numArr = new int[3];
		for (int i = 0; i < 3; i++) {
			numArr[i] = num % 10;
			num /= 10;
		}
		return numArr;
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

}
