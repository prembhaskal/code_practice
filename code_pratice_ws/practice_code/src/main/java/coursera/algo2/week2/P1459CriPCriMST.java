package coursera.algo2.week2;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;


public class P1459CriPCriMST {
    int inf = 1000_000_000;

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        var edgelist = new ArrayList<Edge>();
        for (int i = 0; i < edges.length; i++) {
            edgelist.add(new Edge(i, edges[i][0], edges[i][1], edges[i][2]));
        }

        Collections.sort(edgelist, (e1, e2) -> {
            return Integer.compare(e1.dist, e2.dist);
        });

        int mst = getMst(n, edgelist, new UnionFind(n));
        // System.out.printf("mst is %d\n", mst);

        // critical edge -- if we remove mst becomes bigger or graph gets disconnected.
        // pseudo critical - if we include it, mst remains same
        // others -- if we add them, then mst will increase.
        // if edge is not critical and including it does not increases (others) , then it is pseudo critical.
        var critical = new HashSet<Integer>();
        for (Edge edge : edgelist) {
            edge.ignore = true;
            int newmst = getMst(n, edgelist, new UnionFind(n));
            // System.out.printf("critical: %d, newmst: %d\n", edge.idx, newmst);

            if (newmst > mst) {
                critical.add(edge.idx);
            }
            edge.ignore = false;
        }

        var pscritical = new ArrayList<Integer>();
        for (Edge cedge : edgelist) {
            if (!critical.contains(cedge.idx)) {
                // run separate kruskal
                var djset = new UnionFind(n);
                djset.union(cedge.from, cedge.to);
                cedge.ignore = true;


                int newmst = cedge.dist;
                // run mst
                newmst += getMst(n, edgelist, djset);

                cedge.ignore = false;
                // System.out.printf("pscritical: %d, newmst: %d\n", cedge.idx, newmst);
                if (newmst == mst) {
                    pscritical.add(cedge.idx);
                }
            }
        }

        var ans = new ArrayList<List<Integer>>();
        ans.add(new ArrayList<Integer>(critical));
        ans.add(pscritical);
        return ans;
    }

    int getMst(int n, List<Edge> edgelist, UnionFind djset) {
        // var djset = new UnionFind(n);

        int mst = 0;
        // run mst
        for (Edge edge : edgelist) {
            if (edge.ignore) {
                continue;
            }
            int rtf = djset.find(edge.from);
            int rtt = djset.find(edge.to);
            if (rtf != rtt) { // no cycle
                djset.union(edge.from, edge.to);
                mst += edge.dist;
            }
        }

        if (!djset.isConnected()) {
            return inf;
        }

        return mst;
    }
}

class Edge {
    int idx;
    int from;
    int to;
    int dist;
    boolean ignore;

    Edge(int idx, int from, int to, int dist) {
        this.idx = idx;
        this.from = from;
        this.to = to;
        this.dist = dist;
    }


}

class UnionFind {
    int[] root;
    int[] rank;

    public UnionFind(int n) {
        this.root = new int[n];
        this.rank = new int[n];

        // assign self as root
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
    }

    int find(int x) {
        if (x == root[x]) {
            return x;
        }
        int rt = find(root[x]);
        root[x] = rt;
        return rt;
    }

    void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (rx != ry) {
            if (rank[rx] > rank[ry]) {
                root[ry] = rx;
            } else if (rank[ry] > rank[rx]) {
                root[rx] = ry;
            } else {
                root[ry] = rx;
                rank[rx]++;
            }
        }
    }

    boolean isConnected() {
        for (int i = 1; i < root.length; i++) {
            if (find(i) != find(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
