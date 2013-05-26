package topcoder.srm.s580.div2;

import java.util.Arrays;

public class ShoutterDiv2 {

	// this is a linear solution
	public int count(int[] s, int[] t) {
		int pairs = 0;

		Arrays.sort(s);
		Arrays.sort(t);

		int present = 0;

		int sidx = 0;
		int tidx = 0;

		// 100 because it is the limit of times.
		for (int i=0;i<100;i++) {
			// for each instant, check no. of new rabbits and increase pairs accordingly.
			while (sidx<s.length && s[sidx]==i) {
				sidx++;
				pairs += present;
				present++;
			}

			// remove rabbits after accounting for pairs. (following question's condition)
			while (tidx<t.length && t[tidx] == i) {
				present--;
				tidx++;
			}

			if (tidx == t.length)
				break;

		}
		return pairs;
	}

	// brute force solution
	public int countBruteForce(int[] s, int[] t){
		int pairs = 0;

		// for each rabbit, check how many of rabbit it will be present at his time.
		for (int i=0;i<s.length;i++) {
			for (int j=i+1;j<s.length;j++) {
				if (s[i]<=s[j] && s[j] <= t[i] || s[j]<=s[i] && s[i] <= t[j]) {
					pairs++;
				}
			}
		}

		return pairs;
	}
}
