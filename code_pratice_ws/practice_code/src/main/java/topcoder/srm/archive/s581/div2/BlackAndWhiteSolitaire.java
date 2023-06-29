package topcoder.srm.archive.s581.div2;

// passed system test
public class BlackAndWhiteSolitaire {

	public int minimumTurns(String cardFront) {
		char[] cards = cardFront.toCharArray();

		// W on even place and B on odd place
		int min1 = 0;
		for (int i = 0; i < cards.length; i++) {
			if (i%2==0) {
				if (cards[i]!='W')
					min1++;
			} else {
				if (cards[i]!='B')
					min1++;
			}
		}

		// B on even place and W on odd place
		int min2 = 0;
		for (int i = 0; i < cards.length; i++) {
			if (i%2==0) {
				if (cards[i]!='B')
					min2++;
			} else {
				if (cards[i]!='W')
					min2++;
			}
		}

		return Math.min(min1, min2);
	}
}
