package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class P46Permute {

    List<List<Integer>> answer;
    public List<List<Integer>> permute(int[] nums) {
        answer = new ArrayList<>();
        permuteR(nums, 0);
        return answer;
    }

    void permuteR(int[] nums, int idx) {
        if (idx == nums.length) {
            List<Integer> copy = new ArrayList<>();
            for (int num: nums) {
                copy.add(num);
            }
            answer.add(copy);
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            swap(nums, i, idx);
            permuteR(nums, idx + 1);
            swap(nums, i, idx);
        }
    }

    void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
