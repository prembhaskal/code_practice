package codechef.august13.chmod;

import java.io.*;
import java.math.BigInteger;
import java.util.Random;
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

	int elements;
	int[] nums;
	int[][] elementMap;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		elements = in.nextInt();
		nums = new int[elements];

		for (int i = 0; i < elements; i++) {
			nums[i] = in.nextInt();
		}

		initialize();

		int queries = in.nextInt();

		for (int i = 0; i < queries; i++) {
			int left = in.nextInt();
			left--;
			int right = in.nextInt();
			right--;
			int mod = in.nextInt();

			int segmentProd = getSegmentProd(left, right, mod);
			out.println(segmentProd);
		}

	}

	private int getSegmentProd(int left, int right, int mod) {
		long segmentProd = 1;

		for (int i = 1; i < 101; i++) {
			int num = i;
			int power = elementMap[right][num] - elementMap[left][num];
			int numRaisePower = power(num, power, mod);
			segmentProd = (segmentProd*numRaisePower)%mod;
		}

		segmentProd = (segmentProd * nums[left])%mod;

		return (int) segmentProd;
	}

	private int power(long num, long pow, int mod) {
		long prod = 1;

		while (pow > 0) {
			if (pow%2==1) {
				prod = (prod * num)%mod;
			}

			num = (num * num)%mod;
			pow /= 2;
		}

		return (int)prod;
	}

	// for each number (1 to 100), store the power of each of it, and use it for multiplication.
	private void initialize() {
		elementMap = new int[elements][101]; // values range are from 1 to 100.
		int[] counter = new int[101];

		for (int i = 0; i < elements; i++) {
			int idx = nums[i];
			counter[idx]++;
			for (int j = 1; j < 101; j++) {
				elementMap[i][j] = counter[j];
			}
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
