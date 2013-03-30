package projecteuler.set2;

public class P15LatticePaths {

	// ways(x,y) = ways(x-1,y) + ways(x,y-1)
	public long findLatticePathsForGrid(int size) {
		int paths = 0;

		if (size==1)
			return 2;

		// extra grid line to store boundaries i.e 0
		long[][] grid = new long[size+2][size+2];
		grid[1][2] = 1;
		grid[2][1] = 1;
		grid[2][2] = 2;

		for (int sz=3;sz<=size+1;sz++) {
			for (int i=1;i<sz;i++) {
				grid[i][sz] = grid[i][sz-1] + grid[i-1][sz];
			}

			for (int j=1;j<sz;j++) {
				grid[sz][j] = grid[sz][j-1] + grid[sz-1][j];
			}

			grid[sz][sz] = grid[sz][sz-1] + grid[sz-1][sz];
		}

		return grid[size+1][size+1];
	}

	/*
	In an n by n grid, after completing any particular path, you're making a grand total of n moves to the
	right (R) and n moves down (D) (just in a jumbled order). Consider, though, that once you've chosen the
	order/positions of the n R's (for example), you automatically know that the D's must fill the remaining spaces.
	 This is why we choose n positions to be labeled as R's out of the total space of 2n possible positions
	  we have to choose from.
	 */

}
