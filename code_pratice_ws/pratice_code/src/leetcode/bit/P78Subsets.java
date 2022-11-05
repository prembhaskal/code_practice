package leetcode.bit;

import java.util.ArrayList;
import java.util.List;

public class P78Subsets {
    public static void main(String[] args) {
        var soln = new P78Subsets();
        var nums = new int[]{1,2,3,4};
        var output = soln.subsets(nums);
        System.out.printf("list output: %s\n", output);
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> finallist = new ArrayList<>();
        return addSubsets(nums, new ArrayList<Integer>(), 0, finallist);
    }

    private List<List<Integer>> addSubsets(int[] nums, List<Integer> curr, int idx, List<List<Integer>> finallist) {
        if (idx >= nums.length) {
            finallist.add(new ArrayList<>(curr));
            return finallist;
        }

        // choose
        curr.add(nums[idx]);
        finallist = addSubsets(nums, curr, idx + 1, finallist);
        curr.remove(curr.size() - 1);

        return addSubsets(nums, curr, idx + 1, finallist);
    }
}
