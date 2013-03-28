package projecteuler.set1;

import java.io.PrintWriter;
import java.util.Scanner;

public class P11LargesProdInGrid {

	int[][] grid;

	public void solve(Scanner in, PrintWriter out) {
		readFile(in);

		int max = 0;
		for (int i=0;i<17;i++) {
			for (int j=0;j<17;j++) {
				int prod = grid[i][j] * grid[i+1][j+1] * grid[i+2][j+2] * grid[i+3][j+3];
				max = Math.max(prod, max);
			}
		}

		out.println(max);
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
