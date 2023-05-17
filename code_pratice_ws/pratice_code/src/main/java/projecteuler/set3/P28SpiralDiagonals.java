package projecteuler.set3;

import algorithm.UtilitiesClass;

public class P28SpiralDiagonals {

	int[][] spiral;


	// dumb way by creating the whole spiral but it was fun anyway
	public int getDiagonalSumOfSpiral(int size) {
		spiral = new int[size][size];

		int num = 1;

		int i = size/2; // start with right of middle.
		int j = size/2;

		spiral[i][j] = num;

		int currentSize = 2;
		while (spiral[0][size-1] == 0) { // last element to fill is on right.
			i--;
			j++;
			// first right side filling, top to down
			for (int k=0;k<currentSize;k++) {
				num++;
				i++;
				spiral[i][j] = num;
			}

			// bottom filling left to right
			for (int k=0;k<currentSize;k++) {
				num++;
				j--;
				spiral[i][j] = num;
			}

			// left side bottom to top
			for (int k=0;k<currentSize;k++) {
				num++;
				i--;
				spiral[i][j] = num;
			}

			// top side right to left
			for (int k=0;k<currentSize;k++) {
				num++;
				j++;
				spiral[i][j] = num;
			}

			// the size of the spiral increase by 2 each time.
			currentSize += 2;
		}
//
//		for (int a=0;a<size;a++) {
//			UtilitiesClass.printArray(spiral[a]);
//		}

		int diagonalSum = 0;

		for (i=0;i<size;i++)
			diagonalSum += spiral[i][i];

		for (i=0,j=size-1;j>=0;i++,j--)
			diagonalSum += spiral[i][j];

		diagonalSum -= 1;

		System.out.println("diagonal sum is " + diagonalSum);

		return diagonalSum;
	}


	// for a square, the right top corner == square of the side length.
	// add for all the squares from size 3 to so on.
	public int getDiagonalSumByAddingCorners(int size) {

		int diagonalSum = 1;

		for (int sideLength = 3; sideLength<=size; sideLength +=2) {
			int square = sideLength * sideLength;
			diagonalSum += square;
			for (int i=1;i<4;i++) {
				square -= (sideLength-1);
				diagonalSum += square;
			}
		}

		return diagonalSum;
	}
}
