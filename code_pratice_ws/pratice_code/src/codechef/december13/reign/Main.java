package codechef.december13.reign;

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

	private int N;
	private int K;
	private int[] nums;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			K = in.nextInt();

			nums = new int[N];
			for (int j = 0; j < N; j++) {
				nums[j] = in.nextInt();
			}

			long maxProperSum = getProperYearsSum();
			out.println(maxProperSum);
		}

	}

	private long[] fwdMaxSubArray;
	private long[] revMaxSubArray;

	private long getProperYearsSum() {
		createFwdRevMaxes();

		long maxProsperSum = Long.MIN_VALUE;

		int otherIdx;
		for (int i = 0; i < N; i++) {
			otherIdx = i + K + 1;
			if (otherIdx >= N)
				break;

			long maxSum = fwdMaxSubArray[i] + revMaxSubArray[otherIdx];
			maxProsperSum = Math.max(maxProsperSum, maxSum);
		}


		return maxProsperSum;
	}

	private void createFwdRevMaxes() {
		// create forward maxes.
		fwdMaxSubArray = new long[N];
		fwdMaxSubArray[0] = nums[0];
		long maxUsingThis = nums[0];
		long maxSoFar = nums[0];

		for (int i = 1; i < N; i++) {
			if (maxUsingThis < 0) {
				maxUsingThis = nums[i];
			}
			else {
				maxUsingThis += nums[i];
			}

			maxSoFar = Math.max(maxUsingThis, maxSoFar);
			fwdMaxSubArray[i] = maxSoFar;
		}

		// create reverse maxes
		revMaxSubArray = new long[N];
		revMaxSubArray[N-1] = nums[N-1];
		maxUsingThis = nums[N-1];
		maxSoFar = nums[N-1];

		for (int i = N - 2; i >= 0 ; i--) {
			if (maxUsingThis < 0) {
				maxUsingThis = nums[i];
			}
			else {
				maxUsingThis += nums[i];
			}

			maxSoFar = Math.max(maxSoFar, maxUsingThis);
			revMaxSubArray[i] = maxSoFar;
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
