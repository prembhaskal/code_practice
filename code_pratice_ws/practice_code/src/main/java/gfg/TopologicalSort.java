package gfg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopologicalSort {

    //Function to return list containing vertices in Topological order.
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // add your code here
        return topologicalSortKahnAlgo(V, adj);
    }

    static int[] topologicalSortKahnAlgo(int V, ArrayList<ArrayList<Integer>> adj) {
        // inbound nodes map
        Map<Integer, Integer> inbounds = new HashMap<>();
        for (int i = 0; i < V; i++) {
            for (int to : adj.get(i)) {
                int cnt = inbounds.getOrDefault(to, 0);
                cnt++;
                inbounds.put(to, cnt);
            }
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        // BFS kind
        for (int i = 0; i < V; i++) {
            if (inbounds.getOrDefault(i, 0) == 0) {
                queue.add(i);
            }
        }

        int[] topolist = new int[V];
        int i = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : adj.get(node)) {
                int cnt = inbounds.get(next);
                cnt--;
                inbounds.put(next, cnt);
                if (cnt == 0) {
                    queue.add(next);
                }
            }
            topolist[i] = node;
            i++;
        }
        return topolist;
    }

    static int[] topolist1;
    static int index = 0;

    static int[] topologicalDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        topolist1 = new int[V];
        index = 0;
        var visited = new int[V];
        for (int i = 0; i < V; i++) {
            dfsColor(i, visited, adj);
        }
        // reverse list
        reverse(topolist1);
        return topolist1;
    }

    static void reverse(int[] ar) {
        int i = 0;
        int j = ar.length - 1;
        while (i < j) {
            int temp = ar[i];
            ar[i] = ar[j];
            ar[j] = temp;
            i++;
            j--;
        }
    }


    static void dfsColor(int curr, int[] visited, ArrayList<ArrayList<Integer>> adjList) {
        if (visited[curr] == 2) {
            // already visited previously
            return;
        }

        visited[curr] = 1; // mark grey , visiting
        for (int next : adjList.get(curr)) {
            dfsColor(next, visited, adjList);
        }
        visited[curr] = 2; // mark black
        topolist1[index] = curr;
        index++;
    }


}
