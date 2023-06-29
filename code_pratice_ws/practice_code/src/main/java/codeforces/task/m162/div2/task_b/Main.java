package codeforces.task.m162.div2.task_b;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskB solution = new TaskB();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}
}

class TaskB {

	public void solve(Scanner in, PrintWriter out) {
		int noOfTrees = Integer.parseInt(in.nextLine());

		int[] trees = new int[noOfTrees];

		for (int i=0;i<noOfTrees;i++)
			trees[i] = Integer.parseInt(in.nextLine());

		int time = trees[0];
		int previousHt = trees[0];
		int moveTime = 0;
		for (int i=0;i<trees.length;i++) {
			time = time + (trees[i]-previousHt) + 1; // time to climb and eat

			if (i==trees.length-1)
				break;             //stop if last tree

			// come down if next tree is bigger
			if (trees[i] > trees[i+1]) {
				moveTime = trees[i]-trees[i+1];
				time += moveTime;
			} else
				moveTime = 0;

			previousHt = trees[i] - moveTime;

			// time for jumping
			time++;


		}

		out.println(time);
	}

}
