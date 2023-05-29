package leetcode.graph;

import java.util.Map;
import java.util.HashMap;

//    https://leetcode.com/problems/path-sum-iii/description/
public class P437PathSum3 {
    /**
     * Definition for a binary tree node.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> sumCount = new HashMap<>();
        sumCount.put(0L, 1);
        return pathSumCal(root, targetSum, 0, sumCount);
    }

    public int pathSumCal(TreeNode root, int tsum, long rsum, Map<Long, Integer> sumCount) {
        int finalCount = 0;
        if (root == null) {
            return 0;
        }
        // System.out.printf("rsum: %d, val: %d\n", rsum, root.val);
        rsum = rsum + root.val;
        // System.out.printf("new rsum: %d\n", rsum);

        long findSum = rsum - tsum;

        int matchCount = 0;
        Integer matchCountObj = sumCount.get(findSum);
        if (matchCountObj != null) {
            matchCount = matchCountObj;
        }

        // if (rsum == tsum) {
        //     finalCount += 1;
        // }

        finalCount += matchCount;

        // System.out.printf("curr: %d, rsum: %d, findSum: %d, matchCount: %d \n",
        //     root.val, rsum, findSum, matchCount);

        // update map
        Integer rsumObj = sumCount.get(rsum);
        if (rsumObj == null) {
            rsumObj = 0;
        }
        sumCount.put(rsum, rsumObj + 1);
        // System.out.printf("map add: key: %d, val: %d\n", rsum, rsumObj + 1);
        int leftCount = pathSumCal(root.left, tsum, rsum, sumCount);
        finalCount += leftCount;

        int rightCount = pathSumCal(root.right, tsum, rsum, sumCount);
        finalCount += rightCount;
        // remove from map
        sumCount.put(rsum, rsumObj);

        return finalCount;
    }


}

