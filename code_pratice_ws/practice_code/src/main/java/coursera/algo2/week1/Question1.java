package coursera.algo2.week1;

import common.util.InputReader;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question1 {

	int[] weights;
	int[] lengths;
	int numberOfJobs;

	public void solve(InputReader in, PrintWriter out) {
		numberOfJobs = in.nextInt();
		weights = new int[numberOfJobs];
		lengths = new int[numberOfJobs];

		for (int i = 0; i < numberOfJobs; i++) {
			weights[i] = in.nextInt();
			lengths[i] = in.nextInt();
		}

		List<Job> jobList = new ArrayList<>();
		for (int i = 0; i < numberOfJobs; i++) {
			Job job = new Job(weights[i], lengths[i]);
			jobList.add(job);
		}

		long weightedSum = getWeightedSum(jobList);

		out.println(weightedSum);

	}

	private long getWeightedSum(List<Job> jobList) {
		Collections.sort(jobList);

		long weightedSum = 0;
		int cumulativeLength = 0;
		for (int i=numberOfJobs-1; i >= 0; i--) {
			Job job = jobList.get(i);
			cumulativeLength += job.length;
			weightedSum =  weightedSum + (((long)cumulativeLength)*job.weight);
		}

		return weightedSum;
	}
}

class Job implements Comparable<Job>{

	int weight;
	int length;

	public Job(int weight, int length) {
		this.weight = weight;
		this.length = length;
	}

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
