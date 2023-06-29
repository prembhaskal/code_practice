package binsearch;

public class FindNthRoot {
    public static int NthRoot(int n, int m) {
        // Write your code here.

        int start = 1;
        int end = 1000_000_000;

        while (start < end) {
            int mid = start + (end - start) / 2;
            long pow = check(n, mid);
            // System.out.printf("start: %d, end: %d, mid: %d, pow: %d\n", start, end, mid, pow);
            if (pow == m) {
                return mid;
            } else if (pow > m || pow <= 0) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }

        if (start == end) {
            long pow = check(n, start);
            if (pow == m) {
                return start;
            }
        }


        return -1;
    }

    public static long check(int n, int root) {
        long pow = 1;
        for (int i = 0; i < n; i++) {
            pow = pow * root;
        }

        return pow;
    }

}