package codechef.february14.lmatrix2;

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

	private int N;
	private int M;

	private int[][] P;
	private int[][] A;

	private int totalMoves;

	private int x1[];
	private int x2[];
	private int y1[];
	private int y2[];

	private int k[];

	public void solve(InputReader in, PrintWriter out) throws IOException {

		N = in.nextInt();
		M = in.nextInt();

		P = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				P[i][j] = in.nextInt();
			}
		}

		A = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				A[i][j] = in.nextInt();
			}
		}

		solveBruteForce();

		out.println(totalMoves);

		for (int i = 0; i < totalMoves; i++) {
			out.print(x1[i]);
			out.print(" ");
			out.print(y1[i]);
			out.print(" ");
			out.print(x2[i]);
			out.print(" ");
			out.print(y2[i]);
			out.print(" ");
			out.println(k[i]);
		}

	}

	private void solveBruteForce() {
		int prod = N * M;
		x1 = new int[prod];
		x2 = new int[prod];
		y1 = new int[prod];
		y2 = new int[prod];
		k = new int[prod];

		totalMoves = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int add = P[i][j] - A[i][j];

				if (add == 0 || A[i][j] == 0)
					continue;

				k[totalMoves] = add;

				// begin each move here.
				x1[totalMoves] = x2[totalMoves] = i + 1;
				y1[totalMoves] = y2[totalMoves] = j + 1;


				totalMoves++;
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
