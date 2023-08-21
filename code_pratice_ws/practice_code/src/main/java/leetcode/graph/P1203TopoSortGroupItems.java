package leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class P1203TopoSortGroupItems {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        var itemVsGroup = new HashMap<Integer, Integer>();

        // create group graph
        var gadjList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < m; i++) {
            gadjList.add(new ArrayList<>());
        }


        int gpid = m;
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                gadjList.add(new ArrayList<Integer>());
                itemVsGroup.put(i, gpid);
                gpid++;
            } else {
                itemVsGroup.put(i, group[i]);
            }
        }


        // create adj list and DAG
        var adjList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
        }

        // create DAG pointers for items and groups
        for (int i = 0; i < n; i++) {
            var bitems = beforeItems.get(i);
            for (int bitem : bitems) {
                if (bitem != i) {
                    var next = adjList.get(bitem);
                    next.add(i);
                }

                int fromGroup = itemVsGroup.get(bitem);
                int toGroup = itemVsGroup.get(i);
                if (fromGroup != toGroup) {
                    var gnext = gadjList.get(fromGroup);
                    gnext.add(toGroup);
                    // System.out.printf("gp add from: %d, to: %d\n", fromGroup, toGroup);
                }
            }
        }

        int[] gvisit = new int[gadjList.size()];
        var gtsort = new ArrayList<Integer>();
        // run topo sort for groups first
        for (int i = 0; i < gadjList.size(); i++) {
            if (gvisit[i] == 0) { // !WHITE
                gvisit[i] = 1; // GREY
                dfs(gadjList, gvisit, gtsort, i);
            }
        }

        Collections.reverse(gtsort);
        // System.out.printf("gtsort are %s\n", gtsort);
        if (gtsort.size() < gadjList.size()) { // no topo sort possible
            return new int[0];
        }

        // toposort the items
        int[] visit = new int[adjList.size()];
        var tsort = new ArrayList<Integer>();
        for (int i = 0; i < adjList.size(); i++) {
            if (visit[i] == 0) {
                visit[i] = 1; // GREY
                dfs(adjList, visit, tsort, i);
            }
        }

        Collections.reverse(tsort);
        // System.out.printf("items sorted are %s\n", tsort);
        if (tsort.size() < adjList.size()) { // no topo sort possible
            return new int[0];
        }

        var orderedGroup = new HashMap<Integer, List<Integer>>();
        for (int item : tsort) {
            int gp = itemVsGroup.get(item);
            var glist = orderedGroup.get(gp);
            if (glist == null) {
                glist = new ArrayList<Integer>();
                orderedGroup.put(gp, glist);
            }
            glist.add(item);
        }

        var ans = new ArrayList<Integer>();
        // return items by group
        for (int gp : gtsort) {
            var items = orderedGroup.get(gp);
            if (items != null) {
                ans.addAll(items);
            }
        }

        var ansar = new int[n];
        int i = 0;
        for (int item : ans) {
            ansar[i++] = item;
        }

        return ansar;

    }

    void dfs(ArrayList<ArrayList<Integer>> graph, int[] visited, List<Integer> tsorted, int node) {
        for (int next : graph.get(node)) {
            // if (node == next) {
            //     continue;
            // }
            if (visited[next] == 1) { // GREY, cycle
                // System.out.printf("found cycle with node: %d, next: %d\n", node, next);
                return;
            }
            if (visited[next] == 0) { // NOT WHITE
                visited[next] = 1; // GREY
                dfs(graph, visited, tsorted, next);
            }
        }
        visited[node] = 2; // BLACK
        tsorted.add(node);
    }
}
