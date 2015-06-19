package codechef.archives.practice.cooling_pies;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskA solution = new TaskA();
		solution.solve(in,writer);

		writer.close();
		in.close();
	}

}

class TaskA {

	int noOfPies;
	int[] weightPies;
	int[] weightRacks;

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.next());

		for (int i=0;i<tests;i++) {
			noOfPies = in.nextInt();

			weightPies = new int[noOfPies];
			weightRacks = new int[noOfPies];

			for (int j=0;j<noOfPies;j++) {
				weightPies[j] = in.nextInt();
			}

			for (int j=0;j<noOfPies;j++) {
				weightRacks[j] = in.nextInt();
			}

			int minPie = getMinimumPiesForCooling();

			out.println(minPie);

		}

	}

	private int getMinimumPiesForCooling() {
		int minPies = 0;

		Arrays.sort(weightPies);
		Arrays.sort(weightRacks);

		for (int i=0, j=0;i<weightPies.length && j < weightRacks.length;) {
			if (weightPies[i] <= weightRacks[j]) { // see if we can put the pie on this rack.
				minPies++;
				i++;
				j++;
			} else {
				j++; // try next rack if we cannot place this pie.
			}
		}

		return minPies;

	}



}

