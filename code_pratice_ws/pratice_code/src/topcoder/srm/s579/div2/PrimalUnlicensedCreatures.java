package topcoder.srm.s579.div2;

import java.util.Arrays;

public class PrimalUnlicensedCreatures {

	public int maxWins(int initialLevel, int[] grezPower) {
		int maxWins = 0;

		Arrays.sort(grezPower);

		for (int i=0;i<grezPower.length;i++) {
			int power = grezPower[i];

			if (initialLevel > power) {
				initialLevel = initialLevel + power/2;
				maxWins++;
			} else {
				break;
			}
		}

		return maxWins;
	}
}
