package topcoder.srm.archive.s572.div2;

public class FoxAndGame {

	public int countStars(String[] result) {
		int count = 0;

		int length = result.length;

		for (String str : result) {
			if (str.equals("o--"))
				count = count + 1;
			else if (str.equals("oo-"))
				count += 2;
			else if (str.equals("ooo"))
				count += 3;
		}

		return count;
	}
}
