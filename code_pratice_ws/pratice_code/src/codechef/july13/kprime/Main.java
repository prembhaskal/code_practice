package codechef.july13.kprime;

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
		intialiseFactors();

//		check();

		int tests = in.nextInt();
		for (int i=0;i<tests;i++) {
			int A = in.nextInt();
			int B = in.nextInt();
			int k = in.nextInt();

//			int kprimes = getKPrimesBrute(A, B, k);
			int kprimes = getKPrimes(A, B, k);
			out.println(kprimes);
		}

	}

	// this is to check the correctness of the solution.
	private void check() {
		for (int i = 2; i < 1000; i++) {
			for (int j = i+1; j < 1000; j++) {
				for (int k=1;k<6;k++) {
					int v1 = getKPrimesBrute(i,j,k);
					int v2 = getKPrimes(i,j,k);

					if (v1!=v2) {
						System.out.println("should not reach here");
					}
				}
			}
		}

	}

	// SEXY OPTIMISATION.......
	private int getKPrimes(int A, int B, int k) {
		int kprimes = 0;

		int k1 = factorSum[A-1][k];

		int k2 = factorSum[B][k];

		kprimes = k2 - k1;

		return kprimes;
	}

	private int getKPrimesBrute(int A, int B, int k) {
		int kprimes = 0;

		for (int i = A; i <=B ; i++) {
			if (factorCount[i]==k)
				kprimes++;
		}

		return kprimes;
	}

	private int[] factorCount = new int[100001];
	private int[][] factorSum = new int[100001][6];

	private void intialiseFactors() {
		factorCount = new int[100001];

		for (int i=2;i<=100000;i++) {
			trialDivision(i);
		}

		int[] count = new int[6];

		// initialize the factorSum
		// stores the k primes count till here...
		for (int i = 2; i < 100001; i++) {
			int k = factorCount[i];
			if (k < 6)
				count[k]++;
			for (int j = 1; j < 6; j++) {
				factorSum[i][j] = count[j];
			}
		}
	}

	private void trialDivision(int num) {
		int act = num;
		int i=2;
		int distinctPwr = 0;

		for (;i<=num/i;i++) {
			if (num%i==0) {
				distinctPwr++;

				if (distinctPwr > 5) {
					factorCount[act] = distinctPwr;
					return;
				}

				while (num%i==0) {
					num /= i;
				}
			}
		}

		if (num > 1)
			distinctPwr++;

		factorCount[act] = distinctPwr;
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
