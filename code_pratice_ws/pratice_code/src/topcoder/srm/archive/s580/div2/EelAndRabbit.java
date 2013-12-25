package topcoder.srm.archive.s580.div2;

public class EelAndRabbit {


	public int getmax(int[] l, int[] t) {
		int len = t.length;

		// get the start for each of the ees
		int[] start = new int[len];

		for (int i=0;i<len;i++) {
			start[i] = t[i];
		}

		// get the end of each
		int[] end = new int[len];

		for (int i=0;i<len;i++) {
			end[i] = l[i] + t[i];
		}

		int max = 1;

		// normal brute force solution, for each t1, and t2 combination, get the counts.
		// it would work for each start time possible t1, t2 (but it is not 0 to max)
		/// this works as speed are 1, time will always be integer, hence we will not miss any eel
		// if time had been decimal, we would have certainly missed.
		// this can be seen by drawing diagram.

		// BASICALLY the above logic was copied from TOURIST'S CODE.

		// TODO solve using interval overlap approach
		for (int i=0;i<len;i++) {
			for (int j=i+1;j<len;j++) {
				int t1 = start[i];
				int t2 = start[j];

				int cnt = 0;

				// check for each of the eel, if it present in either t1 or t2
				for (int k=0;k<len;k++) {
					if (t1 >=start[k] && t1<=end[k] || t2 >=start[k] && t2 <=end[k])
						cnt++;
				}

				max = Math.max(max, cnt);
			}
		}
		return max;
	}

}
