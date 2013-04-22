package projecteuler.set4;

import java.util.*;

public class P31CoinSums {

	int [] coinAvail;
	long[][] coinsForAmount;

	public long getWays(int amount) {
		long ways;
		initCoins();
		coinsForAmount = new long[amount+1][coinAvail.length];
//		for (int i=0;i<coinsForAmount.length;i++)
//			Arrays.fill(coinsForAmount[i], -1);
//		ways = getWaysUsingWithAndWithout(amount, coinAvail.length-1);

		ways = getWaysDPIterative(amount);
		return ways;
	}

	// count = count (num, with no coin x) + count (num, with at least one coin x)
	// count(num, at least one coin x) = count(num - s[m], )
	// this requires that the coin are in sorted order and we start with the biggest coin first.
	// this need array to be filled with -1 initially
	private long getWaysUsingWithAndWithout(int amount, int coinIndex) {
		if (amount==0) {
			return 1;
		}

		if (amount < 0 || coinIndex < 0) {
			return 0;
		}

		if (coinsForAmount[amount][coinIndex] < 0) {
			coinsForAmount[amount][coinIndex] = getWaysUsingWithAndWithout(amount, coinIndex-1) // get ways without using coin[coinIndex]
												+
												// get ways using one of the coin[coinIndex];
												getWaysUsingWithAndWithout(amount-coinAvail[coinIndex], coinIndex);
		}

		return coinsForAmount[amount][coinIndex];
	}

	// solution with DP and no recursion
	// start filling for 0 to above, as it happens in recursion (the least is calculate before bigger numbers are set)
	// this avoid the stack overflow for bigger nos.
	private long getWaysDPIterative(int amount) {
		if (amount ==0)
			return 1;

		Arrays.fill(coinsForAmount[0],1);

		for (int amt=1;amt<=amount;amt++) {
			for (int coin = 0;coin<coinAvail.length;coin++) {
				if (coin > 0)
					coinsForAmount[amt][coin] = coinsForAmount[amt][coin-1];
				if (amt >= coinAvail[coin])
					coinsForAmount[amt][coin] += coinsForAmount[amt - coinAvail[coin]][coin];
			}
		}

		return coinsForAmount[amount][coinAvail.length-1];
	}

	private void initCoins() {
		coinAvail = new int[8];

		coinAvail[0] = 1;
		coinAvail[1] = 2;
		coinAvail[2] = 5;
		coinAvail[3] = 10;
		coinAvail[4] = 20;
		coinAvail[5] = 50;
		coinAvail[6] = 100;
		coinAvail[7] = 200;
	}
}
