package codeforces.task.m164div2.task_c;

import java.io.PrintWriter;
import java.util.Scanner;

public class TestTaskC {

	public static void main(String[] s) {
		Scanner in = new Scanner(TestTaskC.class.getResourceAsStream("testC.txt"));
		PrintWriter out = new PrintWriter(System.out);

		new TaskC().solve(in, out);

		in.close();
		out.close();
	}
}
