package leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

// TODO - sort with earlier endtime, to get O(n) time complexity.
public class P435NonOverlap {

    public static void main(String[] args) {
        var sol = new P435NonOverlap();
//        var ints = new int[][]{{1,2}, {2,3}, {3,4}, {1,3}};
//                [[1,2],[2,3],[3,4],[1,3]]
//        {{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}}
        var ints = new int[][]{{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}};
//        var ints = new int[][]{{1, 100}, {2, 3}, {4, 5}, {7, 10}};
        var ans = sol.eraseOverlapIntervals(ints);
        System.out.printf("ans: %d\n", ans);
    }
    public int eraseOverlapIntervals(int[][] intervals) {
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
                    return Integer.compare(o1end, o2end); // first get longer one.
                }

            }
        });

//        1 10 5 15 5 100, 19 30
//        -1------10---
//             -5---------15-
//             -5-----------------------------------100-
//                           -19--------30-

        // remove curr, not remove curr
        // [a, b, c, d]  --> X // keep X, only if does not overlap with 'd'
        // [a, b, c, d]  --> X // remove X,

        //    remove(idx) +  f(idx+1)
        //    keep(idx) +  f(idx+1)

        //

        System.out.println("sorted:");
        for (var intv: intervals) {
            System.out.print(Arrays.toString(intv) + " ");
        }
        System.out.println("");

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

        // find next overlap
//        int next = curridx + 1;
//        for (; next < intervals.length; next++) {
//            var nextintv = intervals[next];
//            if (nextintv[0] >= currintv[1]) {
//                break;
//            }
//        }

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



    private int removeFirst(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }
        int toremove = 1;
        var prev = intervals[1];
        for (int i = 2; i < intervals.length; i++) {
            var curr = intervals[i];
            // check if overlap
            if (curr[0] < prev[1]) {
                // overlap, so we remove this.
                System.out.printf("to remove interval: %s\n", Arrays.toString(curr));
                toremove++;
            } else {
                prev = curr;
            }
        }
        return toremove;
    }

    private int removeFromSecond(int[][] intervals) {
        int toremove = 0;
        var prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            var curr = intervals[i];
            // check if overlap
            if (curr[0] < prev[1]) {
                // overlap, so we remove this.
                System.out.printf("to remove interval: %s\n", Arrays.toString(curr));
                toremove++;
            } else {
                prev = curr;
            }
        }
        return toremove;
    }
}
