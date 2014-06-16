package codechef.june14.maxpr;

import java.io.*;
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
	private int[] nums;
	private int MAX_VAL = 100;
	private int MOD = 1000_000_007;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();

			nums = new int[N];

			for (int j = 0; j < N; j++) {
				nums[j] = in.nextInt();
			}

			int totalNonAps;
//			totalNonAps = getCountOfNonAPs();
//			out.println(totalNonAps);

			totalNonAps = getCountUsingArray();
			out.println(totalNonAps);
		}

	}

	// non ap = total - ap
	// this is timing out. :) may be cause of map. .. yes definitely due to map confirmed.
	private int getCountOfNonAPs() {
		int totalSubSequences = powMod(2, N);

		// finding the aps
		Map<EndAtDiff, Integer> mapCountEndingAtVsDiff = new HashMap<>();
		int[] countOfNos = new int[MAX_VAL + 1];

		// initialize the map
		for (int i = 1; i <= MAX_VAL; i++) {
			for (int diff = -MAX_VAL + 1; diff < MAX_VAL; diff++) {
				EndAtDiff endAtDiff = new EndAtDiff(i, diff);
				mapCountEndingAtVsDiff.put(endAtDiff, 0);
			}
		}


		for (int i = 0; i < N; i++) {
			int num = nums[i];
			//j denotes previous number
			for (int prev = 1; prev <= MAX_VAL; prev++) {
				int diff = num - prev;

				EndAtDiff previousEnd = new EndAtDiff(prev, diff);
				int cnt_j_diff = mapCountEndingAtVsDiff.get(previousEnd);
				int countOfJ = countOfNos[prev];

				int cnt_i_diff = (int) ((cnt_j_diff + (long)countOfJ) % MOD);
				EndAtDiff thisEnd = new EndAtDiff(num, diff);
				int existingCount = mapCountEndingAtVsDiff.get(thisEnd);
				cnt_i_diff = (int) (((long)cnt_i_diff + existingCount) % MOD);
				mapCountEndingAtVsDiff.put(thisEnd,cnt_i_diff);
			}

			// count of nos
			countOfNos[num]++;
		}

		// so we count total aps.
		long totalAPs = 1 + N; // empty set plus single nos.

		for (int i = 1; i <= MAX_VAL; i++) {
			for (int j = 1; j <= MAX_VAL; j++) {
				int diff = i - j;
				int count = mapCountEndingAtVsDiff.get(new EndAtDiff(i, diff));
				totalAPs = (totalAPs + count) % MOD;
			}
		}

		long totalNonAPs = (totalSubSequences - totalAPs) % MOD;
		totalNonAPs = (totalNonAPs + MOD) % MOD;

		return (int) totalNonAPs;
	}

	// this work but is still quite slower..not sure why -- all this mod made it slower.
	// after removing them made it faster.
	private int getCountUsingArray() {
		int totalSubSequence = powMod(2, N);

		// this array store the count of sequence ending at i and part of sequence with diff 'j'
		int[][] countArray = new int[MAX_VAL + 1][2 * MAX_VAL + 1];
		int[] countOfNos = new int[MAX_VAL + 1];

		for (int i = 0; i < N; i++) {
			int num = nums[i];

			for (int prev = 1; prev <= MAX_VAL; prev++) {
				int diff = num - prev + MAX_VAL;  // correct to avoid the -1;

				countArray[num][diff] += countArray[prev][diff];
				if (countArray[num][diff] >= MOD)
					countArray[num][diff] -= MOD;

				if (countOfNos[prev] > 0)
					countArray[num][diff] += countOfNos[prev];

				if (countArray[num][diff] >= MOD)
					countArray[num][diff] -= MOD;

				// countOfJ to map self with just the previous guy. (ie 2 guys)
				// cnt_j_diff for previous sequence of length > 2.
			}

			countOfNos[num]++;
		}

		// so we count total aps.
		long totalAPs = 1 + N; // empty set plus single nos.

		for (int i = 1; i <= MAX_VAL; i++) {
			for (int j = 1; j <= MAX_VAL; j++) {
				int diff = i - j + MAX_VAL;
				totalAPs = (totalAPs + countArray[i][diff]);
				if (totalAPs >= MOD)
					totalAPs -= MOD;
			}
		}

		long totalNonAPs = (totalSubSequence - totalAPs) % MOD;
		totalNonAPs = (totalNonAPs + MOD) % MOD;

		return (int) totalNonAPs;
	}

	private int powMod(long num, int pow) {
		long power = 1;
		while (pow > 0) {
			if ((pow&1)==1) {
				power = (power * num) % MOD;
			}
			num = (num * num) % MOD;
			pow /= 2;
		}

		return (int) power;
	}

	private class EndAtDiff {
		int endAt;
		int diff;

		public EndAtDiff(int endAt, int diff) {
			this.endAt = endAt;
			this.diff = diff;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			EndAtDiff endAtDiff = (EndAtDiff) o;

			if (diff != endAtDiff.diff) return false;
			if (endAt != endAtDiff.endAt) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = endAt;
			result = 31 * result + diff;
			return result;
		}

		@Override
		public String toString() {
			return "EndAtDiff{" +
					"endAt=" + endAt +
					", diff=" + diff +
					'}';
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
