package codeforces.task.m161div2.task_b;

import java.io.PrintWriter;
import java.util.Arrays;
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

		int n = in.nextInt();
		int k = in.nextInt();

		int[] points = new int[n];

		for (int i=0;i<n;i++)
			points[i] = in.nextInt();

		Arrays.sort(points);

		int point_i;
		int point_j;

		if (k > n) {
			out.println(-1);
		} else {
			point_i = points[n-k];
			point_j = point_i;
			out.print(point_i);
			out.print(" ");
			out.print(point_j);
		}


	}

}
