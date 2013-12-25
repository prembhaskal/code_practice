package topcoder.srm.archive.s464div2;

// SRM 464 DIV2 level1
public class ColorfulBoxesAndBalls {


	public static void main(String[] s) {
		ColorfulBoxesAndBalls colorfulBoxesAndBalls = new ColorfulBoxesAndBalls();

		// >>1400
		System.out.println(colorfulBoxesAndBalls.getMaximum(2,3,100,400,200));
	}

	// system test passed
	public int getMaximum(int numRed, int numBlue, int onlyRed, int onlyBlue, int bothColors) {
		int max = Integer.MIN_VALUE;

		int min = Math.min(numRed, numBlue);

		int val;

		// start by moving the balls with min count,
		// if the ball is moved, it means it goes into opposite color box, (also the opposite color ball
		// goes into the ball moved.
		for (int i=0;i<=min;i++) {
			val = (numRed-i)*onlyRed + (numBlue-i)*onlyBlue + 2*i*bothColors;
			max = Math.max(max,val);
		}

		return max;
	}

	// TODO try to solve without loop, just formula
}
