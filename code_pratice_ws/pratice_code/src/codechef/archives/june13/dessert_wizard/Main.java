package codechef.archives.june13.dessert_wizard;

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

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		int[] num;
		for (int i=0;i<tests;i++) {
			int len = in.nextInt();
			num = new int[len];
			for (int j = 0; j < len; j++) {
				num[j] = in.nextInt();
			}

			long diff = getMaxSubArrayDifference(num);

			out.println(diff);
		}

	}

	long[] maxFwd;
	long[] maxRev;
	long[] minFwd;
	long[] minRev;

	public long getMaxSubArrayDifference(int[] num) {

		createMaxMinFwdRev(num);

		long bestDiff = Long.MIN_VALUE;

		// test for max on left side and min on right side
		for (int i = 0; i < num.length-1; i++) {
			long diff = maxFwd[i] - minRev[i+1];
			diff = Math.abs(diff);
			bestDiff = Math.max(diff, bestDiff);
		}

		// test for min on left side and max on right side
		for (int i=0;i<num.length-1;i++) {
			long diff = minFwd[i] - maxRev[i+1];
			diff = Math.abs(diff);
			bestDiff = Math.max(diff, bestDiff);
		}

		return bestDiff;
	}

	private void createMaxMinFwdRev(int[] num) {
		int len = num.length;

		// create maxFwd
		maxFwd = new long[len];
		long max_till_here = num[0];
		long max_so_far = num[0];
		maxFwd[0] = max_so_far;
		for (int i=1;i<len;i++) {
			if (max_till_here < 0) {
				max_till_here = num[i];
			} else {
				max_till_here += num[i];
			}

			if (max_till_here >= max_so_far) {
				max_so_far = max_till_here;
			}

			maxFwd[i] = max_so_far;
		}

		// create maxRev
		maxRev = new long[len];
		max_till_here = num[len-1];
		max_so_far = num[len-1];
		maxRev[len-1] = max_so_far;

		for (int i=len-2;i >=0;i--) {
			if (max_till_here < 0) {
				max_till_here = num[i];
			} else {
				max_till_here += num[i];
			}

			if (max_till_here >= max_so_far) {
				max_so_far = max_till_here;
			}

			maxRev[i] = max_so_far;
		}

		// create minFwd
		minFwd = new long[len];
		long min_till_here = num[0];
		long min_so_far = num[0];
		minFwd[0] = min_so_far;

		for (int i=1;i<len;i++) {
			if (min_till_here > 0) {
				min_till_here = num[i];
			} else {
				min_till_here += num[i];
			}

			if (min_till_here <= min_so_far) {
				min_so_far = min_till_here;
			}
			minFwd[i] = min_so_far;
		}

		// create minRev
		minRev = new long[len];
		min_till_here = num[len-1];
		min_so_far = num[len-1];
		minRev[len-1] = min_so_far;

		for (int i=len-2;i>=0;i--) {
			if (min_till_here > 0) {
				min_till_here = num[i];
			} else {
				min_till_here += num[i];
			}

			if (min_till_here <= min_so_far) {
				min_so_far = min_till_here;
			}
			minRev[i] = min_so_far;
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
