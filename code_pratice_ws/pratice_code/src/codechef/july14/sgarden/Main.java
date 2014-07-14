package codechef.july14.sgarden;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	private int[] nums;
	private int MOD = 1000_000_007;

	private boolean[] visited;
	
	private List<Integer> primeList;
	private int[] factorsCount;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		initPrimes();

		for (int i=0;i<tests;i++) {
			int len = in.nextInt();

			nums = new int[len];

			for (int j = 0; j < len; j++) {
				nums[j] = in.nextInt();
			}

			int moves = findMoves();

			out.println(moves);
		}

	}

	// graph problem...divide in Strongly connected components.
	// answer = lcm(count1, count2, count3) ...

	private int findMoves() {
		visited = new boolean[nums.length + 1];
		factorsCount = new int[100001];

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (!visited[num]) {
				int countOfSCC = findNumbersInSCC(num);
				markFactorsCount(countOfSCC);
			}
		}

		return findLCM();
	}

	private int findLCM() {
		long prod = 1;

		for (int i = 2; i < factorsCount.length; i++) {
			if (factorsCount[i] > 0)
				prod = (prod * powMod(i, factorsCount[i])) % MOD;
		}

		return (int) prod;
	}

	private void markFactorsCount(int num) {
		if (num == 1)
			return;

		int N = num;
		for (int prime : primeList) {
			if (prime > num / prime)
				break;

			int cnt = 0;
			while (N % prime == 0) {
				cnt++;
				N /= prime;
			}

			factorsCount[prime] = Math.max(factorsCount[prime], cnt);
		}

		if (N > 1)
			factorsCount[N] = Math.max(factorsCount[N], 1);
	}

	private void initPrimes() {
		int range = 1000;
		int sqrt = (int) (Math.sqrt(range)) + 1;
		boolean[] primes = new boolean[range + 1];
		Arrays.fill(primes, true);

		for (int i = 2; i < sqrt; i++) {
			for (int j = i * i; j < range ; j += i) {
				primes[j] = false;
			}
		}

		primeList = new ArrayList<>();
		primeList.add(2);

		for (int i = 3; i <= range; i += 2) {
			if (primes[i])
				primeList.add(i);
		}
	}

	private int findNumbersInSCC(int num) {
		int count = 0;
		while (!visited[num]) {
			visited[num] = true;
			// get the next number --> jump to next index and get the number there.
			num = nums[num - 1]; // 1 based index.
			count++;
		}

		return count;
	}

	private int powMod(long n ,long p) {
		long pow = 1;

		while (p > 0) {
			if ((p&1) == 1) {
				pow = (pow * n) % MOD;
			}

			n = (n * n) % MOD;
			p /= 2;
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
