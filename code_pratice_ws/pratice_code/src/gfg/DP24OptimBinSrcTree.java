package gfg;

import java.io.*;
import java.util.*;


public class DP24OptimBinSrcTree {
//    https://practice.geeksforgeeks.org/problems/optimal-binary-search-tree2214/1

    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(read.readLine());
            String input_line[] = read.readLine().trim().split("\\s+");
            int keys[] = new int[n];
            for (int i = 0; i < n; i++)
                keys[i] = Integer.parseInt(input_line[i]);
            String input_line1[] = read.readLine().trim().split("\\s+");
            int freq[] = new int[n];
            for (int i = 0; i < n; i++)
                freq[i] = Integer.parseInt(input_line1[i]);
            DP24OptimBinSrcTree ob = new DP24OptimBinSrcTree();
            System.out.println(ob.optimalSearchTree(keys, freq, n));
        }
    }
    
    static int optimalSearchTree(int keys[], int freq[], int n) {
        return fn2(keys, freq, n);
    }


    static int fn2(int[] keys, int[] freq, int n) {
        int[][] memo = new int[n][n];

        return f2(0, n - 1, freq, memo);
    }

    // no level needed in this impl.
    // see https://www.geeksforgeeks.org/optimal-binary-search-tree-dp-24/ for clean info
    // try to write formula on paper, cost of tree at level (L) = cost of tree at level L-1 + (sum of all nodes of tree)
    // thus we don't need the level variable at all.
    static int f2(int start, int end, int[] freq, int[][] memo) {
        if (start > end) {
            return 0;
        }
        if (memo[start][end] != 0) {
            return memo[start][end];
        }
        if (start == end) {
            memo[start][end] = freq[start];
            return freq[start];
        }

        int rsum = rangesum(start, end, freq);

        int minval = 1000000;
        for (int curr = start; curr <= end; curr++) {
            int lcost = f2(start, curr - 1, freq, memo);
            int rcost = f2(curr + 1, end, freq, memo);
            minval = min(minval, lcost + rcost + rsum);
        }

        memo[start][end] = minval;
        return minval;

    }

    static int rangesum(int s, int e, int[] val) {
        int sum = 0;
        for (int i = s; i <= e; i++) {
            sum += val[i];
        }
        return sum;
    }

    static int fn3(int[] keys, int[] freq, int n) {
        int[][][] memo = new int[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n + 1; k++) {
                    memo[i][j][k] = -1;
                }
            }
        }
        // code here
        return f(0, n - 1, 1, freq, memo);
    }

    static int f(int start, int end, int level, int[] freq, int[][][] memo) {

        // base checks
        if (start > end) {
            return 0;
        }
        if (memo[start][end][level] != -1) {
            return memo[start][end][level];
        }


        if (start == end) {
            memo[start][end][level] = level * freq[start];
            // System.out.printf("memo[%d][%d][%d] == %d\n", start, end, level, memo[start][end][level]);
            return memo[start][end][level];
        }

        int minVal = 1000000;

        for (int curr = start; curr <= end; curr++) {
            int lval = f(start, curr - 1, level + 1, freq, memo);
            int cval = level * freq[curr];
            int rval = f(curr + 1, end, level + 1, freq, memo);
            minVal = min(minVal, lval + cval + rval);
        }
        memo[start][end][level] = minVal;
        // System.out.printf("memo[%d][%d][%d] == %d\n", start, end, level, memo[start][end][level]);
        return minVal;
    }

    static int min(int a, int b) {
        return a < b ? a : b;
    }

}


