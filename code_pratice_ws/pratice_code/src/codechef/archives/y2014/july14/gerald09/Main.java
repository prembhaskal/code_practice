package codechef.july14.gerald09;

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
	private int K;

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			N = in.nextInt();
			M = in.nextInt();
			K = in.nextInt();

			char[][] matrix;

			matrix = getNaiveFilledMatrix();
			printMatrix(out, matrix);
		}

	}

	private void printMatrix(PrintWriter out, char[][] matrix) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				out.print(matrix[i][j]);
			}
			out.println();
		}

	}

	private char[][] getNaiveFilledMatrix() {
		char[][] matrix = new char[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				matrix[i][j] = 'A';
			}
		}

		return matrix;
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
