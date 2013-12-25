package codechef.archives.september13.caos1;

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

	int rows;
	int columns;
	char[][] grid;

	int[][] left;
	int[][] right;
	int[][] top;
	int[][] bottom;


	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();

		for (int i=0;i<tests;i++) {
			rows = in.nextInt();
			columns = in.nextInt();

			grid = new char[rows][columns];

			for (int j = 0; j < rows; j++) {
				String str = in.next();
				char[] chars = str.toCharArray();
				for (int k = 0; k < columns; k++) {
					grid[j][k] = chars[k];
				}
			}

			int totalMonsters = getMonstersCount();
			out.println(totalMonsters);
		}

	}

	private int getMonstersCount() {
		initializeLRTB();

		int totalMonsters = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int L = left[i][j];
				int R = right[i][j];
				int T = top[i][j];
				int B = bottom[i][j];

				int min = Math.min(Math.min(L, R), Math.min(T, B));

				if (min>=2) // least prime is 2
					totalMonsters++;
			}
		}

		return totalMonsters;
	}


	// '#' is a wall
	// '^' is what we are interested in.
	private void initializeLRTB() {
		// initialize these arrays.
		left = new int[rows][columns];
		right = new int[rows][columns];

		top = new int[rows][columns];
		bottom = new int[rows][columns];

		// initialize left
		for (int i = 0; i < rows; i++) {
			int counter = 0;
			for (int j = 0; j < columns; j++) {
				char ch = grid[i][j];
				left[i][j] = counter;
				if (ch=='^')
					counter++;
				else {
					counter = 0;
					left[i][j] = 0; // # gets a 0
				}
			}
		}

		// initialize right
		for (int i = 0; i < rows; i++) {
			int counter = 0;
			for (int j=columns-1;j>=0;j--) {
				char ch = grid[i][j];
				right[i][j] = counter;
				if (ch=='^')
					counter++;
				else {
					counter = 0;
					right[i][j] = 0;
				}
			}
		}

		// initialize top
		for (int j=0;j<columns;j++) {
			int counter = 0;
			for (int i = 0; i < rows; i++) {
				char ch = grid[i][j];
				top[i][j] = counter;
				if (ch=='^')
					counter++;
				else {
					counter = 0;
					top[i][j] = 0;
				}
			}
		}

		// initialize bottom
		for (int j=0;j<columns;j++) {
			int counter = 0;
			for (int i = rows-1; i >=0 ; i--) {
				char ch = grid[i][j];
				bottom[i][j] = counter;
				if (ch=='^')
					counter++;
				else {
					counter = 0;
					bottom[i][j] = 0;
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
