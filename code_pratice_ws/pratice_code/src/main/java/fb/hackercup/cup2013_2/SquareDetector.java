package fb.hackercup.cup2013_2;

import common.util.InputReader;
import java.io.PrintWriter;

public class SquareDetector {

	int size;
	char[][] square;

	public void solve(InputReader in, PrintWriter out) {
		int tests = in.nextInt();

		for (int i = 1; i <= tests; i++) {
			size = in.nextInt();

			square = new char[size][size];

			for (int j = 0; j < size; j++) {
				String str = in.next();
				square[j] = str.toCharArray();
			}

			if (isSquarePresent()) {
				out.println("Case #" + i + ": YES");
			} else {
				out.println("Case #" + i + ": NO");
			}
		}
	}

	private boolean isSquarePresent() {
		boolean endOfSquare = false;

		int firstRowOfSquare = -1;
		int firstColOfSquare = -1;
		int totalRowsWithSquare = 0;
		int sizeOfSquare = 0;

		// check for any this occurrences #.# in whole square
		for (int i = 0; i < size; i++) {
			char prevCell = ' ';
			boolean thisSquareStarted = false;
			for (int j = 0; j < size; j++) {
				char cell = square[i][j];
				if (cell == '#') {
					if (thisSquareStarted && prevCell == '.')
						return false;

					thisSquareStarted = true;
				}

				prevCell = cell;
			}
		}

		// find the size of the square and first column of square.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				char cell = square[i][j];
				if (cell == '#') {
					sizeOfSquare++;
				}
				if (sizeOfSquare == 1)
					firstColOfSquare = j;
			}
			if (sizeOfSquare > 0) {
				firstRowOfSquare = i;
				break;
			}
		}

		if (sizeOfSquare < 0)
			return false;

		// we already have one.
		totalRowsWithSquare = 1;

		// now check for the entire grid,
		// match firstColOfSquare;
		// match sizeOfSquare;
		// keep counting totalRowsWithSquare
		for (int i = firstRowOfSquare + 1; i < size; i++) {
			if (!endOfSquare) {
				int firstOfBlack = getFirstIndexOfBlack(square[i]);

				if (firstOfBlack == -1) {
					endOfSquare = true;
					continue;
				}

				if (firstOfBlack != firstColOfSquare)
					return false;

				int totalBlacks = getTotalConsecutiveBlacks(square[i]);
				if (totalBlacks != sizeOfSquare)
					return false;

				totalRowsWithSquare++;
			}
			else { // check for any # present below this part, if yes, return false
				int firstOfBlack = getFirstIndexOfBlack(square[i]);
				if (firstOfBlack != -1)
					return false;
			}
		}

		if (totalRowsWithSquare != sizeOfSquare)
			return false;


		return true; // we passed all tests, return true.
	}

	private int getFirstIndexOfBlack(char[] chars) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '#')
				return i;
		}

		return -1;
	}

	private int getTotalConsecutiveBlacks(char[] chars) {
		int total = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '#')
				total++;
		}

		return total;
	}
}
