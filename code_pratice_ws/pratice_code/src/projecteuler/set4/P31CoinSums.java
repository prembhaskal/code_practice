package projecteuler.set4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P31CoinSums {

	int [] coinAvail;
	int ways;
	Map<Integer, Integer> amountWays = new HashMap<Integer, Integer>();
	Set<String> combinationSet = new HashSet<String>();

	public int getWays(int amount) {

		initCoins();

		ways = getWaysForAmount(amount);
		return ways;
	}

	private int getWaysForAmount(int amount) {
		int waysForAmt = 0;

		return waysForAmt;
	}

	private String getCombinationToStore(int i, int j) {
		String str = ",";
		if (i<=j) {
			str = i + str + j;
		} else {
			str = j + str + i;
		}

		return str;
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
