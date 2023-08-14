package leetcode.array;

import java.util.Random;

public class P215KthLargest {
    public int findKthLargest(int[] nums, int k) {
        return quickselect(nums, 0, nums.length - 1, k - 1);
    }

    // quick select in descending order,
    int quickselect(int[] nums, int start, int end, int k) {
        int pivot = new Random().nextInt(end - start + 1) + start;
        swap(nums, start, pivot);
        int j = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] >= nums[start]) {
                j++;
                swap(nums, j, i);
            }
        }
        swap(nums, start, j);

        if (j == k) {
            return nums[k];
        }
        if (k < j) {
            return quickselect(nums, start, j - 1, k);
        } else {
            return quickselect(nums, j + 1, end, k);
        }
    }

    void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }
}
