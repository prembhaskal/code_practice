package projecteuler.set1;

import java.io.PrintWriter;
import java.util.Scanner;

public class P11LargesProdInGrid {

	int[][] grid;

	public void solve(Scanner in, PrintWriter out) {
		readFile(in);

		int max = 0;
		max = Math.max(max, getLargestDiagonally());
		max = Math.max(max, getLargestHorizontal());
		max = Math.max(max, getLargestVertical());

		out.println(max);
	}

	private int getLargestDiagonally() {
		int max = 0;

		// from top-left to bottom-right
		for (int i=0;i<17;i++) {
			for (int j=0;j<17;j++) {
				int prod = grid[i][j] * grid[i+1][j+1] * grid[i+2][j+2] * grid[i+3][j+3];
				max = Math.max(prod, max);
			}
		}

		// from bottom-right to top-left
		for (int i=19;i>=3;i--) {
			for (int j=19;j>=3;j--) {
				int prod = grid[i][j] * grid[i-1][j-1] * grid[i-2][j-2] * grid[i-3][j-3];
				max = Math.max(prod, max);
			}
		}

		// from top-right to bottom -left
		for (int i=0;i<17;i++) {
			for (int j=19;j>=3;j--) {
				int prod = grid[i][j] * grid[i+1][j-1] * grid[i+2][j-2] * grid[i+3][j-3];
				max = Math.max(max, prod);
			}
		}

		return max;
	}

	private int getLargestHorizontal() {
		int max = 0;
		for (int i=0;i<20;i++) {
			for (int j=0;j<17;j++) {
				int prod = grid[i][j] * grid[i][j+1] * grid[i][j+2] * grid[i][j+3];
				max = Math.max(prod, max);
			}
		}

		return max;
	}

	private int getLargestVertical() {
		int max = 0;
		for (int j=0;j<20;j++) {
			for (int i=0;i<17;i++) {
				int prod = grid[i][j] * grid[i+1][j] * grid[i+2][j] * grid[i+3][j];
				max = Math.max(prod, max);
			}
		}

		return max;
	}

	private void readFile(Scanner in) {
		grid = new int[20][20];

		for (int i=0;i<20;i++) {
			for (int j=0;j<20;j++) {
				grid[i][j] = in.nextInt();
			}
		}
	}
}
