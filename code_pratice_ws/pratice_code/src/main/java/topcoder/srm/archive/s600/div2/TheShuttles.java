package topcoder.srm.archive.s600.div2;

public class TheShuttles {

	public int getLeastCost(int[] cnt, int baseCost, int seatCost) {

		int maxSeats = -1;
		for (int i = 0; i < cnt.length; i++) {
			maxSeats = Math.max(maxSeats, cnt[i]);
		}

		// Brute force :)
		int minCost = Integer.MAX_VALUE;

		for (int seats = maxSeats; seats > 0 ; seats--) {
			int costPerBus = baseCost + seatCost * seats;

			int totalBus = 0;
			for (int i = 0; i < cnt.length; i++) {
				totalBus += ((cnt[i] + seats - 1)/ seats);
			}

			int totalCost = costPerBus * totalBus;

			minCost = Math.min(minCost, totalCost);
		}

		return minCost;
	}


}
