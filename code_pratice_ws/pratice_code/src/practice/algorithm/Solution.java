package practice.algorithm;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

	public int getMaxMovies(int[] movie_start, int[] movie_end) {

		float a  = 4 + 1.0f;
		System.out.println(a);

		Interval[] intervals = new Interval[movie_end.length];

		for (int i = 0; i < movie_end.length; i++) {
			intervals[i] = new Interval();
			intervals[i].start = movie_start[i];
			intervals[i].end = movie_end[i];
		}

		// sort by end;
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				if (o1.end < o2.end) return -1;
				if (o1.end > o2.end) return 1;
				return 0;
			}
		});

		int maxMovies = 0;

		int previousEnd = -1;
		for (int i = 0; i < intervals.length; i++) {
			if (intervals[i].start >= previousEnd) {
				previousEnd = intervals[i].end;
				maxMovies++;
			}
		}

		return maxMovies;
	}

	class Interval {
		int start;
		int end;
	}
}
