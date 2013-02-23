package codeforces.task.m164div2.task_b;

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
		int buttons = Integer.parseInt(in.nextLine());

		if (buttons==1) {
			out.println(1);
			return;
		}
		int presses = 0;

		for (int i=1;i<buttons;i++) {
			presses = presses + (i * (buttons-i));
		}

		presses = presses + buttons;

		out.println(presses);
	}

}
