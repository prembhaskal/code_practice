package codechef.practice.product_divisors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestTaskA {

	public static void main(String[] s) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(TestTaskA.class.getResourceAsStream("test.txt")));
		PrintWriter out = new PrintWriter(System.out);

		long startTime = System.nanoTime();

		new TaskA().solve(in, out);

		long endTime = System.nanoTime();
		out.println("elapsed time " + (endTime - startTime) / 1000000 + "milli secs");

		in.close();
		out.flush();
	}
}
