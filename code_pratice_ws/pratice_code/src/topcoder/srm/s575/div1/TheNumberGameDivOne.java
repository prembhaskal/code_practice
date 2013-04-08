package topcoder.srm.s575.div1;

public class TheNumberGameDivOne {

	public String find(long num) {
		boolean chance;

		chance = isWinningUsingPattern(num);

		if (chance) {
			return "John";
		} else {
			return "Brus";
		}
	}


	// if num is odd, it is losing position
	// if number is even, and odd power of 2, it is losing position.
	// else it is winning position.
	private boolean isWinningUsingPattern(long num) {
		if (num%2==1)
			return false;

		int exp = 0;
		while (num%2==0) {
			num /= 2;
			exp++;
		}

		if (num > 1) { // even and not power of 2, so wing
			return true;
		} else if (exp%2==0) { // even power of 2
			return true;
		} else { // odd power of 2
			return false;
		}

	}
}
