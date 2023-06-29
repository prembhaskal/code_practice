package coursera.algo1.week6;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TwoSumNaive {

	Set<Integer> integerSet = new HashSet<Integer>();

	int lowT;
	int highT;

	private int targetValues;

	public void solve(Scanner in, PrintWriter out, int total, int lowT, int highT) {
		this.lowT = lowT;
		this.highT = highT;
		readFile(in, total);
		findTargetValues();
		out.println("target values are " + targetValues);
	}

	private void findTargetValues() {
		targetValues = 0;
		for (int sum=lowT;sum<=highT;sum++) {
			for (int num: integerSet) {
				if (num <= sum) {
					int find = sum - num;
					if (find != num && integerSet.contains(find)) {
						targetValues++;
						break;
					}
				}
			}
		}
	}


	private void readFile(Scanner in, int total) {
		int i=0;
		while (in.hasNext()) {
			int num = Integer.parseInt(in.nextLine());
			if (num <= highT)
				integerSet.add(num);
		}
	}
}
