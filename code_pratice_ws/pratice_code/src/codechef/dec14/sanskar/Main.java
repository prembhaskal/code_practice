package codechef.dec14.sanskar;

import java.io.*;
import java.util.ArrayList;
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

//	long[] sums
	int N;
	int K;
	long[] nums;

	long reqdChunk;

	int[][] DP;

	int twoPowerN;

	public void solve(InputReader in, PrintWriter out) throws IOException {

//		storeSums();
		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			K = in.nextInt();

			twoPowerN = 1 << N;

			nums = new long[N];
			for (int j = 0; j < N; j++) {
				nums[j] = in.nextLong();
			}

			boolean isPossible = isPossible();
			if (isPossible)
				out.println("yes");
			else
				out.println("no");
		}
	}

	private boolean isPossible() {
		if (K == 1)
			return true;
		if (N < K)
			return false;

		long total = 0;
		for (int i = 0; i < N; i++) {
			total += nums[i];
		}

		long rem = total % K;
		if (rem != 0)
			return false;

		reqdChunk = total / K;

//		DP = new int[1<<N][K];
//		for (int i = 0; i < 1 << N; i++) {
//			for (int j = 0; j < K; j++) {
//				DP[i][j] = -1;
//			}
//		}
//		int val = 0;//findSol(0, 0);
//		if (val > 0)
//			return true;
//		return false;

		masks = getMasks();
//		func(0, 0,0);

		return POSSIBLE;
	}

	private List<Integer> getMasks() {
		List<Integer> maskList = new ArrayList<>();
		for (int mask = 1; mask < twoPowerN; mask++) {
			long sum = getSum(mask);
			if (sum == reqdChunk) {
				maskList.add(mask);
			}
		}

		return maskList;
	}

	private List<Integer> masks;
	private boolean POSSIBLE = false;
	// this times out too :( :( , obvious since the complexity is 0(2 ^ totalMasks)
	// and totalMasks possible can be > 100.
	private void func(int netMask, int totalMaskUsed, int maskNo) {
		if (POSSIBLE)
			return;

		if (totalMaskUsed == K && netMask == twoPowerN) {
			POSSIBLE = true;
			return;
		}
		if (maskNo >= masks.size()) {
			return;
		}
		//use this mask
		int currentMask = masks.get(maskNo);
		if ((netMask & currentMask) == 0) {
			func(netMask | currentMask, totalMaskUsed + 1, maskNo + 1);
		}
		// don't use this mask.
		func(netMask, totalMaskUsed, maskNo + 1);
	}

	// this times out.....:( :(
	private int findSol(int usedMask, int bkt) {
		if (bkt >= K)
			return 1;

		if (DP[usedMask][bkt] != -1)
			return DP[usedMask][bkt];

		int val = 0;
		int total = 1 << N;
		for (int newMask = 1; newMask < total; newMask++) {
			if ((usedMask & newMask) != 0) // item already used
				continue;

			long chunkSum = getSum(newMask);
			if (chunkSum != reqdChunk) {
				continue;
			}

			val = findSol(usedMask | newMask, bkt + 1);
			if (val > 0)
				break;
		}

		if (val > 0)
			DP[usedMask][bkt] = 1;
		else
			DP[usedMask][bkt] = 0;

		return DP[usedMask][bkt];
	}

	private long getSum(int mask) {
		int pointer = 1;
		long sum = 0;
		for (int j = 0; j < N; j++) {
			if ((mask & pointer) != 0) {
				sum += nums[j];
			}
			pointer = pointer << 1;
		}

		return sum;
	}

	private void storeSums() {

		int n = 21;
		int totalComb = 1 << n;
		long[] nums = new long[n];

		long[] sums = new long[totalComb];

		for (int i = 0; i < n; i++) {
			nums[i] = 1000_000_000_0L;
		}

		for (int mask = 1; mask < totalComb; mask++) {
			int pointer = 1;
			long sum = 0;
			for (int j = 0; j < n; j++) {
				if ((mask & pointer) != 0) {
					sum += nums[j];
					pointer = pointer << 1;
				}
			}

			sums[mask] = sum;
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
