package topcoder.srm.s566iv2;

import java.util.Arrays;

// SRM 566 DIV2 2nd question
public class PenguinPals {

	public int findMaximumMatching(String colors) {
		int maxMatch = 0;

//		maxMatch = countWays(colors,0,0,colors.length()-1);
//		maxMatch = countWays2(colors.toCharArray(),0,colors.length()-1);


		// dp to store matches for string within a fromIndex and toIndex.
		// 50 becoz string length can be between 1 to 50
		int [][] dp = new int[50][50];

		for (int i=0;i<50;i++)
			Arrays.fill(dp[i],-1);

		maxMatch = countWays2DP(colors.toCharArray(),0,colors.length()-1,dp);
//		maxMatch = countWays2(colors.toCharArray(), 0, colors.length());
		return maxMatch;
	}

	// B -- R -- B -- R -- B  ----SHOULD RETURN 2

	// R -- R -- B -- R -- B -- R -- B -- B   ---- SHOULD RETURN 3


	// Fails system test....too slow
	private int countWays2(char[] colors, int fromIndex, int toIndex) {
		int count = 0;
		if (fromIndex >= toIndex)
			return count;
		int maxCount = count;

		for (int i=fromIndex; i<toIndex;i++) {
			for (int j=i+1;j<toIndex;j++) {
				if (colors[i]==colors[j]) {
					// first count ways is for search in string within the i+1 && j-1, (see we already searched between i and j
					// second is for searching the matches j+1 and ahead (see we already searched till j+1)
					maxCount = Math.max(maxCount, 1 + countWays2(colors,i+1,j-1) + countWays2(colors,j+1,toIndex));
				}
			}
		}

		return maxCount;
	}


	// PASSED SYSTEM TEST....dp id for value for fromIndex and toIndexS
	private int countWays2DP(char[] colors, int fromIndex, int toIndex, int [][] dp) {
		int count = 0;
		if (fromIndex >= toIndex)
			return count;
		int maxCount = count;

		if (dp[fromIndex][toIndex] >=0)
			return dp[fromIndex][toIndex];

		for (int i=fromIndex; i<toIndex;i++) {
			for (int j=i+1;j<=toIndex;j++) {
				if (colors[i]==colors[j]) {
					maxCount = Math.max(maxCount, 1 + countWays2DP(colors,i+1,j-1,dp) + countWays2DP(colors,j+1,toIndex,dp));
				}
			}
		}

		dp[fromIndex][toIndex] = maxCount;

		return maxCount;
	}

	// R -- R -- B -- R -- B -- R -- B -- B

	// B -- R -- B -- R -- B  ----SHOULD RETURN 2

	// TODO try to solve by remembering the String (in DP) instead of from and toIndex.
	// can store hashcode of string instead of complete string.
}
