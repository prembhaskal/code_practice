package spoj.set1.P5Palin;

import java.io.*;
import java.util.Arrays;
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
			String numStr = in.next();

			String nextPalin = getNextPalin(numStr);

			out.println(nextPalin);
		}

//		char '0' has ascii code 30HEX or 48.... use this to convert char array to int array.
//		char ch = '0';
//		int intch = ch;
//		System.out.println(intch);

	}

	private String getNextPalin(String numStr) {
		int[] num = getNum(numStr);

		int[] origNum = Arrays.copyOf(num, num.length);

		// if num is already palin, we need to change the centre num/pair.
		// else we start by changing the centre num/pairs and move forward.
		int left, right;
		if (num.length % 2 == 0) {
			right = num.length/2;
			left = right - 1;
		} else {
			left = right = (num.length)/2;
		}

		// copy the left half to the right half, so that we move to closest palindrome, above or below it depends
		// this is done because searching next one palin would be easier from there.
		int lx = left;
		int rx = right;
		while (lx >= 0) {
			num[rx] = num[lx];
			lx--;
			rx++;
		}



		// add condition when centre is 9.
		boolean overflow = false;

		if (compareArrays(origNum, num)) {
			// increase the centre number/pair once, so that we don't have to compare array again and again.
			while (left >= 0 && num[left] == 9) {
				num[right] = num[left] = 0;
				left--;
				right++;

				if (left < 0)
					overflow = true;
			}

			if (!overflow) {
				num[left] += 1;
				num[right] = num[left];

				left--;
				right++;

				while (left >= 0) {
					if (num[left] < num[right]) {
						while (left >= 0 && num[left] == 9) {
							num[right] = num[left] = 0;
							left--;
							right++;

							if (left < 0)
								overflow = true;
						}

						if (overflow)
							break;

						num[left] += 1;
						num[right] = num[left];
					}

					left--;
					right++;
				}
			}


		}

		if (overflow) {
			num[num.length - 1] = 1;
		}

		String nextPalin = convertNumArrToString(num);


		if (overflow)
			nextPalin = "1" + nextPalin;

		return nextPalin;

	}

	private String convertNumArrToString(int[] num) {
		char[] chars = new char[num.length];

		for (int i = 0; i < num.length; i++) {
			chars[i] = (char) (num[i] + 48);
		}

		return new String(chars);
	}

	// true if num1 is equal to OR greater than num2, considering both represent a number.
	private boolean compareArrays(int[] num1, int[] num2) {
		int i = num1.length - 1;
		while ( i>=0) {
			if (num1[i] < num2[i])
				return false;
			else if (num1[i] > num2[i])
				return true;
			i--;
		}

		return true;
	}

	private int[] getNum(String numStr) {
		char[] chars = numStr.toCharArray();

		int[] num = new int[chars.length];

		for (int i = 0; i < chars.length; i++) {
			int n = chars[i];
			n -= 48;
			num[i] = n;
		}

		return num;
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
