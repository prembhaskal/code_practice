package leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

public class P435NonOverlapIntervals {

    public static void main(String[] args) {
        var sol = new P435NonOverlapIntervals();
        var ints = new int[][]{{1,2}, {2,3}, {3,4}, {1,3}};
//                [[1,2],[2,3],[3,4],[1,3]]
//        {{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}}
//        var ints = new int[][]{{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}};
//        var ints = new int[][]{{1, 100}, {2, 3}, {4, 5}, {7, 10}};
        var ans = sol.eraseOverlapIntervals(ints);
        System.out.printf("ans: %d\n", ans);
    }

    public int eraseOverlapIntervals(int[][] intervals) {
//        return solveDPWithBinSearch(intervals);
        return solveWithGreedyRadixSort(intervals);
//        return solveWithGreedy(intervals);
    }

    public int solveWithGreedy(int[][] intervals) {
        // sort by endtime
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] < o2[1]) {
                    return -1;
                } else if (o1[1] > o2[1]) {
                    return 1;
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });

        // find max possible without overlap
        int total = 1;
        var prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            var curr = intervals[i];
            // see if we don't overlap
            if (prev[1] <= curr[0]) {
                total++;
                prev = curr;
            }
        }

        return intervals.length - total;
    }

    // This runs much fast ~ 11ms.
    public int solveWithGreedyRadixSort(int[][] intervals) {
        if (intervals.length < 10000) {
            return solveWithGreedy(intervals);
        }
        // sort by endtime
        var starttimes = radixSort(intervals);

//        System.out.printf("sorted starttimes\n");
//        for (int i = 0; i < starttimes.length; i++) {
//            if (starttimes[i] != -1) {
//                System.out.printf("i: %d, starttime: %d\n", i, starttimes[i]);
//            }
//        }
//        System.out.printf("done print\n");

        // find first
        int i = 0;
        int prevend = -1;
        for (; i < starttimes.length; i++) {
            if (starttimes[i] != -1) {
                prevend = i;
                break;
            }
        }
        if (prevend == -1) {
            throw new RuntimeException("something wrong");
        }
        // find max possible without overlap
        int total = 1;
        i = i + 1;
        for (; i < starttimes.length; i++) {
            var currend = i;
            var currstart = starttimes[i];
            if (currstart == -1) {
                continue; // non real
            }
            // see if we don't overlap
            if (prevend <= currstart) {
                total++;
                prevend = currend;
            }
        }
        return intervals.length - total;
    }


    int[] radixSort(int[][] arr) {
        int SHIFT=50_000;
        int[] starttimes = new int[100_002];

        // fill with empty marker
        Arrays.fill(starttimes, -1);

        for (var ar: arr) {
            int end = ar[1] + SHIFT;
            // retain only smaller intervals
            starttimes[end] = Math.max(starttimes[end], ar[0] + SHIFT);
        }
        return starttimes;
    }

//    sort intervals by (start,end)
//     try all possible combinations, with memory and bnary search to find next non-overlapping interval
    public int solveDPWithBinSearch(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int o1start = o1[0];
                int o1end = o1[1];
                int o2start = o2[0];
                int o2end = o2[1];

                if (o1start < o2start) {
                    return -1;
                } else if (o1start > o2start) {
                    return 1;
                } else {
                    return Integer.compare(o1end, o2end);
                }
            }
        });

//        1 10 5 15 5 100, 19 30
//        -1------10---
//             -5---------15-
//             -5-----------------------------------100-
//                           -19--------30-
        //    remove(idx) +  f(idx+1)
        //    keep(idx) +  f(idx+1)

        int[] mem = new int[intervals.length];
        Arrays.fill(mem, -1);

        int mxattend = maxAttend(intervals, 0, mem);
        return intervals.length - mxattend;
    }


    // min remove  == max attend
    private int maxAttend(int[][] intervals, int curridx, int[] mem) {
        if (curridx >= intervals.length) {
            return 0;
        }

        if (mem[curridx] != -1) {
            return mem[curridx];
        }

        // curr interval
        var currintv = intervals[curridx];
        int next = upperBound(intervals, curridx);

        int attend = maxAttend(intervals, next, mem) + 1;
        int skip = maxAttend(intervals, curridx + 1, mem);

        mem[curridx] = Math.max(attend, skip);
        return mem[curridx];
    }


    int upperBound(int[][] intervals, int start) {
        int low = start + 1;
        int high = intervals.length - 1;

        int ans = intervals.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (intervals[mid][0] >= intervals[start][1]) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }


}
