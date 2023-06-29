package leetcode.dp;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class P1547CutStickCost {

    public static void main(String[] s) {
//        int n = 7;
//        var cuts = new int[]{1,3,4,5};
        int n = 30;
        var cuts = new int[]{13,25,16,20,26,5,27,8,23,14,6,15,21,24,29,1,19,9,3};
        int cutcost = new P1547CutStickCost().minCost(n, cuts);
        System.out.printf("answer is %d\n", cutcost);
    }
// TODO - optimize by using indices of cuts instead of string to run it faster
// DP[i][j] = min cost for cutting between cuts[i] and cuts[j] (both not included ??)
// which is equal to (cuts[j] - cuts[i]) + DP[i][k] + DP[k][j] where k ranges from i + 1 to j - 1
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        return calcMinCost(0, n, cuts, new HashMap<>(), 0, cuts.length);
    }

    private int calcMinCost(int start, int end, int[] cuts, Map<Pair, Integer> dp, int cs, int ce) {
        if (start >= end) {
            return 0;
        }

        var dpval = dp.get(new Pair(start, end));
        if (dpval != null) {
            return dpval;
        }

        int mincost = Integer.MAX_VALUE;
        var foundcut = false;
        for (int cidx = cs; cidx < ce; cidx++) {
            int cut = cuts[cidx];
            if (start < cut && end > cut) {
                foundcut = true;
                int cutcost = calcMinCost(start, cut, cuts, dp, cs, cidx) + calcMinCost(cut, end, cuts, dp, cidx, ce) + (end - start);
                mincost = min(mincost, cutcost);
            }
        }
        if (!foundcut) {
            mincost = 0;
        }
        dp.put(new Pair(start, end), mincost);
        return mincost;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    class Pair {
        int st;
        int en;

        Pair(int st, int en) {
            this.st = st;
            this.en = en;
        }

        @Override
        public int hashCode() {
            return 31 * st + en;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj instanceof Pair o) {
                return st == o.st && en == o.en;
            }
            return false;
        }
    }

}
