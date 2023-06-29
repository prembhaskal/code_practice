package codechef.jan15.gcdq;

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
	private int Q;
	private int[] nums;

	private int[] leftGCDs;
	private int[] rightGCDs;
	// accepted.
	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			Q = in.nextInt();

			nums = new int[N];
			for (int j = 0; j < N; j++) {
				nums[j] = in.nextInt();
			}

			initGCDs();

			for (int j = 0; j < Q; j++) {
				int L = in.nextInt();
				int R = in.nextInt();

				L--;R--;

				out.println(getGCDOfSegment(L, R));
			}
		}

	}

	private int getGCDOfSegment(int left, int right) {
		left--;

		right++;

		if (left < 0) {
			return rightGCDs[right];
		}
		else if (right >= N) {
			return leftGCDs[left];
		}
		else {
			return getGCD(leftGCDs[left], rightGCDs[right]);
		}
	}

	private void initGCDs() {
		leftGCDs = new int[N];

		leftGCDs[0] = nums[0];
		for (int i = 1; i < N; i++) {
			leftGCDs[i] = getGCD(nums[i], leftGCDs[i-1]);
		}

		rightGCDs = new int[N];
		rightGCDs[N-1] = nums[N-1];
		for (int i = N - 2; i >= 0; i--) {
			rightGCDs[i] = getGCD(nums[i], rightGCDs[i+1]);
		}
	}

	private int getGCD(int a, int b) {

		while (true) {
			int rem = a % b;
			if (rem == 0)
				break;
			a = b;
			b = rem;
		}

		return b;
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
