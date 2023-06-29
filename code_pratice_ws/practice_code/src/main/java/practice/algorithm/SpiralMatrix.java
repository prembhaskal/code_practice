package practice.algorithm;

import java.util.Arrays;

public class SpiralMatrix {

	public void printMatrix(int size) {
		int total = size * size;

		int val = 1;
		int[][] mat = new int[size][size];
		int x = 0;
		int y = 0;

		for (int i = 0; i < size; i++) {
			Arrays.fill(mat[i], -1);
		}

		out:
		while (val <= total) {
			// down
			for (; !isBoundaryCrossed(size, x, y) && mat[x][y] == -1; x++) {
				mat[x][y] = val++;
				if (val > total) break out;
			}
			x--;y++;
			// right
			for (; !isBoundaryCrossed(size, x, y) && mat[x][y] == -1; y++) {
				mat[x][y] = val++;
				if (val > total) break out;
			}
			y--;x--;
			// up
			for (; !isBoundaryCrossed(size, x, y) && mat[x][y] == -1; x--) {
				mat[x][y] = val++;
				if (val > total) break out;
			}
			x++;y--;
			// left
			for (; !isBoundaryCrossed(size, x, y) && mat[x][y] == -1; y--) {
				mat[x][y] = val++;
				if (val > total) break out;
			}
			y++;x++;
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(mat[i][j] + "  ");
			}
			System.out.println("");
		}
	}

	private boolean isBoundaryCrossed(int size, int x, int y) {
		if (x < 0 || x > size - 1) return true;
		if (y < 0 || y > size - 1) return true;
		return false;
	}

	public static void main(String[] args) {
		new SpiralMatrix().printMatrix(5);
	}
}
