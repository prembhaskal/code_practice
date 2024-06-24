package leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

public class P1235MaxProfit {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] alljobs = new int[n][3]; // 0 -> startTime, 1 -> endtime, 2 -> profit
        for (int i = 0; i < n; i++) {
            alljobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(alljobs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        // Arrays.sort(alljobs, (a,b) -> a[0] - b[0]); // shorthand

        return recFnHelper(alljobs);
    }

    public int recFnHelper(int[][] alljobs) {
        int[] dp = new int[alljobs.length];
        Arrays.fill(dp, -1);
        return recFn(0, alljobs, dp);
    }

    // max profit can be obtained by starting the jobs at index job
    public int recFn(int job, int[][] alljobs, int[] dp) {
        // System.out.printf("current job: %d\n", job);
        // base
        if (job == alljobs.length) {
            return 0;
        }
        if (dp[job] > 0) {
            return dp[job];
        }

        int maxProfit = 0;
        // find jobs which can be picked
        int nextJob = findNextJobBinSearch(alljobs, job);
        int chooseCurrent = alljobs[job][2];
        if (nextJob != -1) {
            chooseCurrent = recFn(nextJob, alljobs, dp) + alljobs[job][2];
        }
        int notChooseCurrent = recFn(job + 1, alljobs, dp);

        dp[job] = Math.max(chooseCurrent, notChooseCurrent);
        return dp[job];
    }

    public int findNextJob(int[][] alljobs, int currJob) {
        for (int nextJob = currJob + 1; nextJob < alljobs.length; nextJob++) {
            int nextStart = alljobs[nextJob][0];
            int currEnd = alljobs[currJob][1];
            if (nextStart >= currEnd) {
                return nextJob;
            }
        }
        return -1;
    }

    public int findNextJobBinSearch(int[][] alljobs, int currJob) {
        int low = currJob + 1;
        int high = alljobs.length - 1;
        int next = -1;
        int currEnd = alljobs[currJob][1];
        while (low <= high) {
            int mid = (low + high) / 2;
            int currStart = alljobs[mid][0];
            if (currStart >= currEnd) {
                next = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return next;
    }
}