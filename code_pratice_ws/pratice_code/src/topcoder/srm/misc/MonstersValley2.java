package topcoder.srm.misc;

//SRM 565 DIV 2 ... 500 point problem
public class MonstersValley2{

	public int minimumPrice(int[] dread, int[] price) {
		int dreadOwn = 0;
		int pricepaid = 0;

		return getBribePaid(dread, price, 0, 0, 0);
	}


	private int getBribePaid(int[] dread, int [] price, int index, long totalDread, int bribePaid) {

		if (index == dread.length)
			return bribePaid;

		if (dread[index] > totalDread) {
			return getBribePaid(dread, price, index+1, totalDread+dread[index], bribePaid+price[index]);
		} else {
			return Math.min(getBribePaid(dread, price, index+1,totalDread,bribePaid),
							getBribePaid(dread, price, index+1, totalDread+dread[index], bribePaid+price[index]));
		}


	}


}
