package leetcode.array;

public class P121BuySellStock {
    public int maxProfit(int[] prices) {

        // for every day price = num, check what is the max profit we can achieve based on it
        // mxprofit = Max(mxprofit, num - smallest_till_now)

        // also for next rounds, update smallest_till_now
        // smallest_till_now = min(smallest_till_now, num)

        int smallest = prices[0];
        int mxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if (price > smallest) {
                mxprofit = max(mxprofit, price - smallest);
            } else {
                smallest = price;
            }
        }

        return mxprofit;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }
}
