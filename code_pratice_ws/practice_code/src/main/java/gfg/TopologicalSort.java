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

}
