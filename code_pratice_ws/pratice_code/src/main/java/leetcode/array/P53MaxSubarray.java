package leetcode.array;

public class P53MaxSubarray {
    public int maxSubArray(int[] nums) {
        // Kadane's algo

        int max = -10001;
        int sum = 0;

        for (int num : nums) {
            sum = sum + num;
            if (sum > max) {
                max = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
            // System.out.printf("num: %d, sum: %d, max: %d\n", num, sum, max);
        }

        return max;
    }
}
