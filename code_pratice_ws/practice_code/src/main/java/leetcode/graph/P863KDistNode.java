package leetcode.graph;

import java.util.List;
import java.util.ArrayList;

public class P863KDistNode {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    // TODO - another approach, convert bin tree to normal graph, then perform BFS from target node and stop and distance k
        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            // from node, go left k times, go right k times
            // dist from root, if left = x, search node in right at dist = k-x
            allNodes = new ArrayList<>();
            find(root, target, k);
            return allNodes;
        }
        List<Integer> allNodes = new ArrayList<>();

//       Approach - find the path till target
//        search and add nodes from below target
//        while back tracking, add nodes from direction other than the target is found.
        public int find(TreeNode node, TreeNode target, int k) {
            if (node == null) {
                return -1; // nothing to search below
            }

            if (node.val == target.val) {
                if (k == 0) { // corner case
                    allNodes.add(node.val);
                }
                nodeAtDist(node.left, k-1);
                nodeAtDist(node.right, k-1);
                return 0;
            }

            int inleft = find(node.left, target, k);
            // System.out.printf("at node: %d, inleft: %d\n", node.val, inleft);
            if (inleft >= 0) {
                if (inleft + 1 == k) { // check if current element is at k distance
                    allNodes.add(node.val);
                }
                nodeAtDist(node.right, k - (inleft+1) - 1); // draw on paper to get this calculation.
                return inleft + 1; // +1 to count current node
            }

            int inright = find(node.right, target, k);
            // System.out.printf("at node: %d, inright: %d\n", node.val, inright);
            if (inright >=0) {
                if (inright + 1 == k) { // check if current element is at k distance
                    allNodes.add(node.val);
                }
                nodeAtDist(node.left, k - (inright+1) - 1);
                return inright + 1; // +1 to count current node
            }

            return -1;// nothing found
        }

        public void nodeAtDist(TreeNode node, int k) {
            if (node == null) {
                return;
            }
            if (k < 0) {
                return;
            }
            if (k == 0) {
                allNodes.add(node.val);
                return;
            }
            nodeAtDist(node.left, k - 1);
            nodeAtDist(node.right, k - 1);
        }

}
