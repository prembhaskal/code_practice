package google_code_jam.cjam_13;

import java.io.PrintWriter;
import java.util.Scanner;

public class LawnMower {

	int[][] mower;

	int rows;
	int columns;

	public void solve(Scanner in, PrintWriter out) {
		int tests = in.nextInt();

		for (int testNo=1;testNo<=tests;testNo++) {
			rows = in.nextInt();
			columns = in.nextInt();

			readMower(in);

			String result = isPossible();

			out.println("Case #" + testNo + ": " + result);
		}
	}

	private String isPossible() {
		int[] maxInRow = new int[rows];
		int[] maxInColumn = new int[columns];

		// find max in rows
		for (int i=0;i<rows;i++) {
			int max = -1;
			for (int j=0;j<columns;j++) {
				max = Math.max(max, mower[i][j]);
			}
			maxInRow[i] = max;
		}

		// find max in columns
		for (int j=0;j<columns;j++) {
			int max = -1;
			for (int i=0;i<rows;i++) {
				max = Math.max(max, mower[i][j]);
			}

			maxInColumn[j] = max;
		}

		// check each cell is possible
		boolean isPossible = true;

		for (int i=0;i<rows;i++) {
			for (int j=0;j<columns;j++) {
				// check if lawning not possible from column and row
				if (mower[i][j] < maxInColumn[j] && mower[i][j] < maxInRow[i]) {
					isPossible = false;
					break;
				}
			}
		}

		if (isPossible) {
			return "YES";
		} else {
			return "NO";
		}
	}

	private void readMower(Scanner in) {
		mower = new int[rows][columns];

		for (int i=0;i<rows;i++) {
			for (int j=0;j<columns;j++) {
				mower[i][j] = in.nextInt();
			}
		}
	}


}
