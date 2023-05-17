package codechef.october14.prpotion;

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

	private int R;
	private int G;
	private int B;
	private int M;

	private int[] reds;
	private int[] greens;
	private int[] blues;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			R = in.nextInt();
			G = in.nextInt();
			B = in.nextInt();
			M = in.nextInt();

			reds = new int[R];
			greens = new int[G];
			blues = new int[B];

			for (int j = 0; j < R; j++) {
				reds[j] = in.nextInt();
			}

			for (int j = 0; j < G; j++) {
				greens[j] = in.nextInt();
			}

			for (int j = 0; j < B; j++) {
				blues[j] = in.nextInt();
			}

			int minMaxima = getMin1();
			out.println(minMaxima);
		}

	}

	// greedy ...divide the group having max value.
	private int getMin1() {

		int minimumMaxima = Integer.MAX_VALUE;

		int maxRed = getMax(reds);
		int maxGreen = getMax(greens);
		int maxBlue = getMax(blues);

		int max = Math.max(maxRed, Math.max(maxGreen, maxBlue));
		minimumMaxima = Math.min(max, minimumMaxima);

		for (int i = 0; i < M; i++) {
			if (maxRed >= maxGreen && maxRed >= maxBlue) {
				divideBy2(reds);
				add1(greens);
				add1(blues);
			}
			else if (maxGreen >= maxRed && maxGreen >= maxBlue) {
				divideBy2(greens);
				add1(reds);
				add1(blues);
			}
			else {
				divideBy2(blues);
				add1(reds);
				add1(greens);
			}

			maxRed = getMax(reds);
			maxGreen = getMax(greens);
			maxBlue = getMax(blues);

			max = Math.max(maxRed, Math.max(maxGreen, maxBlue));
			minimumMaxima = Math.min(max, minimumMaxima);
		}

		return minimumMaxima;
	}

	private void divideBy2(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			nums[i] = nums[i] / 2;
		}
	}

	private void add1(int[] nums) {
		return; // problem statement was updated.
//		for (int i = 0; i < nums.length; i++) {
//			nums[i]++;
//		}
	}

	private int getMax(int[] nums) {
		int max = -1;
		for (int i = 0; i < nums.length; i++) {
			max = Math.max(nums[i], max);
		}
		return max;
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
