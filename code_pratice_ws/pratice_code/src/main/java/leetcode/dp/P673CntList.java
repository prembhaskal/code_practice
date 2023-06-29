package leetcode.dp;

public class P673CntList {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        var ldp = new int[nums.length];
        var cnt = new int[nums.length];

        int maxlen = 1;
        int liscnt = 0;
        for (int c = 0; c < n; c++) {
            // base case, single elem is LIS in itself
            ldp[c] = 1;
            cnt[c] = 1;
            for (int p = 0; p < c; p++) {
                if (nums[c] > nums[p]) {
                    if (ldp[c] == ldp[p] + 1) { // same len LIS, add to count
                        cnt[c] += cnt[p];
                    } else if (ldp[c] < ldp[p] + 1) { // more len LIS, use that count
                        ldp[c] = ldp[p] + 1;
                        cnt[c] = cnt[p];
                    }
                }
            }
            if (ldp[c] == maxlen) {
                liscnt += cnt[c];
            } else if (ldp[c] > maxlen) {
                maxlen = ldp[c];
                liscnt = cnt[c];
            }
        }
        return liscnt;
    }
}
