package coursera.algo2.week1;

import common.util.InputReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question2 {

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

		List<JobOptimal> jobList = new ArrayList<>();
		for (int i = 0; i < numberOfJobs; i++) {
			JobOptimal job = new JobOptimal(weights[i], lengths[i]);
			jobList.add(job);
		}

		long weightedSum = getWeightedSum(jobList);

		out.println(weightedSum);

	}

	private long getWeightedSum(List<JobOptimal> jobList) {
		Collections.sort(jobList);

		long weightedSum = 0;
		int cumulativeLength = 0;
		for (int i=numberOfJobs-1; i >= 0; i--) {
			JobOptimal job = jobList.get(i);
			cumulativeLength += job.length;
			weightedSum =  weightedSum + (((long)cumulativeLength)*job.weight);
		}

		return weightedSum;
	}
}

class JobOptimal implements Comparable<JobOptimal>{

	int weight;
	int length;

	public JobOptimal(int weight, int length) {
		this.weight = weight;
		this.length = length;
	}

	// lesser if W/L < Wx/Lx ... where Wx is of the object being compared with.
	// or W.Lx < Wx.L
	@Override
	public int compareTo(JobOptimal o) {
		int prod1 = this.weight*o.length;
		int prod2 = this.length*o.weight;

		if (prod1 <= prod2) {
			return -1;
		} else {
			return 1;
		}
	}
}
