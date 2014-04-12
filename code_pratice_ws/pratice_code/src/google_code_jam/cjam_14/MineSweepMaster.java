package google_code_jam.cjam_14;

import algorithm.UtilitiesClass;
import common.util.InputReader;
import java.io.PrintWriter;
import projecteuler.set3.p27QuadraticPrimes;

public class MineSweepMaster {

	private int[][] maze;

	private boolean[][] markMaze;

	private boolean possible;

	private int R;
	private int C;
	private int M;
	private int uncovered;

	public void solve(InputReader in, PrintWriter out) {
		int tests = in.nextInt();

		for (int testNo = 1; testNo <= tests; testNo++) {
			R = in.nextInt();
			C = in.nextInt();
			M = in.nextInt();

			findMaze();

			out.println("Case #" + testNo + ": ");

			if (!possible) {
				out.println("Impossible");
			}
//			else
				printMine(out);
		}
	}

	private void findMaze() {
		maze = new int[R][C];
		markMaze = new boolean[R][C];
		uncovered = 0;
		possible = true;
		fillOptimally();

		for (int i = 0; i < R; i++) {
			UtilitiesClass.printArray(maze[i]);
		}

		// try uncovering all.
		int row = R-1;
		int col = C-1;
		if (maze[row][col] != -1) {
			uncovered++;
			int mines = findMines(row, col);
			if (mines == 0) {
				uncover(row, col);
			}
		}

		if (uncovered + M < R * C) {
			possible = false;
		}

	}

	private void uncover(int row, int col) {

		if (maze[row][col] == -1) { // if its a bomb, return.
			return;
		}

		if (markMaze[row][col]) // return if already uncovered.
			return;

		markMaze[row][col] = true;
		int mines = findMines(row, col);
		if (mines != 0) // recursion only when count is 0.
			return;


		int newRow;
		int newCol;

		// check above
		newCol = col;
		newRow = row - 1;
		if (inBounds(newRow, newCol)) {
			uncovered++;
			uncover(newRow, newCol);
		}

		// check left
		newCol = col - 1;
		newRow = row;
		if (inBounds(newRow, newCol)) {
			uncovered++;
			uncover(newRow, newCol);
		}

		// check above left
		newCol = col - 1;
		newRow = row - 1;
		if (inBounds(newRow, newCol)) {
			uncovered++;
			uncover(newRow, newCol);
		}
	}
	// this is assuming a particular way of filling.
	private int findMines(int row, int col){
		int mines = 0;
		int newRow;
		int newCol;

		// check above
		newCol = col;
		newRow = row - 1;
		if (inBounds(newRow, newCol) && maze[newRow][newCol] == -1)
			mines++;

		// check left
		newCol = col - 1;
		newRow = row;
		if (inBounds(newRow, newCol) && maze[newRow][newCol] == -1)
			mines++;

		// check above and left
		newCol = col - 1;
		newRow = row - 1;
		if (inBounds(newRow, newCol) && maze[newRow][newCol] == -1)
			mines++;

		return mines;
	}

	private boolean inBounds(int row, int col) {
		if (row < 0)
			return false;
		if (row >= R)
			return false;
		if (col < 0)
			return false;
		if (col >= C)
			return false;

		return true;
	}

	// fill a row, then col, then row, then row....
	private void fillOptimally() {
		boolean col_wise = true;

		int row = 0;
		int col = 0;

		int fillingRow = 0;
		int fillingCol = 0;

		int filled = 0;

		if (R > C) {
			col_wise = true;
		}
		else {
			col_wise = false;
		}

		while (filled < M) {
			if (col_wise) {
				if (maze[fillingRow][col] != -1) {
					maze[fillingRow][col] = -1;
					filled++;
					col_wise = !col_wise;
				}

				fillingRow++;

				if (fillingRow == R) {
					fillingRow = 0;
					col++; // next column
				}
			}
			// filling a row, so incrementing the fillingColumn.
			else {
				if (maze[row][fillingCol] != -1) {
					maze[row][fillingCol] = -1;
					filled++;
					col_wise = !col_wise;
				}

				fillingCol++;

				if (fillingCol == C) {
					fillingCol = 0;
					row++; // next row.
				}
			}
		}

	}

	private void printMine(PrintWriter out) {
		char[][] printMaze = new char[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (maze[i][j] == -1)
					printMaze[i][j] = '*';
				else
					printMaze[i][j] ='.';
			}
		}

		printMaze[R-1][C-1] = 'c';

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				out.print(printMaze[i][j]);
			}
			out.println();
		}
	}
}
