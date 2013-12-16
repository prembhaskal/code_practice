package codechef.december13.rect_quer;

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

	int size;
	int query;
	int[][] matrix;
	int[][] queryX;
	int[][] queryY;

	int[][][] counters;

	// HINT... think if you have to count the number of 1s in the sub-matrix.
	public void solve(InputReader in, PrintWriter out) throws IOException {
		size = in.nextInt();
		matrix = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = in.nextInt();
			}
		}

		init();

		query = in.nextInt();
		queryX = new int[query][2];
		queryY = new int[query][2];

		for (int i = 0; i < query; i++) {
			// upper-left
			queryX[i][0] = in.nextInt();
			queryY[i][0] = in.nextInt();
			// lower-right
			queryX[i][1] = in.nextInt();
			queryY[i][1] = in.nextInt();
		}

		// fire the queries
		for (int i = 0; i < query; i++) {
			int X1 = queryX[i][0] - 1;
			int Y1 = queryY[i][0] - 1;

			int X2 = queryX[i][1] - 1;
			int Y2 = queryY[i][1] - 1;

			int distinct = getDistinct(X1, Y1, X2, Y2);
			out.println(distinct);
		}


	}

	private int getDistinct(int X1, int Y1, int X2, int Y2) {
		// upper right corner
		int X3 = X1;
		int Y3 = Y2;

		// lower left corner
		int X4 = X2;
		int Y4 = Y1;

		//get the area1
		int[] cntA1 = new int[11];
		if (X1 > 0 && Y1 > 0) {
			cntA1 = counters[X1-1][Y1-1];
		}

		// get the area2
		int[] cntA2 = new int[11];
		if (X3 > 0) {
			cntA2 = counters[X3-1][Y3];
		}

		// get the area3
		int[] cntA3 = new int[11];
		if (Y4 > 0) {
			cntA3 = counters[X4][Y4-1];
		}

		// get the total area
		int[] cntArea = counters[X2][Y2];

		int totalDistinct = 0;
		// make the final counter
		int[] finalCnt = new int[11];
		for (int i = 1; i < 11; i++) {
			finalCnt[i] = cntArea[i] - cntA3[i] - cntA2[i] + cntA1[i];
			if (finalCnt[i] > 0)
				totalDistinct++;
		}

		return totalDistinct;
	}

	private void init() {
		// nos are from 1 to 10
		counters = new int[size][size][11];
		// counter to keep track
		int[] cntr = new int[11];

		// init the first row
		for (int i = 0; i < size; i++) {
			int num = matrix[0][i];

			cntr[num]++;

			for (int j = 1; j < 11; j++) {
				counters[0][i][j] = cntr[j];
			}

		}

		// init the rest of the matrix
		for (int i = 1; i < size; i++) {
			cntr = new int[11];
			for (int j = 0; j < size; j++) {
				int num = matrix[i][j];
				cntr[num]++;

				for ( int k = 1; k < 11; k++) {
					// cell from the above column plus previous row.
					counters[i][j][k] = counters[i-1][j][k] + cntr[k];
				}
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
