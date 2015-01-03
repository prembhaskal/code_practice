package codechef.archives.feb13.buy1get1;

import java.io.PrintWriter;
import java.util.Scanner;

public class TestTaskA {

	public static void main(String[] s) {
		Scanner in = new Scanner(TestTaskA.class.getResourceAsStream("test.txt"));
		PrintWriter out = new PrintWriter(System.out);

		new TaskA().solve(in, out);

		in.close();
		out.close();
	}
}
