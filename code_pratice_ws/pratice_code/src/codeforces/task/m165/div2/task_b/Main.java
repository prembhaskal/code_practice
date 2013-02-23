package codeforces.task.m165.div2.task_b;

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
		int threadNo = Integer.parseInt(in.nextLine());
		int[] threads = new int[threadNo];

		for (int i=0;i<threadNo;i++) {
			threads[i] = in.nextInt();
		}

		int count = 0;

		for (int i=threadNo-1;i>0;i--) {
			if (threads[i] < threads[i-1]) {
				out.println(i);
				return;
			}
		}
		out.println(count);
	}

}
