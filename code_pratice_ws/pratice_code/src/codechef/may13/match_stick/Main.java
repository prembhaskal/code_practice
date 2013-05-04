package codechef.may13.match_stick;

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

	int[] burnTimes;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int N = in.nextInt();

		burnTimes = new int[N];

		for (int i=0;i<N;i++) {
			burnTimes[i] = in.nextInt();
		}

		init();

		int queries = in.nextInt();

		for (int i=0;i<queries;i++) {
			int low = in.nextInt();
			int high = in.nextInt();

			String time = getBurnTime(low, high);
			out.println(time);
		}
	}

	private void init() {

	}


	private String getBurnTime(int low, int high) {
		// get max is burning area
		int maxBurn = getMaximum(low, high);
		// min in burning area
		int minBurn = getMinimum(low, high);
		// max in non burn area
		int maxNonBurn = getMaxInNonBurn(low, high);

		double time1 = minBurn + maxNonBurn;
		double time2 = minBurn + ((double)maxBurn-minBurn)/2;

		double time = Math.max(time1, time2);

		String resultTime = convertToDecimal(time);
		return resultTime;
	}

	private String convertToDecimal(double time) {
		int timeInt = (int)time;
		int doubleTime = (int)(time * 2);

		if (doubleTime==timeInt*2) {
			return timeInt + "" + ".0";
		} else {
			return timeInt + "" + ".5";
		}
	}

	private int getMaxInNonBurn(int low, int high) {
		int max = -1;
		if (low > 0) {
			max = getMaximum(0, low-1);
		}

		if (high < burnTimes.length-1) {
			max = Math.max(max, getMaximum(high+1,burnTimes.length-1));
		}

		return max;
	}

	// inclusive range
	private int getMaximum(int low, int high) {
		if (low == high)
			return burnTimes[low];

		int max = -1;
		for (int i=low;i<=high;i++) {
			max = Math.max(burnTimes[i], max);
		}

		return max;
	}

	private int getMinimum(int low, int high) {
		if (low==high)
			return burnTimes[low];
		int min = Integer.MAX_VALUE;

		for (int i=low;i<=high;i++) {
			min = Math.min(burnTimes[i], min);
		}

		return min;
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
