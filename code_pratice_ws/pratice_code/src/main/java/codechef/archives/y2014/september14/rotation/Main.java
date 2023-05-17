package codechef.september14.rotation;

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

	int[] nums;
	int M;

	public void solve(InputReader in, PrintWriter out) throws IOException {
		int N = in.nextInt();
		M = in.nextInt();

		nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = in.nextInt();
		}

		int shifts = 0;

		for (int i = 0; i < M; i++) {
			String query = in.next();

			if (query.equals("A")) { // left shifts
				int rots = in.nextInt();
				shifts = (shifts + rots);
				if (shifts > N)
					shifts -= N;
			}
			else if (query.equals("C")) { // right shifts
				int rots = in.nextInt();
				shifts = shifts - rots;
				if (shifts < 0)
					shifts += N;
			}
			else {
				int index = in.nextInt();
				index--; // input is 1 based
				index = index - shifts; // account for shifting.
				if (index < 0)
					index += N;

				out.println(nums[index]);
			}
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
