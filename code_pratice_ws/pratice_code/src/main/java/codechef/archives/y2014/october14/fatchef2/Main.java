package codechef.october14.fatchef2;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
	private int M;

	private int[] colors;
	private int[] buckets;

	private final int MOD = 1000000009;

	// assumes the buckets are given in proper order.
	// TODO FIXME above assertion is incorrect. input can be in different order.
	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			M = in.nextInt();

			colors = new int[N];
			buckets = new int[N]; // first color and then bucket idx.

			for (int j = 0; j < M; j++) {
				int color = in.next().charAt(0);
				int idx = in.nextInt() - 1; // input is 1 based index.

				colors[idx] = color;
				buckets[idx] = 1; // indicates that there exits bucket here.
			}

//			int colors = getColorCombinations2();
			int colors = getCombinationOnlyMultiplication();
			out.println(colors);

//			if (colors < 0) {
//				throw new RuntimeException("ERROR");
//			}
		}

	}

	// straight multiplication works  and is faster too. :)
	private int getCombinationOnlyMultiplication() {
		if (M == 1)
			return 1;
		if (M == N)
			return 1;


		int i;
		for (i = 0; i < N; i++) {
			if (buckets[i] == 1) {
				break;
			}
		}

		int prevIdx = i;
		int prevColor = colors[i];
		i++;

		long ans = 1;

		for ( ; i < N; i++) {
			if (buckets[i] != 1) {
				continue;
			}

			int currColor = colors[i];
			int currIdx = i;

			if (currColor != prevColor) {
				int diff = currIdx - prevIdx;
				ans = (ans * diff) % MOD;
			}

			prevColor = currColor;
			prevIdx = currIdx;
		}

		return (int) ans;
	}

	// simple plain, the guy doesn't stop, so multiply all fragments together....using usual num-freq-fastpow method.
	private int getColorCombinations2() {
		if (M == 1)
			return 1;
		if (M == N)
			return 1;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		int i;
		for (i = 0; i < N; i++) {
			if (buckets[i] == 1) {
				break;
			}
		}

		int prevIdx = i;
		int prevColor = colors[i];
		i++;

		for ( ; i < N; i++) {
			if (buckets[i] != 1) {
				continue;
			}

			int currColor = colors[i];
			int currIdx = i;

			if (currColor != prevColor) {
				int diff = currIdx - prevIdx;
//				if (diff == 1)
//					continue;

				// update the map.
				Integer freq = map.get(diff);
				if (freq == null) {
					freq = 0;
				}
				map.put(diff, freq + 1);
			}


			prevColor = currColor;
			prevIdx = currIdx;
		}

		long totalWays = 1;

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int num = entry.getKey();
			int power = entry.getValue();

			if (num == 1)
				continue;

			int pow = powMod(num, power);

			totalWays = (totalWays * pow) % MOD;
		}


		return (int) totalWays;
	}

	private int powMod(long num, int pow) {
		long power = 1;
		while (pow > 0) {
			if ((pow&1) == 1) {
				power = (power * num) % MOD;
			}

			num = (num * num) % MOD;

			pow /= 2;
		}

		return (int) power;
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
