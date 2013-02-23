package codeforces.task;

import java.io.PrintWriter;
import java.util.Scanner;

public class TestWinner {

	public static void main(String[] s) {
		Scanner in = new Scanner(TestWinner.class.getResourceAsStream("test.txt"));
		PrintWriter out = new PrintWriter(System.out);

		new TestSolution().testSolution(in, out);

		in.close();
		out.close();
	}
}
