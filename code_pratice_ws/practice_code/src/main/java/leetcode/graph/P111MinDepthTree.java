package leetcode.graph;

import java.util.Deque;
import java.util.ArrayDeque;

public class P111MinDepthTree {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    public int minDepth(TreeNode root) {
        // globalMin = 1000_000;
        // return minDepth2(root, 0);
        return minDepthStack(root);
    }

    int minDepthStack(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();

        root.val = 1;
        stack.offer(root);

        while (!stack.isEmpty()) {
            TreeNode elem = stack.pop();

            if (elem.left == null && elem.right == null) {
                return elem.val;
            }
            if (elem.left != null) {
                elem.left.val = elem.val + 1;
                stack.offer(elem.left);
            }
            if (elem.right != null) {
                elem.right.val = elem.val + 1;
                stack.offer(elem.right);
            }
        }
        return -1;
    }

    int globalMin;

    int minDepth2(TreeNode root, int depth) {
        if (depth > globalMin) {
            return depth;
        }
        int mindepth;
        if (root == null) {
            mindepth = depth;
        } else if (root.left == null && root.right == null) {
            mindepth = depth + 1;
        } else if (root.left == null) {
            mindepth = minDepth2(root.right, depth + 1);
        } else if (root.right == null) {
            mindepth = minDepth2(root.left, depth + 1);
        } else {
            mindepth = min(minDepth2(root.left, depth + 1), minDepth2(root.right, depth + 1));
        }

        globalMin = min(globalMin, mindepth);
        return mindepth;
    }

    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // check if it is a leaf
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        return min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    int min(int a, int b) {
        return (a < b) ? a : b;
    }
}

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
