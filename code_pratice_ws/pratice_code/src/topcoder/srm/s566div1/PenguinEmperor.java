package topcoder.srm.s566div1;

public class PenguinEmperor {

	public int countJourneys(int numCities, long daysPassed) {
		int count = 0;

//		System.out.println("next city " + cityAfterShift(3,7,7));

//		System.out.println("next city " + cityAfterShift(3,-7,7));

		return count;
	}


	// TODO how to know that two ways are different


	private int cityAfterShift(int cityIndex, long noOfMoves, long totalCity) {

		int nextCity = cityIndex + (int)(noOfMoves%totalCity);

		if (nextCity<0)
			nextCity += totalCity;

		nextCity = (int) (nextCity % totalCity);
		return nextCity;
	}
}


