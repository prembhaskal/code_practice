package codeforces.task.m162.div2.task_d;

import java.io.PrintWriter;
import java.util.Scanner;

public class TestTaskD {

	public static void main(String[] s) {
		Scanner in = new Scanner(TestTaskD.class.getResourceAsStream("testD.txt"));
		PrintWriter out = new PrintWriter(System.out);

		new TaskD().solve(in, out);

		in.close();
		out.close();
	}
}
