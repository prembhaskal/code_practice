package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class P403FrogJump {

//    public static void main(String[] args) {
////        new P403FrogJump().canCross(null);
//    }
    public boolean canCross(int[] stones) {
         return frec(stones);
//        return frecnomap(stones);
        // return fcrossdp(stones);
        // return fcrossdp1(stones);
    }

    boolean fcrossdp1(int[] stones) {
        int n = stones.length;

        // dp[idx][jmp] => we can reach stone idx with jump of jmp.
        var dp = new boolean[n][n];
        dp[0][1] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // check if we can reach here from prev stone.
                int jmp = stones[i] - stones[j];
                if (jmp >= n || !dp[j][jmp]) {
                    continue;
                }
                if (i == n - 1) {
                    return true;
                }

                // mark all next stone we can reach.
                if (jmp - 1 >= 0) {
                    dp[i][jmp - 1] = true;
                }
                dp[i][jmp] = true;
                if (jmp + 1 < n) {
                    dp[i][jmp + 1] = true;
                }
            }
        }

        return false;

    }

    boolean fcrossdp(int[] stones) {
        int n = stones.length;

        // dp[idx][jmp] = true, if we can reach idx with previous jump = jmp;
        var dp = new boolean[n][n];
        dp[0][0] = true; // first stone is base case;

        var stonepos = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            stonepos.put(stones[i], i);
        }

        for (int idx = 1; idx < n; idx++) {
            // iterate through all jumps and check if previdx was reachable too.
            // this is slower than recursive, which finds next possible jump from current idx
            for (int jmp = 1; jmp < n; jmp++) {
                int prev = stones[idx] - jmp;
                if (!stonepos.containsKey(prev)) {
                    continue;
                }
                int previdx = stonepos.get(prev);
                if (jmp > 0) {
                    dp[idx][jmp] |= dp[previdx][jmp - 1];
                }

                dp[idx][jmp] |= dp[previdx][jmp];


                if ((jmp + 1) < n) {
                    dp[idx][jmp] |= dp[previdx][jmp + 1];
                }
                // System.out.printf("idx: %d, pos: %d, prev: %d, jmp: %d, ans: %b\n", idx, stones[idx], prev, jmp, dp[idx][jmp]);
            }
        }

        for (int jmp = 0; jmp < n; jmp++) {
            if (dp[n - 1][jmp]) {
                return true;
            }
        }

        return false;
    }

    boolean frec(int[] stones) {
        var distMap = new HashMap<Integer, Integer>();
        int n = stones.length;
        for (int i = 0; i < n; i++) {
            distMap.put(stones[i], i);
        }
        // dp[idx][jmp] = true, if we can reach idx with previous jump = jmp;
        int[][] dp = new int[n][n];

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                dp[i][j] = -1;
//            }
//        }

        return fcross(stones, distMap, 0, 0, dp);
    }

    boolean frecnomap(int[] stones) {
        int n = stones.length;
        // dp[idx][jmp] = true, if we can reach idx with previous jump = jmp;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return fcrossnomap(stones, 0, 0, dp);
    }

    // recursive
    boolean fcross(int[] stones, Map<Integer, Integer> distMap, int curr, int prevjump, int[][] dp) {
        // base case
        if (curr == stones.length - 1) {
            return true;
        }

        if (dp[curr][prevjump] != -0) {
            return dp[curr][prevjump] == 1;
        }

        int currdist = stones[curr];
        Integer next;
        int nextjump = 0;

        // k - 1
        boolean ans = false;
        if (prevjump > 1) {
            next = distMap.get(currdist + prevjump - 1);
            if (next != null) {
                ans = fcross(stones, distMap, next, prevjump - 1, dp);
            }
        }

        if (!ans && prevjump > 0) {
            next = distMap.get(currdist + prevjump);
            if (next != null) {
                ans = fcross(stones, distMap, next, prevjump, dp);
            }
        }

        next = distMap.get(currdist + prevjump + 1);
        if (!ans && next != null) {
            ans = fcross(stones, distMap, next, prevjump + 1, dp);
        }

        if (ans) {
            dp[curr][prevjump] = 1;
        } else {
            dp[curr][prevjump] = 2;
        }
        return ans;
    }

    // recursive
    boolean fcrossnomap(int[] stones, int curr, int prevjump, int[][] dp) {
        // base case
        if (curr == stones.length - 1) {
            return true;
        }

        if (dp[curr][prevjump] != -1) {
            return dp[curr][prevjump] == 1;
        }

        boolean ans = false;
        for (int next = curr + 1; next < stones.length; next++) {
            if (stones[next] == stones[curr] + prevjump - 1) {
                ans = fcrossnomap(stones, next, prevjump - 1, dp);
                if (ans) {
                    break;
                }
            }

            if (stones[next] == stones[curr] + prevjump) {
                ans = fcrossnomap(stones, next, prevjump, dp);
                if (ans) {
                    break;
                }
            }

            if (stones[next] == stones[curr] + prevjump + 1) {
                ans = fcrossnomap(stones, next, prevjump + 1, dp);
                if (ans) {
                    break;
                }
            }
        }
        dp[curr][prevjump] = (ans ? 1 : 0);
        return ans;
    }
}
