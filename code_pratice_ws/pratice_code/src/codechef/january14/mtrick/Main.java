package codechef.january14.mtrick;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;
import org.omg.DynamicAny._DynEnumStub;

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
	private BigInteger A;
	private BigInteger B;
	private BigInteger C;

	private String S;

	private BigInteger[] list;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			list = new BigInteger[N];

			for (int j = 0; j < N; j++) {
				list[j] = BigInteger.valueOf(in.nextLong());
			}

			A = BigInteger.valueOf(in.nextLong());
			B = BigInteger.valueOf(in.nextLong());
			C = BigInteger.valueOf(in.nextLong());

			S = in.next();

			computeUsingSwap(out);
		}

	}

	// this one got accepted.
	private void computeUsingSwap(PrintWriter out) {
		char[] chars = S.toCharArray();

		// store the indexes.
		int[] indexes = new int[N];
		for (int i = 0; i < N; i++) {
			indexes[i] = i;
		}

		// start idx and end idx kept so that we don't have to reverse the full list.
		int startIdx = 0;
		int endIdx = N - 1;

		boolean listReversed = false;

		BigInteger ADD = BigInteger.ZERO;
		BigInteger MUL = BigInteger.ONE;

		for (int i = 0; i < N; i++) {
			char ch = chars[i];

			if (ch == 'R') {
				listReversed = !listReversed;

				// swap startIdx and endIdx.
				int temp = startIdx;
				startIdx = endIdx;
				endIdx = temp;
			}
			else if (ch == 'A') {
				ADD = ADD.add(A);
				ADD = ADD.mod(C);
			}
			else {
				MUL = MUL.multiply(B);
				MUL = MUL.mod(C);
				ADD = ADD.multiply(B);
				ADD = ADD.mod(C);
			}

			// get the index of number which will be shown now
			int numberIdx = startIdx;

			BigInteger result = list[numberIdx];
			result = result.multiply(MUL);
			result = result.add(ADD);
			result = result.mod(C);

			out.print(result);

			// move the current pointer based on the list was reversed or not.
			if (i < N-1) {
				if (listReversed) {
					startIdx--;
				} else {
					startIdx++;
				}

				out.print(" ");
			}
		}

		out.println("");

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
