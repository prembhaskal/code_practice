package codechef.archives.y2013.april13.king_con;

import java.io.PrintWriter;
import java.util.Scanner;

public class TestTaskA {

	public static void main(String[] s) {
		Scanner in = new Scanner(TestTaskA.class.getResourceAsStream("test.txt"));
		PrintWriter out = new PrintWriter(System.out);

		long startTime = System.nanoTime();

		new TaskA().solve(in, out);

		long endTime = System.nanoTime();
		out.println("elapsed time " + (endTime-startTime)/1000000 + "milli secs");

		in.close();
		out.flush();
	}
}
