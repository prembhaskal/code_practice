package leetcode.array;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class P15ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        // sort the array
        // choose i element, find in A[i-end] , two nos with sum = -A[i]
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break; // we cannot make sum zero
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int need = -nums[i];
            int start = i + 1;
            int end = nums.length - 1;
            boolean isFirst = true;
            while (start < end) {
                int sum = nums[start] + nums[end];
                if (sum == need) {
                    // addToList(nums[i], nums[start], nums[end], ans);
                    ans.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    // recheck this.
                    start++;
                    end--;
                } else if (sum > need) {
                    end--;
                } else {
                    start++;
                }

                while (start < end && end < nums.length - 1 && nums[end] == nums[end + 1]) {
                    end--; // avoid duplicate couplet
                }
            }
        }
        return ans;
    }

    // void addToList(int a, int b, int c, List<List<Integer>> ans) {
    //     // if (ans.size() > 0) {
    //     //     List<Integer> prev = ans.get(ans.size() - 1);
    //     //     if (prev.get(0) == a && prev.get(1) == b && prev.get(2) == c) {
    //     //         return;
    //     //     }
    //     // }
    //     List<Integer> newList = new ArrayList<>();
    //     newList.add(a);
    //     newList.add(b);
    //     newList.add(c);
    //     ans.add(newList);
    // }
}
