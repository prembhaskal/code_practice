package codechef.nov14.powermul;

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

// this is still damn slow (and requires more memory) to get the 100 points
class TaskA {

	private List<Integer> primes = new ArrayList<>();
	private int range = 501;
	private Map<Integer, Map<Integer, Integer>> numVsFactorMap = new HashMap<>();
	private int MOD;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();
		initPrimes();
		initNumVsFactorMap();
		initSimpleN();

		initFuncVsFactorCount();

		for (int i=0;i<tests;i++) {
			int n = in.nextInt();
			MOD = in.nextInt();
			int Q = in.nextInt();

//			initNRaiseN();

			for (int j = 0; j < Q; j++) {
				int r = in.nextInt();
				int result;
				result = getAnsStoringAnswers(n, r);
				out.println(result);
//				result = getAnsPrimeFactorsApproach(n ,r);
//				out.println(result);
			}
		}

	}

	private int smallRange = 501;

	private int[] NRaiseN = new int[6];
	private int[] fofN = new int[6];

	private void initSimpleN() {
		NRaiseN[1] = 1;
		fofN[1] = 1;
		for (int i = 2; i < 6; i++) {
			NRaiseN[i] = pow(i, i);
			fofN[i] = (fofN[i - 1]) * ( NRaiseN[i]);
		}
	}

	private int getSimpleAns(int n, int r) {
		int numer = fofN[n];
		int denom = fofN[r] * fofN[n-r];
		int res = numer / denom;
		res = (res % MOD);
		return res;
	}

	private int pow(int num, int p) {
		int pow = 1;
		for (int i = 0; i < p; i++) {
			pow = pow * num;
		}

		return pow;
	}

	private int getAnsStoringAnswers(int n, int r) {
		if (n <= 5)
			return getSimpleAns(n , r);

		if (n <= 500)
			return getAnsLessThan500(n, r);

		return 0;
	}

	// store the total factor count for number for each f(N).
	private int[][] funcVsFactorCount = new int[501][501];

	private void initFuncVsFactorCount() {

		funcVsFactorCount[1][1] = 1;
		for (int num = 2; num < 501; num++) {

			Map<Integer, Integer> factorMap  = numVsFactorMap.get(num);

			// copy previous counts
			for (int factor = 0; factor < 501; factor++) {
				funcVsFactorCount[num][factor] = funcVsFactorCount[num-1][factor];
			}

			// increment for present number
			for (Map.Entry<Integer, Integer> entry : factorMap.entrySet()) {
				int factor = entry.getKey();
				int count = entry.getValue();
				funcVsFactorCount[num][factor] += (count * num); // N^N. so multiply powers.
			}
		}
	}

	// this approach works for nos less than 500.
	private int getAnsLessThan500(int n, int r) {
		int[] numerFact= new int[501];
		int[] denomFact = new int[501];

		for (int i = 1; i < 501; i++) {
			numerFact[i] = funcVsFactorCount[n][i];
		}

		for (int i = 1; i < 501; i++) {
			denomFact[i] = funcVsFactorCount[r][i];
			denomFact[i] += funcVsFactorCount[n-r][i];
		}

		for (int i = 1; i < 501; i++) {
			numerFact[i] -= denomFact[i];
		}

		long ans = 1;
		for (int i = 2; i < 501; i++) {
			if (numerFact[i] > 0) {
				int pow = powMod(i, numerFact[i]);
				ans = (ans * pow) % MOD;
			}
		}

		return (int) ans;
	}


	private int getMulInverse(int num) {
		return powMod(num, MOD - 2);
	}



	// times out....obvious as 0(q * range) implies O(10^10)
	private int getAnsPrimeFactorsApproach(int n, int r) {
		long[] numerFactorCount = new long[range];
		long[] denomFactorCount = new long[range];

		while (r > 0) {
			updateFactorCountArray(n, numerFactorCount);
			updateFactorCountArray(r, denomFactorCount);
			n--;
			r--;
		}

		// cancel numerator vs denominator
		for (int i = 2; i < range; i++) {
			numerFactorCount[i] = numerFactorCount[i] - denomFactorCount[i];
		}

		long result = 1;
		for (int i = 2; i < range; i++) {
			if (numerFactorCount[i] > 0) {
				int ans = powMod(i, numerFactorCount[i]);
				result = (result * ans) % MOD;
			}
		}

		return (int) result;
	}

	private void updateFactorCountArray(int num, long[] totalFactorCount) {
		if (num == 1)
			return;
		Map<Integer, Integer> numFactorCount = numVsFactorMap.get(num);

		for (Map.Entry<Integer, Integer> entry : numFactorCount.entrySet()) {
			int factor = entry.getKey();
			int count = entry.getValue();

			long totalCount = count * (long)num;
			totalFactorCount[factor] += totalCount;
		}
	}

	private void initNumVsFactorMap() {
		for (int i = 2; i < 100000; i++) {
			Map<Integer, Integer> factorMap = getFactorMap(i);
			numVsFactorMap.put(i, factorMap);
		}
	}

	private Map<Integer, Integer> getFactorMap(int num) {
		Map<Integer, Integer> factorMap = new HashMap<>();
		for (int prime : primes) {
			if (prime > num / prime)
				break;

			int k = 0;
			while (num % prime == 0) {
				num /= prime;
				k++;
			}

			if (k > 0) {
				factorMap.put(prime, k);
			}
		}

		if (num > 1) {
			factorMap.put(num, 1);
		}
		return factorMap;
	}

	private void initPrimes() {
		int sqrt = (int) Math.sqrt(range) + 1;
		boolean[] flags = new boolean[range];

		Arrays.fill(flags, true);

		for (int i = 2; i < sqrt; i++) {
			for (int j =  i * i; j < range; j += i) {
				flags[j] = false;
			}
		}

		primes.add(2);
		for (int i = 3; i < flags.length; i += 2) {
			if (flags[i])
				primes.add(i);
		}
	}

	private int powMod(long n, long k) {
		long pow = 1;
		while (k > 0) {
			if ((k&1)==1) {
				pow = (pow * n) % MOD;
			}

			k /= 2;
			n = (n * n) % MOD;
		}

		return (int) pow;
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
