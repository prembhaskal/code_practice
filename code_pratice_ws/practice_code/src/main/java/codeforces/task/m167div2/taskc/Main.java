package codeforces.task.m167div2.taskc;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			Task solution = new Task();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class Task {

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			int n = in.nextInt();
			int[] A = new int[n];
			for (int j = 0; j < n; j++) {
				A[j] = in.nextInt();
			}
			int[] B = new int[n];
			for (int j = 0; j < n; j++) {
				B[j] = in.nextInt();
			}
			out.println(findMaxRating(n, A, B));
		}

	}

	// somewhat greedy
	// for unequal ratings, choose higher one
	// for equal ratings, choose which will help maximizing the smallest one.
	int findMaxRating(int n, int[] A, int[] B) {
		// find non equal first
		int totalA = 0, totalB = 0;
		for (int i = 0; i < n; i++) {
			if (A[i] != B[i]) {
				if( A[i] > B[i] ) {
					totalA += A[i];
				} else {
					totalB += B[i];
				}
			}
		}

		// take care of equals
		for (int i = 0; i < n; i++) {
			if (A[i] == B[i]) {
				if (A[i] == -1) {
					if (totalA > totalB) {
						totalA--;
					} else {
						totalB--;
					}
				} else if (A[i] == 1) {
					if (totalA > totalB) {
						totalB++;
					} else {
						totalA++;
					}
				}

			}
		}

		return Math.min(totalA, totalB);
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
