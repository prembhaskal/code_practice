package codechef.archives.march13.fire_escape_routes;

import java.io.PrintWriter;
import java.util.Scanner;

public class TestTaskA {

	public static void main(String[] s) {
		Scanner in = new Scanner(TestTaskA.class.getResourceAsStream("test.txt"));
//		Scanner in = new Scanner(TestTaskB.class.getResourceAsStream("test_fire_exit.txt"));

		PrintWriter out = new PrintWriter(System.out);

		try {
			new TaskA().solve(in, out);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("do nothing");
		}
		in.close();
		out.close();
	}
}
