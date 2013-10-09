package codechef.october13.yet_another_nice_girl;

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

	private int totalNums;
	private int[] A;
	private int[] B;

	// this was accepted :)
	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			totalNums = in.nextInt();

			A = new int[totalNums];
			B = new int[totalNums];

			for (int j = 0; j < totalNums; j++) {
				A[j] = in.nextInt();
			}

			for (int j = 0; j < totalNums; j++) {
				B[j] = in.nextInt();
			}

			double totalKisses = getExpectedNumber();
			out.println(totalKisses);
		}

	}

	private double getExpectedNumber() {
		int countOfOnes = 0;
		int countOfTwos = 0;
		int countOfThreeFours = 0;
		for (int i = 0; i < totalNums; i++) {
			if (B[i] > 4)
				continue;
			else if (B[i] == 1)
				countOfOnes++;
			else if (B[i] == 2)
				countOfTwos++;
			else if (B[i] == 3 || B[i] == 4)
				countOfThreeFours++;
		}

		// sort for binary search.
		Arrays.sort(B);

		long totalIsses = 0;

		// x/log(x) <= 2 for 2 < x < 4, so need special cases for x = 2 and x = 3.
		for (int i = 0; i < totalNums; i++) {
			int num = A[i];
			if (num == 1) {
				continue;
			}
			else if (num == 2) {
				totalIsses = totalIsses + getNumbersMoreThanMe(num);
				totalIsses = totalIsses + countOfOnes;
				totalIsses = totalIsses - countOfThreeFours;
			}
			else if (num == 3) {
				totalIsses = totalIsses + getNumbersMoreThanMe(num);
				totalIsses = totalIsses + countOfOnes;
				totalIsses = totalIsses + countOfTwos;
			}
			else  {
				totalIsses = totalIsses + getNumbersMoreThanMe(num);
				totalIsses = totalIsses + countOfOnes;
			}
		}

		double expectedValue = totalIsses * 1.0;
		expectedValue = expectedValue/totalNums;

		return expectedValue;
	}

	private int getNumbersMoreThanMe(int num) {
		int idx = binarySearchIndex(num);

		if (idx < 0)
			return 0;
		else
			return (totalNums - idx);
	}

	// find the first index of the number which is greater than the num
	// search for boundary such that B[idx] > num && B[idx-1] <= num.
	private int binarySearchIndex(int num) {
		int low = 0;
		int high = B.length-1;
		int index = -1; // assume not existent

		while (low <= high) {
			int mid = (low + high)/2;
			int midVal = B[mid];

			if (midVal > num) {
				if (mid == 0) {
					index = 0;
					break;
				}

				if (B[mid - 1] <= num) {
					index = mid;
					break;
				}
				else {
					high = mid - 1;
				}
			}
			else if (midVal <= num) {
				low  = mid + 1;
			}
		}

		return index;
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
