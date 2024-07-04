package leetcode.dp;

import java.util.Arrays;

public class P322CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] mem = new int[amount + 1];
        Arrays.fill(mem, -1);
        int ans = f(coins, amount, mem);
        if (ans == maxval) {
            return -1;
        }
        return ans;
    }

    int maxval = 1 << 31 - 1;

    int f(int[] coins, int amount, int[] mem) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return maxval;
        }
        if (mem[amount] != -1) {
            return mem[amount];
        }
        int minways = maxval;
        for (int i = 0; i < coins.length; i++) {
            // use coins[i]
            int choose = f(coins, amount - coins[i], mem);
            if (choose != maxval) {
                minways = Math.min(minways, choose + 1);
            }
        }
        mem[amount] = minways;
        return mem[amount];
    }
}
