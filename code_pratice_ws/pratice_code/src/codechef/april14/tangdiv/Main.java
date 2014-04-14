package codechef.april14.tangdiv;

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

	private int n;
	private int k;
	private int p;
	private int[][] p_parts;
	private int[][] k_parts;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			n = in.nextInt();
			k = in.nextInt();
			p = in.nextInt();

			k_parts = new int[k][2];
			p_parts = new int[p][2];

			for (int j = 0; j < k; j++) {
				k_parts[j][0] = in.nextInt();
				k_parts[j][1] = in.nextInt();
			}

			for (int j = 0; j < p; j++) {
				p_parts[j][0] = in.nextInt();
				p_parts[j][1] = in.nextInt();
			}

			if (isPossibleToDivide()) {
				out.println("Yes");
			}
			else {
				out.println("No");
			}
		}

	}

	// THIS GOT ACCEPTED
	private boolean isPossibleToDivide() {
		// check for each of the required part is possible to cut from one of the existing parts.
		for (int i = 0; i < k; i++) {
			int l = k_parts[i][0];
			int r = k_parts[i][1];

			boolean possible = false;

			if ( l <= r) {
				for (int j = 0; j < p; j++) {
					int a = p_parts[j][0];
					int b = p_parts[j][1];

					if (a <= b) { // NO overlap
						// just check if the required part overlaps completely.
						if (a <= l && b >= r) {
							possible = true;
							break;
						}
					}
					else {
						if ((l <= b && r <= b) || (l >= a && r >= a)) { // right part is straight or left part is straight.
							possible = true;
							break;
						}
					}

				}
			}
			else { // this part needs boundary(1:n) to be contained in it.
				for (int j = 0; j < p; j++) {
					int a = p_parts[j][0];
					int b = p_parts[j][1];

					if (a <= b) {
						continue; // since a<=b it does not contains the boundary.
					}
					else {
						if (l >= a && r <= b) {
							possible = true;
							break;
						}
					}
				}
			}


			if (!possible)
				return false; // STOP if we are not able to get this part.
		}


		return true;
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
