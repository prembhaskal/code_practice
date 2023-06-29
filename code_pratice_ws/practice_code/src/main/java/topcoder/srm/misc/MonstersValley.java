package topcoder.srm.misc;

import java.util.Arrays;

public class MonstersValley {
	// should execute within 2 secs
	public int minimumPrice(long[] dread, int[] price) {
		// for each arrival of monster we create a lookup of
		// oldPrice and newPrice
		// something like this.
		// monster1 dread = x, price = y
		// spent	oldDread	newDread
		// 0		 --- 		 ---
		// 1
		// 2
		// 3
		// at each step, update the price vs table table and update for all conditions,
		// one is EITHER not bribing the monster  OR bribing the monster.
		// at each check, we try to get maximum dread with minimum price.
		// RECURSION is avoided to get solution quickly.
		int n = dread.length;
		// all the combination of price. 0 to max (i.e. price*2)
		long[] oldPriceDreads = new long[2*n + 1];
		Arrays.fill(oldPriceDreads,-1);
		oldPriceDreads[0] = 0;

		for(int monster = 0; monster < n; monster++) {
			long [] newPriceDreads = new long[2*n + 1];
			Arrays.fill(newPriceDreads, -1);

			for ( int pricePaid = 0; pricePaid <= 2*n; pricePaid++) {
				if (oldPriceDreads[pricePaid] >=0) {
					// update assuming that we bribe the monster
					newPriceDreads[pricePaid + price[monster]] = Math.max(newPriceDreads[pricePaid],oldPriceDreads[pricePaid] + dread[monster]);

					// if we see that the dread at oldPrice is more that current monster, we try to put the maximum dread possible
					// at this price. see example of dread - (8,5,2) and price - (1,1,2) for this.
					if (oldPriceDreads[pricePaid] >= dread[monster]) {
						newPriceDreads[pricePaid] = Math.max(oldPriceDreads[pricePaid], newPriceDreads[pricePaid]);
					}
				}
			}

			oldPriceDreads = newPriceDreads;
		}

		for(int i=0;i<=2*n;i++) {
			if (oldPriceDreads[i] >= 0)
				return i;
		}

		throw new RuntimeException();
	}


}
