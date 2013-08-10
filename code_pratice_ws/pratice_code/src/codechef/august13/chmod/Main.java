package codechef.august13.chmod;

import java.io.*;
import java.util.Random;
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

	int elements;
	int[] nums;
	int[][] elementMap;
	long[][] preCal;
	int[] maxPowers;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		elements = in.nextInt();
		nums = new int[elements];

		for (int i = 0; i < elements; i++) {
			nums[i] = in.nextInt();
		}

		initialize();

		int queries = in.nextInt();

		for (int i = 0; i < queries; i++) {
			int left = in.nextInt();
			left--;
			int right = in.nextInt();
			right--;
			int mod = in.nextInt();

//			int segmentProd = getSegmentProd(left, right, mod);
			int segmentProd = getSegmentProdPreCal(left, right, mod);
			out.println(segmentProd);
		}

	}

	private int getSegmentProd(int left, int right, int mod) {
		long segmentProd = 1;

		for (int i = 2; i < 101; i++) {
			int num = i;
			int power = elementMap[right][num] - elementMap[left][num];
			int numRaisePower = power(num, power, mod);
			segmentProd = (segmentProd*numRaisePower)%mod;
			if (segmentProd==0)
				break;
		}

		segmentProd = (segmentProd * nums[left])%mod;

		return (int) segmentProd;
	}

	private int getSegmentProdPreCal(int left, int right, int mod) {
		long segmentProd = 1;

		for (int i = 2; i < 101; i++) {
			// first try to find using preCal value
			int power = elementMap[right][i] - elementMap[left][i];

			if (power == 0)
				continue;

			int maxPower = maxPowers[i];

			int prePower = power/maxPower;
			int remPower = power%maxPower;

			if (prePower > 0) {
				int power9 = (int) (preCal[i][maxPower] % mod);
				int numRaisePower = power(power9, prePower, mod);
				segmentProd = (segmentProd * numRaisePower) % mod;
			}

			if (remPower > 0) {
				int remProd = (int) (preCal[i][remPower] % mod);
				segmentProd = (segmentProd * remProd) % mod;
			}

			if (segmentProd == 0)
				break;
		}

		segmentProd = (segmentProd * nums[left]) % mod;

		return (int) segmentProd;
	}

	private int power(long num, int pow) {
		long prod = 1;
		while (pow>0) {
			if ((pow&1)==1) {
				prod = (prod * num);
			}
			num = (num * num);
			pow /=2;
		}
		return (int)prod;
	}

	private int power(long num, int pow, int mod) {
		long prod = 1;

		while (pow > 0) {
			if ((pow&1)==1) {
				prod = (prod * num)%mod;
			}

			num = (num * num) % mod;
			pow /= 2;
		}

		return (int)prod;
	}

	// for each number (1 to 100), store the power of each of it, and use it for multiplication.
	private void initialize() {
		elementMap = new int[elements][101]; // values range are from 1 to 100.
		int[] counter = new int[101];

		for (int i = 0; i < elements; i++) {
			int idx = nums[i];
			counter[idx]++;
			for (int j = 2; j < 101; j++) {
				elementMap[i][j] = counter[j];
			}
		}

		preCalculateValues();
	}

	// pre calculate values within long range taking 10^18 here
	// max power is 9 for any number (max kept based on 100)... we can change this for smaller number
	private void preCalculateValues() {
		maxPowers = new int[101];

		// put max power for each number
		for (int i = 2; i < 101; i++) {
			maxPowers[i] = (int)(18/Math.log10(i));
		}

		preCal = new long[101][64];

		for (int i=2;i<101;i++) {
			long prod = 1;
			int maxPower = maxPowers[i];
			for (int j = 1; j <= maxPower; j++) {
				prod = prod * i;
				preCal[i][j] = prod;
			}
		}
	}

	public void testTiming() {
		elements = 100000;
		nums = new int[elements];
		Random random = new Random();
		for (int i = 0; i < nums.length; i++) {
			int num = random.nextInt(100) + 1;
			nums[i] = num;
		}

		initialize();

		int queries = elements;

		for (int i = 0; i < queries; i++) {
//			getSegmentProd(2, 99999, 1000000007);
			getSegmentProdPreCal(2, 99999, 1000000007);
//			segmentProdBruteForce(2, 99999, 1000000007);
		}

		System.out.println("done");
	}

	public void testCorrectness() {
//		nums = new int[]{6,13,6,6,13,13,6,1,1};
//		elements = nums.length;

		int mod = 100007;// 487587;

		elements = 1000;
		nums = new int[elements];
		Random random = new Random();
		for (int i = 0; i < nums.length; i++) {
			int num = 97;
			nums[i] = num;
		}

		initialize();

		for (int i = 0; i < elements; i++) {
			for (int j = i; j < elements; j++) {
//				int prod1 = getSegmentProd(i, j, mod);
				int prod1 = getSegmentProdPreCal(i, j, mod);
				int prod2 = segmentProdBruteForce(i, j, mod);

				if (prod1 != prod2) {
					System.out.println("PROBLEM");
				}
			}
		}
	}

	private int segmentProdBruteForce(int left, int right, int mod) {
		long prod = 1;

		for (int i = left; i <= right ; i++) {
			prod = (prod * nums[i])%mod;
		}

		return (int) prod;
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
