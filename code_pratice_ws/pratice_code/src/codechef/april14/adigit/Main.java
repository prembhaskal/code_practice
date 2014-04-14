package codechef.april14.adigit;

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

	private int[] nums;
	private int n;
	private int m;

	private int[] queryIndexes;
	private int[] results;

	private int[][] DP;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		n = in.nextInt();
		m = in.nextInt();
		nums = new int[n];

		String numberWithoutSpaces = in.next();
		char[] nosChars = numberWithoutSpaces.toCharArray();

		for (int i = 0; i < nosChars.length; i++) {
			nums[i] = Integer.parseInt(nosChars[i] + "");
		}

		initDP();

		queryIndexes = new int[m];

		for (int i = 0; i < m; i++) {
			queryIndexes[i] = in.nextInt();
			queryIndexes[i]--; // 0 based indexes
		}

		findAbsDiffSum();

		// print results
		for (int i = 0; i < m; i++) {
			out.println(results[i]);
		}
	}


	private void findAbsDiffSum() {
		results = new int[m];

		for (int i = 0; i < m; i++) {
			int index = queryIndexes[i];
			int absSum = 0;
			int num = nums[index];

			for (int digit = 0; digit < 10; digit++) {
				int diff = DP[index][digit]  * Math.abs(num - digit);
				absSum += diff;
			}

			results[i] = absSum;
		}

	}

	private void initDP() {
		DP = new int[n][10];

		int[] count = new int[10];

		for (int i = 0; i < n; i++) {
			int num = nums[i];
			count[num]++;

			// update the DP
			for (int j = 0; j < 10; j++) {
				DP[i][j] = count[j];
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
