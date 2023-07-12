package leetcode.slidewin;

public class P209MinSizeSubArr {
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0;
        int end = 0;
        int n = nums.length;

        int currsum = 0;
        int minlen = n + 1;
        for (; start < n && end < n; end++) {
            currsum = currsum + nums[end];
            // keep removing from start as much as possible
            while (currsum >= target) {
                // System.out.printf("tgt currsum: %d, start: %d, end: %d\n", currsum, start, end);
                minlen = min(minlen, end - start + 1);
                currsum = currsum - nums[start];
                start++;
            }
        }

        return minlen > n ? 0 : minlen;
    }

    int min(int a, int b) {
        return a < b ? a : b;
    }
}
