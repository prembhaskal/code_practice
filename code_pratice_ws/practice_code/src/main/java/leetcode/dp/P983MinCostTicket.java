package leetcode.dp;

import java.util.Arrays;

public class P983MinCostTicket {
    public int mincostTickets(int[] days, int[] costs) {
        // int[] mem = new int[days.length];
        // Arrays.fill(mem, -1);
        // return minCost(days, costs, 0, mem);
        return bottomup(days, costs);
    }

    int bottomup(int[] days, int[] costs) {
        int[] present = new int[366];
        for (int i = 0; i < days.length; i++) {
            present[days[i]] = 1;
        }

        int[] dp = new int[366];


        Arrays.fill(dp, 1000_000_000);
        dp[0] = 0;

        for (int i = 1; i <= days[days.length - 1]; i++) {
            if (present[i] == 0) {
                dp[i] = dp[i - 1];
                continue;
            }
            // one day ticket
            dp[i] = Math.min(dp[i], dp[i - 1] + costs[0]);
            // 7 day ticket
            if (i - 7 >= 0) {
                dp[i] = Math.min(dp[i], dp[i - 7] + costs[1]);
            } else {
                dp[i] = Math.min(dp[i], costs[1]);
            }
            if (i - 30 >= 0) {
                dp[i] = Math.min(dp[i], dp[i - 30] + costs[2]);
            } else {
                dp[i] = Math.min(dp[i], costs[2]);
            }
        }
        // System.out.printf("dp is %s\n", Arrays.toString(dp));
        return dp[days[days.length - 1]];
    }

    int minCost(int[] days, int[] costs, int idx, int[] mem) {
        if (idx >= days.length) {
            return 0;
        }
        if (mem[idx] != -1) {
            return mem[idx];
        }
        int minCost = 1000_000_00;
        int onecost = costs[0] + minCost(days, costs, getNextDayBinSearch(days, idx, 1), mem);
        int sevencost = costs[1] + minCost(days, costs, getNextDayBinSearch(days, idx, 7), mem);
        int thirtycost = costs[2] + minCost(days, costs, getNextDayBinSearch(days, idx, 30), mem);
        mem[idx] = Math.min(onecost, Math.min(sevencost, thirtycost));
        return mem[idx];
    }

    int getNextDayIdx(int[] days, int curr, int gap) {
        for (int i = curr + 1; i < days.length; i++) {
            if (days[i] - days[curr] >= gap) {
                return i;
            }
        }
        return days.length; // out of bounds
    }

    // return idx of day which is greater or equal to nextDay
// if no such days exists, it returns days.length
    int getNextDayBinSearch(int[] days, int currIdx, int gap) {
        int low = currIdx;
        int high = days.length - 1;
        int key = days[currIdx] + gap;
        int nextIdx = days.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (days[mid] >= key) {
                nextIdx = mid;
                high = mid - 1;
            } else if (days[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return nextIdx;
    }

}
