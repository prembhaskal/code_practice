package leetcode.bit;

import java.util.*;

class P90SubsetDup {
    public static void main(String[] args) {
        var sol = new P90SubsetDup();
        var out = sol.subsetsWithDup(new int[]{1,2,2,2,3,3});
        System.out.printf("subsets with duplicates: %s\n", out);
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        var numfreq = new HashMap<Integer, Integer>();
        int prev = -100;
        var uniq = new ArrayList<Integer>();
        for(int num: nums) {
            int cnt = numfreq.getOrDefault(num, 0);
            numfreq.put(num, cnt + 1);
            if (num != prev) {
                uniq.add(num);
                prev = num;
            }
        }

        List<List<Integer>> allList = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();
        addSubsetsWithDup(uniq.stream().mapToInt(i -> i).toArray(), numfreq, 0, curr, allList);
        return allList;
    }

    private void addSubsetsWithDup(int[] uniq, Map<Integer,Integer> numfreq, int idx,
                                   List<Integer> curr, List<List<Integer>> allList) {
        if (idx >= uniq.length) {
            allList.add(curr);
            return;
        }

        int num = uniq[idx];
        int freq = numfreq.get(num);
        for (int i = 0; i <= freq; i++) {
            // choose 0 to i times
            var copy = new ArrayList<>(curr);
            for (int j = 0; j < i; j++) {
                copy.add(num);
            }
            addSubsetsWithDup(uniq, numfreq, idx + 1, copy, allList);
        }
    }
}