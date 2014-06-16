package codechef.june14.func;

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

	int[] highExp;
	int MOD = 1000_000_007;
	int[] nums;
	long[] queries;
	int N;
	int Q;

	int[] sumFromEnd;

// TODO this is not working.... gives wrong answer.
	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		init();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			Q = in.nextInt();

			nums = new int[N];
			queries = new long[Q];
			// read nos.
			for (int j = 0; j < N; j++) {
				nums[j] = in.nextInt();
				if (nums[j] < 0)
					nums[j] = (nums[j] + MOD);
			}

			// init sum
			initSum();

			// read queries
			for (int j = 0; j < Q; j++) {
				queries[j] = in.nextLong();
			}

			// find results
			for (int j = 0; j < Q; j++) {
				int result;
//				result = getResult(queries[j]);
//				out.print(result);
				result = getResultNaive(queries[j]);
				out.print(result);
				out.print(" ");
			}
			out.println();
		}

	}

	private int getResult(long x) {
		long result = 0;
		for (int i = 0; i < N; i++) {
			int root = i + 1;
			long nthRoot;
			if (root == 1) {
				nthRoot = x;
			}
			else {
				nthRoot = findIntegerRootUsingBinarySearch(x, root);
			}
			// if root is 1 just add the remaining guys directly and STOP.
			if (nthRoot == 1) {
				result = (result + sumFromEnd[i]) % MOD;
				break;
			}

			long prod = (nthRoot * nums[i]) % MOD;

			result = (result + prod) % MOD;
		}

		return (int) result;
	}

	private int getResultNaive(long x) {
		long result = 0;

		for (int i = 0; i < N; i++) {
			int root = i + 1;

			long nthRoot;
			if (root == 1) {
				nthRoot = x;
			}
			else {
				nthRoot = (long) (Math.pow(x, 1.0/root) + 0.0000000000001);
			}

			if (nthRoot == 1) {
				result = (result + sumFromEnd[i]) % MOD;
				break;
			}

			long prod = (nthRoot * nums[i]) % MOD;
			result = (result + prod) % MOD;
		}

		return (int) result;
	}


	private void initSum() {
		sumFromEnd = new int[N];
		long sum = 0;
		for (int i = N - 1; i >= 0; i--) {
			sum = (sum + nums[i]) % MOD;
			sumFromEnd[i] = (int) sum;
		}
	}


	public int findIntegerRootUsingBinarySearch(long num, int root) {
		int low = 1;
		int high = highExp[root];
		int mid = 1;

		while (low <= high) {
			mid = (low + (high - low)/2);

			long power = pow(mid, root);
			if (power == num) {
				break;
			}

			if (power > num) {
				high = mid - 1;
			}
			else {
				// check if we have reached CEIL of root.
				long nextPower = pow(mid + 1, root);
				if (nextPower > num) {
					break;
				}

				low = mid + 1;
			}
		}

		return mid;
	}

	public long pow(long num, int pow) {
		long power = 1;

		while (pow > 0) {
			if ((pow&1) == 1) {
				power = (power * num);
			}
			num = (num * num);
			pow /= 2;
		}

		return power;
	}

	private void init() {
		// init highest expo
		highExp = new int[64];
		for (int i = 2; i < highExp.length; i++) {
			highExp[i] = (int)findHigestExpUsingPrecision(i);
		}
	}

	private double findHigestExpUsingPrecision(int root) {
		long max = 1000_000_000_000_000_007L;
		// we seek to find the solution to this equation. x^root = max;

		double highDouble = findNthRootNewtonMethod(max, root, 0.001);
		return highDouble;
	}


	private double findNthRootNewtonMethod(long num, int root, double precision) {
		double x = 1.0;
		double PRECISION = Math.min(precision, 0.5);

		while (true) {
			double power = powDouble(x, root - 1);
			double delta = (num / power - x) / root;

			if (Math.abs(delta) < PRECISION) {
				break;
			}

			x = x + delta;
		}

		return x;
	}

	private double powDouble(double num, int pow) {
		double power = 1;

		while (pow > 0) {
			if ((pow&1) == 1) {
				power = (power * num);
			}
			num = (num * num);
			pow /= 2;
		}

		return power;
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
