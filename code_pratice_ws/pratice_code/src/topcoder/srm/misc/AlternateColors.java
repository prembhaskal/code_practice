package topcoder.srm.misc;

public class AlternateColors {

	// SRM 564 DIV 2
	// solution is possible with brute force.. trying to avoid brute force.
	public String getColor(long r, long g, long b, long k) {
		String color = "";

		long [] colArray = new long[3];
		colArray[0] = r;
		colArray[1] = g;
		colArray[2] = b;

		int minIndex = findMin(colArray);

		// find 3 repetition possible
		long minValue = colArray[minIndex];

//		if (minValue > k/3 || minValue==k/3 && k%3==0) {
		if (k<=minValue*3) {

			if (k %3 == 1)
				color = "RED";
			else if (k%3 == 2)
				color = "GREEN";
			else
				color = "BLUE";
		} else {
			// copy other than min
			long [] newArray = new long[2];
			String [] colorName = new String[2];
			for (int i=0,j=0;i<3;i++) {
				if (i != minIndex) {
					// removing the values which were present in repetition.
					newArray[j] = colArray[i] - minValue;

					if(i==0)
						colorName[j] = "RED";
					else if (i==1)
						colorName[j] =  "GREEN";
					else
						colorName[j] = "BLUE";

					j++;
				}
			}
			// since we got 3 balls, we remove thrice the value.
			k = k - minValue - minValue - minValue;
			minIndex = findMin(newArray);

			// check  for repetition of 2
			minValue = newArray[minIndex];

//			if (minValue > k/2 || minValue==k/2 && k%2==0) {
			if (k<=minValue*2) {
				if (k%2==0)
					return colorName[1];
				else
					return colorName[0];
			} else {
				int returnIndex = minIndex==0?1:0;// the color other than minimum will be chosen.
				return colorName[returnIndex];
			}
		}

		return color;
	}



	private int findMin(long [] colArray) {

		long min = Long.MAX_VALUE;
		int minIndex = -1;

		for (int i =0 ; i < colArray.length; i++) {
			if (colArray[i] <= min) {
				minIndex = i;
				min = colArray[i];
			}
		}

		return minIndex;
	}
}
