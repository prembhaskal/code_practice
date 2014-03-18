package codechef.march14.tmslt;

import java.io.*;
import java.util.*;

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
	private int a;
	private int b;
	private int c;
	private int d;

	private int nums[];

	private int MOD = 1000000;


	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			a = in.nextInt();
			b = in.nextInt();
			c = in.nextInt();
			d = in.nextInt();

			formNumbers();

			long absDiff = getAbsDiff1();

			out.println(absDiff);
		}

	}

	private long getAbsDiff1() {
		// all nos will be less than the MOD
		boolean[] freq = new boolean[MOD];

		for (int i = 0; i < N; i++) {
			int num = nums[i];
			// normalize the frequencies... since even strengths will cancel each other.
			freq[num] = !freq[num];
		}

		List<Integer> nos = new ArrayList<>();
		for (int i = 0; i < MOD; i++) {
			if (freq[i])
				nos.add(i);
		}

		// no need to sort...:) :)
//		Collections.sort(nos);

		// we can start from 0 and not the end because we just need to make sure that two team take separate players..
		// does not matter which team starts picking first.
		long team1 = 0;
		for (int i = 0; i < nos.size(); i += 2) {
			team1 += nos.get(i);
		}

		long team2 = 0;
		for (int i = 1; i < nos.size(); i += 2) {
			team2 += nos.get(i);
		}

		return Math.abs(team2 - team1);
	}

	private void formNumbers() {
		nums = new int[N];

		nums[0] = d;

		for (int i = 1; i < N; i++) {
			int num = (a * nums[i - 1] + b) % MOD;
			num = (int) ((num * (long) nums[i - 1] + c) % MOD);
			nums[i] = num;
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
