package com.prem.srm.m566.div2;

import java.util.Arrays;

/**
 * User: premkumar.bhaskal
 * Date: 1/18/13
 * Time: 3:13 PM
 */
public class PenguinPals {

	public int getCount(String circle) {
		int[][] dp = new int[50][50];

		for (int i=0;i<50;i++)
			Arrays.fill(dp[i],-1);

		return getPalCount(0,circle.length(), circle.toCharArray(), dp);
	}

	private int getPalCount(int fromIndex, int toIndex, char[] circleArray, int[][] dp) {
		int maxCount = 0;
		if (fromIndex >= toIndex)
			return maxCount;

		if (dp[fromIndex][toIndex] > 0)
			return dp[fromIndex][toIndex];

		for (int i=fromIndex;i<circleArray.length;i++) {
			for (int j=i+1; j < toIndex;j++) {
				if (circleArray[i] == circleArray[j])
					maxCount = Math.max(maxCount, 1 + getPalCount(i+1,j-1,circleArray,dp)
													+ getPalCount(j+1, toIndex, circleArray, dp));
			}
		}

		dp[fromIndex][toIndex] = maxCount;

		return dp[fromIndex][toIndex];
	}
}
