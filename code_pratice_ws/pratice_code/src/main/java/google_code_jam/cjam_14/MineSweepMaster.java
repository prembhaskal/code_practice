package google_code_jam.cjam_14;

import algorithm.UtilitiesClass;
import common.util.InputReader;
import java.io.PrintWriter;

public class MineSweepMaster {

	private int[][] maze;

	private boolean[][] markMaze;

	private boolean possible;

	private int R;
	private int C;
	private int M;

	public void solve(InputReader in, PrintWriter out) {
		int tests = in.nextInt();

		for (int testNo = 1; testNo <= tests; testNo++) {
			R = in.nextInt();
			C = in.nextInt();
			M = in.nextInt();

			tryFinding();

			out.println("Case #" + testNo + ": ");

			if (!possible) {
				out.println("Impossible");
			}
//			else
				printMine(out);

			System.out.println("done with test " + testNo);
		}
	}

	private void tryFinding() {
		if (!trial1()) {
			trial2();
		}
	}

	private boolean trial1() {
		maze = new int[R][C];
		markMaze = new boolean[R][C];
		possible = true;
		fillOptimally1();

		for (int i = 0; i < R; i++) {
			UtilitiesClass.printArray(maze[i]);
		}

		// try uncovering all.
		int row = R-1;
		int col = C-1;
		if (maze[row][col] != -1) {
			int mines = findMines(row, col);
			if (mines == 0) {
				uncover(row, col);
			}
			markMaze[row][col] = true;
		}

		if (getMarked() + M < R * C) {
			possible = false;
		}

		return possible;
	}

	private boolean trial2() {
		maze = new int[R][C];
		markMaze = new boolean[R][C];
		possible = true;
		fillOptimally2();

		for (int i = 0; i < R; i++) {
			UtilitiesClass.printArray(maze[i]);
		}

		// try uncovering all.
		int row = R-1;
		int col = C-1;
		if (maze[row][col] != -1) {
			int mines = findMines(row, col);
			if (mines == 0) {
				uncover(row, col);
			}
			markMaze[row][col] = true;
		}

		if (getMarked() + M < R * C) {
			possible = false;
		}

		return possible;
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
			uncover(newRow, newCol);
		}

		// check left
		newCol = col - 1;
		newRow = row;
		if (inBounds(newRow, newCol)) {
			uncover(newRow, newCol);
		}

		// check above left
		newCol = col - 1;
		newRow = row - 1;
		if (inBounds(newRow, newCol)) {
			uncover(newRow, newCol);
		}
	}

	private int getMarked() {
		int marked = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (markMaze[i][j])
					marked++;
			}
		}

		return marked;
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
	private void fillOptimally1() {

		if (M == 0)
			return;

		boolean col_wise = true;

		int filled = 0;

		if (R < C) {
			col_wise = true;
		}
		else {
			col_wise = false;
		}

		STOP:
		if (col_wise) {
			for (int col = 0; col < C - 2 && col >= 0; col++) {
				for (int row = 0; row < R && row >= 0; row++) {
					if (maze[row][col] != -1) {
						maze[row][col] = -1;
						filled++;
						if (filled == M)
							break STOP;
					}
				}
			}
			for (int row = 0; row < R && row >= 0; row++) {
				for (int col= C - 2; col < C && col >= 0; col++) {
					if (maze[row][col] != -1) {
						maze[row][col] = -1;
						filled++;
						if (filled == M)
							break STOP;
					}
				}
			}
		}
		else {
			for (int row = 0; row < R - 2 && row >= 0; row++) {
				for (int col= 0; col < C && col >= 0; col++) {
					if (maze[row][col] != -1) {
						maze[row][col] = -1;
						filled++;
						if (filled == M)
							break STOP;
					}
				}
			}
			for (int col = 0; col < C && col >= 0; col++) {
				for (int row = R - 2; row < R && row >= 0; row++) {
					if (maze[row][col] != -1) {
						maze[row][col] = -1;
						filled++;
						if (filled == M)
							break STOP;
					}
				}
			}
		}
	}


	// fill a row, then col, then row, then row....
	private void fillOptimally2() {
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
