package codechef.september14.uaseq;

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

// epic ...this got accepted.... no crazy logic..just plain logic.
class TaskA {

	private int N;
	private int[] nums;
	private int K;

	private long start;
	private long diff;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		N = in.nextInt();
		K = in.nextInt();

		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = in.nextInt();
		}

		findAP();

		// print the AP
		int num = (int) start;
		out.print(num);
		for (int i = 1; i < N; i++) {
			num = num + (int)diff;
			out.print(" " + num);
		}
		out.println();
	}

	private void findAP() {
		// since K <= 10, for N > 100, the max occurring AP would be the answer.
		if (N > 100) {
			findMostOccuringAP();
			return;
		}

		if (K == 0) {
			start = nums[0];
			diff = nums[1] - nums[0];
			return;
		}

		// only smaller aps.
		AP bestAP = null;

		// since K < N-2, atleast one of the adjacent pair will belong to final result...try to get for all of these.
		for (int i = 0; i < N - 1; i++) {
			long a = nums[i];
			long b = nums[i + 1];

			// diff
			long dif = (b - a);
			long first = a - i * dif;

			// check if this AP is even valid.
			if (!isValidAP(first, (int) dif, K))
				continue;

			AP presentAp = new AP(first, dif);
			if (bestAP == null) {
				bestAP = presentAp;
			}
			else {
				if (presentAp.compareTo(bestAP) == -1) {
					bestAP = presentAp;
				}
			}
		}

		start = bestAP.a;
		diff = bestAP.d;
	}

	private boolean isValidAP(long a, int d, int k) {
		if (a > Integer.MAX_VALUE || a < Integer.MIN_VALUE) {
			return false;
		}

		int[] newArray = new int[N];
		newArray[0] = (int) a;
		for (int i = 1; i < N; i++) {
			newArray[i] = newArray[i-1] + d;
		}

		// find difference from actual array
		for (int i = 0; i < N; i++) {
			if (nums[i] != newArray[i])
				k--;
		}

		if (k < 0)
			return false;

		return true;
	}

	// if N > 100, just take the most occurring AP.
	private void findMostOccuringAP() {

		Map<AP, Integer> apCount = new HashMap<>();
		int maxCount = -1;
		AP maxAP = null;

		for (int i = 0; i < N - 1; i++) {
			long a = nums[i];
			long b = nums[i + 1];

			// diff
			long dif = (b - a);
			long first = a - i * dif;

			AP ap = new AP(first, dif);
			Integer count = apCount.get(ap);
			if (count == null) {
				count = 0;
			}
			count = count + 1;
			apCount.put(ap, count);

			if (count > maxCount) {
				maxCount = count;
				maxAP = ap;
			}
		}

		start = maxAP.a;
		diff = maxAP.d;
	}


	private class AP implements Comparable<AP>{
		long a;
		long d;

		public AP(long a, long d) {
			this.a = a;
			this.d = d;
		}

		@Override
		public int hashCode() {
			return (int) (31 * a + d);
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof AP))
				return false;
			AP other = (AP) obj;
			if (this.a != other.a)
				return false;
			if (this.d != other.d)
				return false;
			return true;
		}


		@Override
		public int compareTo(AP o) {
			if (this.a < o.a)
				return -1;
			else if (this.a == o.a && this.d < o.d)
				return -1;
			else
				return 1;
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
