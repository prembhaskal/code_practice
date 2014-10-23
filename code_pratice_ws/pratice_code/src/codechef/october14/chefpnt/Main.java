package codechef.october14.chefpnt;

import java.io.*;
import java.util.Arrays;
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

	private int[][] matrix;

	private int N;
	private int M;

	private int K;

	private int[][] stepsColored; // x,y
	private int[] ways; // 0 or 1
	private int totalSteps;
	// 0 - left-right
	// 1 - up-down

	public void solve(InputReader in, PrintWriter out) throws IOException {

		N = in.nextInt();
		M = in.nextInt();

		matrix = new int[N][M];

		// 1 denotes BLACK cells
		// 0 denotes WHITE cells
		// 2 denotes RED cell
		K = in.nextInt();

		for (int i = 0; i < K; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			x--;y--; // index 1 based input.
			matrix[x][y] = 1;
		}

		doItNaiveWay();

		out.println(totalSteps);

		for (int i = 0; i < totalSteps; i++) {
			out.print(stepsColored[i][0]);
			out.print(" ");
			out.print(stepsColored[i][1]);
			out.print(" ");
			out.print(ways[i]);
			out.println();
		}
	}

	private void doItNaiveWay() {
		// scan row-wise and mark each white cell.

		int[][] rowwiseSteps = new int[N * M][2];
		int[] operations = new int[M * N];
		int steps = 0;

		int[][] originalMatrix = new int[N][M];
		for (int i = 0; i < N; i++) {
			System.arraycopy(matrix[i], 0, originalMatrix[i], 0, M);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// choose white cell and start searching on its right-side.
				if (matrix[i][j] == 0) {
					int start = j;
					rowwiseSteps[steps][0] = i + 1;
					rowwiseSteps[steps][1] = j + 1;
					operations[steps] = 0;
					steps++;

					for (int k = start; k < M; k++) {
						if (matrix[i][k] != 0) {
							break;
						}
						matrix[i][k] = 2; // PAINT RED.
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			System.arraycopy(originalMatrix[i], 0, matrix[i], 0, M);
		}


		int[][] colwisesteps = new int[N * M][2];
		int[] colOperations = new int[N * M];
		int colSteps = 0;

		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				// choose white cell and start search down
				if (matrix[i][j] == 0) {
					int start =  i;
					colwisesteps[colSteps][0] = i + 1;
					colwisesteps[colSteps][1] = j + 1;
					colOperations[colSteps] = 1;
					colSteps++;

					for (int k = start; k < N; k++) {
						if (matrix[k][j] != 0) {
							break;
						}

						matrix[k][j] = 2; // RED
					}
				}
			}
		}

		if (steps < colSteps) {
			totalSteps = steps;

			stepsColored = new int[totalSteps][2];
			ways = new int[totalSteps];

			System.arraycopy(rowwiseSteps, 0, stepsColored, 0, totalSteps);
			System.arraycopy(operations, 0, ways, 0, totalSteps);
		}
		else {
			totalSteps = colSteps;
			stepsColored = new int[totalSteps][2];
			ways = new int[totalSteps];

			System.arraycopy(colwisesteps, 0, stepsColored, 0, totalSteps);
			System.arraycopy(colOperations, 0, ways, 0, totalSteps);
		}
	}

	// try to sort the points based on (point+ connected_zeroes_together).
	private void anotherWay() {

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
