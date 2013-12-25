package codechef.archives.cieldist;

import java.io.PrintWriter;
import java.util.Scanner;

public class TestMain {
	public static void main(String[] s) {
		Scanner in = new Scanner(TestMain.class.getResourceAsStream("test.txt"));
		PrintWriter out = new PrintWriter(System.out);

		new Main().solve(in, out);

		in.close();
		out.close();
	}
}
