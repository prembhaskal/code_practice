package leetcode.array;

public class P2369GoodPartition {

    public static void main(String[] args) {
        var sol = new P2369GoodPartition();
        int[] nums = new int[]{4, 2, 3, 4};
        System.out.printf("answer: %s\n", sol.validPartition(nums));
    }

    public boolean validPartition(int[] nums) {
        // return validPartitionRec(nums);
        return validPartitionDP(nums);
    }

    boolean validPartitionDP(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;

        for (int i = 1; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                dp[i + 1] = dp[i + 1] | dp[i - 1];
            }

            if (i > 1 && nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) {
                dp[i + 1] = dp[i + 1] | dp[i - 2];
            }

            if (i > 1 && nums[i] == nums[i - 1] + 1 && nums[i] == nums[i - 2] + 2) {
                dp[i + 1] = dp[i + 1] | dp[i - 2];
            }
        }

        return dp[n];
    }

    public boolean validPartitionRec(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = -1;
        }
        return solve(nums, 0, dp);
    }

    boolean solve(int[] nums, int idx, int[] dp) {
        int n = nums.length;
        if (idx >= n) {
            return true;
        }

        if (dp[idx] != -1) {
            return dp[idx] == 1;
        }

        if (idx + 1 < n && nums[idx] == nums[idx + 1]) {
            if (solve(nums, idx + 2, dp)) {
                dp[idx] = 1;
                return true;
            }
        }

        if (idx + 2 < n && nums[idx] == nums[idx + 1] && nums[idx] == nums[idx + 2]) {
            if (solve(nums, idx + 3, dp)) {
                dp[idx] = 1;
                return true;
            }
        }

        if (idx + 2 < n
                && nums[idx] == nums[idx + 1] - 1
                && nums[idx] == nums[idx + 2] - 2) {
            if (solve(nums, idx + 3, dp)) {
                dp[idx] = 1;
                return true;
            }
        }

        dp[idx] = 0;
        return false;
    }

}
