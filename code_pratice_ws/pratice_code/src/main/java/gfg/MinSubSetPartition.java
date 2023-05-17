package gfg;
import java.io.*;
import java.util.*;

//https://practice.geeksforgeeks.org/problems/minimum-sum-partition3317/1?utm_source=geeksforgeeks&utm_medium=article_practice_tab&utm_campaign=article_practice_tab
public class MinSubSetPartition
{

    public int minDifference(int arr[], int n)
    {
        // Your code goes here

        // with first 'idx' elements in array, is it possible to achieve 'target_sum'
        // DP[idx][target_sum] == true/false

        int sum = 0;
        for (int a : arr) {
            sum += a;
        }

        boolean [][] dp = new boolean[n+1][sum+1];
        // base case
        // when we don't choose any, we get sum = 0
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // not choose ith element
                boolean not_take = dp[i-1][j];

                boolean take = false;
                // choose ith element
                if (j >= arr[i-1]) {
                    take = dp[i-1][j - arr[i-1]];
                }
                dp[i][j] = not_take || take;
            }
        }



        // final row has all answers
        int min_diff = 2 * sum ; // some high number
        for (int j = 0; j <= sum; j++) {
            if (dp[n][j]) {
                min_diff = min(min_diff, abs(sum - j - j));
            }
        }

        return min_diff;
    }

    int min(int a , int b) {
        return a < b ? a : b;
    }

    int abs(int a) {
        return a < 0 ? -a : a;
    }
}
