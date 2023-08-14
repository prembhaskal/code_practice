package leetcode.array;

import java.util.Random;
import java.util.PriorityQueue;

public class P215KthLargest {
    public int findKthLargest(int[] nums, int k) {
//        return heapbased(nums, k);
        return quickselect(nums, 0, nums.length - 1, k - 1);
    }
    
    // slower O(klogk)
    int heapbased(int[] nums, int k) {
        var heap = new PriorityQueue<Integer>();
        for (int num : nums) {
            heap.add(num);

            if (heap.size() > k) {
                heap.remove();
            }
        }
        return heap.peek();
    }

    // quick select in descending order.
    // O(n)
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
