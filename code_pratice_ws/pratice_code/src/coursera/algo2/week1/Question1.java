package coursera.algo2.week1;

import common.util.InputReader;

import java.io.PrintWriter;

public class Question1 {

	public void solve(InputReader in, PrintWriter out) {

	}
}

class Job implements Comparable<Job>{

	int weight;
	int length;

	@Override
	public int compareTo(Job o) {
		int otherDiff = o.weight - o.length;
		int myDiff = this.weight - this.length;

		if (myDiff < otherDiff) {
			return -1;
		} else if (myDiff > otherDiff) {
			return 1;
		} else {
			if (this.weight < o.weight)
				return -1;
			else if (this.weight > o.weight)
				return 1;
			else
				return 0;
		}
	}
}
