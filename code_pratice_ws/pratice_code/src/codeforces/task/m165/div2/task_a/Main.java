package codeforces.task.m165.div2.task_a;
import java.io.PrintWriter;
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

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());
		int[] angles = new int[tests];

		for (int i=0;i<tests;i++) {
			angles[i] = Integer.parseInt(in.nextLine());
		}


		for (int i=0;i<tests;i++) {
			if (360%(180-angles[i])==0) {
				out.println("YES");
			} else {
				out.println("NO");
			}
		}
	}


}

