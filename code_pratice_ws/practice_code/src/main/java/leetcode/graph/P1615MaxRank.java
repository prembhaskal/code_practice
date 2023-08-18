package leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;

public class P1615MaxRank {
    public int maximalNetworkRank(int n, int[][] roads) {
        // make adjacency list
        var adjList = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<>());
        }

        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        int max = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int frmcount = adjList.get(i).size();
                int tocount = adjList.get(j).size();
                int rank = frmcount + tocount;
                if (adjList.get(i).contains(j)) {
                    rank--;
                }
                max = Math.max(max, rank);
            }
        }

        return max;


    }
}
