package leetcode.array;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class P56MergeIntervals {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        Interval[] inters = new Interval[n];
        int i = 0;
        for (int[] interval : intervals) {
            inters[i] = new Interval(interval[0], interval[1]);
            i++;
        }

        // sort by start
        Arrays.sort(inters, new Comparator<Interval>() {
            public int compare(Interval self, Interval other) {
                if (self.start < other.start) {
                    return -1;
                } else if (self.start > other.start) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        List<Interval> overlap = new ArrayList<>();

        Interval prev = inters[0];
        for (i = 1; i < inters.length; i++) {
            Interval curr = inters[i];
            // check if prev overlaps with curr
            if (prev.end >= curr.start) {
                // merge both intervals
                int newstart = prev.start;
                int newend = max(curr.end, prev.end);
                Interval newinter = new Interval(newstart, newend);
                prev = newinter;
            } else {
                // prev is not overlapping, save it.
                overlap.add(prev);
                prev = curr;
            }
        }
        overlap.add(prev);


        // make new array
        int[][] overar = new int[overlap.size()][2];
        i = 0;
        for (Interval over : overlap) {
            overar[i][0] = over.start;
            overar[i][1] = over.end;
            i++;
        }

        return overar;
    }

    int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    class Interval {
        int start;
        int end;

        Interval(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

}
