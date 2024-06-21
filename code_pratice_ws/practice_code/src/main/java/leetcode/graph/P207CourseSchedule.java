package leetcode.graph;

import java.util.ArrayList;

public class P207CourseSchedule {
    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        int[][] pre = prerequisites;
        // create graph
        var adjList = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        for (int[] pres : prerequisites) {
            int from = pres[0];
            int to = pres[1];
            var nexts = adjList.get(from);
            nexts.add(to);
        }

        hasCycle = false;
        var visited = new int[n];
        for (int i = 0; i < n; i++) {
            dfsColor(i, visited, adjList);
        }
        return hasCycle;
    }

    void dfsColor(int curr, int[] visited, ArrayList<ArrayList<Integer>> adjList) {
        if (visited[curr] == 2) {
            // already visited previously
            return;
        }
        if (visited[curr] == 1) {
            // cycle detected
            hasCycle = true;
            return;
        }
        visited[curr] = 1; // mark grey , visiting
        for (int next : adjList.get(curr)) {
            dfsColor(next, visited, adjList);
        }
        visited[curr] = 2; // mark black
    }
}
