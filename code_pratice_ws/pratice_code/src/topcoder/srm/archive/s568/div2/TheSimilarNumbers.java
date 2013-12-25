package topcoder.srm.archive.s568.div2;

public class TheSimilarNumbers {

	public int find(int lower, int upper) {
		int count = 1;

		lower = lower*10 + 1;
		while (lower <= upper) {
			lower = lower * 10 + 1;
			count++;
		}

		return count;
	}
}
