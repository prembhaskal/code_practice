package topcoder.srm.s568.div2;

public class BallsSeparating {

	public int minOperations(int[] red, int[] green, int[] blue) {
		int count = 0;

		int balls = red.length;

		if (balls < 3)
			return -1;
		// program reduces to essentially below given problem.
		// 3 arrays , select at least one value from each array.
		// A -- a1, a2, a3
		// B -- b1, b2, b3
		// C -- c1, c2, c3
		// and select from only one of 3 for each index. find the minimum possible value
		int minCount = Integer.MAX_VALUE;

		for (int i=0;i<balls;i++) {// for chosing red ball
			for(int j=0;j<balls;j++) // choosing green ball
				if(i!=j) {
					for (int k=0;k<balls;k++)// blue ball
						if(i!=k && j!=k) {
							// now we have 3 places chosen, now try to find the sum here
							int current = 0;
							for (int m=0;m<balls;m++) {
								if (m==i)
									current += green[m] + blue[m];
								else if (m==j)
									current += red[m] + blue[m];
								else if (m==k)
									current += red[m] + green[m];
								else
									current += sumOfMin(red[m],green[m], blue[m]);
							}

							minCount = Math.min(minCount, current);
						}


				}
		}

		return minCount;
	}

	private int max(int r, int g, int b) {
		if (r>=g && r>=b)
			return 0;
		if (g>=r && g>=b)
			return 1;
		return 2;
	}


	private int sumOfMin(int r, int g, int b) {
		int max = Math.max(r,Math.max(g,b));
		return r+g+b-max;
	}
}
