package codechef.november13.superpower_2;

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

	int MOd = 1000_000_007;

	int[] preCalculateValues;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		initPreCalculateValues();

		int tests = in.nextInt();

//		int num = 600000;
//		System.out.println("bit representation " + Integer.toBinaryString(num));
//		1001 0010 0111 1100 0000 -- it has 20 digits...
// 		hence we need to precalculate till 2 ^ (10^19)

		for (int i=0;i<tests;i++) {
			int N = in.nextInt();
			int superPower = getSuperPowerTwo(N);
			out.println(superPower);
		}


	}

	private int getSuperPowerTwo(int num) {
		int rotator = 1;

		long superPower = 1;

		for (int i = 0; i < 20; i++) {
			int check = num & rotator;
			if (check!=0) {
				superPower = (superPower * preCalculateValues[i]) % MOd;
			}

			rotator = rotator << 1; // shift the 1 to left.
		}

		// accounting for the raise to 2
		superPower = (superPower * superPower) % MOd;

		return (int) superPower;
	}

	private void initPreCalculateValues() {
		preCalculateValues = new int[20];

		preCalculateValues[0] = 2;

		for (int i = 1; i < preCalculateValues.length; i++) {
			preCalculateValues[i] = powMod(preCalculateValues[i-1], 10);
		}
	}

	private int powMod(long num, int pow) {
		long power = 1;
		while (pow > 0) {
			if ((pow&1)==1) {
				power = (power * num)%MOd;
			}

			num = (num * num)%MOd;
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
