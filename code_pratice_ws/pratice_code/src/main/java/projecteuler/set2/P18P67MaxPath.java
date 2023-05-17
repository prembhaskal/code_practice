package projecteuler.set2;

import java.io.PrintWriter;
import java.util.Scanner;

public class P18P67MaxPath {

	int[][] grid;
	int[][] maxPaths;

	public void solve(Scanner in, PrintWriter out, int size) {
		readFile(in, size);

		// look up to store max path value for each node
		maxPaths = new int[size][size];
		maxPaths[0][0] = grid[0][0];

		// all elements at first can only be reached from first above them
		for (int i=1;i<size;i++) {
			maxPaths[i][0] = grid[i][0] + maxPaths[i-1][0];
		}


		// for each element, we find the parent element which gives the maximum length.
		for (int i=1;i<size;i++) {
			for (int j=1;j<size;j++) {
				maxPaths[i][j] = Math.max(
										  grid[i][j] + maxPaths[i-1][j-1],
										  grid[i][j] + maxPaths[i-1][j]);
			}
		}

		int maxPathLength = 0;

		for (int j=0;j<size;j++) {
			maxPathLength = Math.max(maxPaths[size-1][j], maxPathLength);
		}

		out.println("max path length is " + maxPathLength);

	}

	// representing the tree in form of array.
	private void readFile(Scanner in, int size) {
		grid = new int[size][size];

		for (int i=0;i<size;i++) {
			for (int j=0;j<=i;j++) {
				grid[i][j] = in.nextInt();
			}
		}
	}
}
