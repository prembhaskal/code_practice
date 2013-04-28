package codechef.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TestTaskA {

	public static void main(String[] s) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(TestTaskA.class.getResourceAsStream("test.txt")));
		InputReader in = new InputReader(reader);

		PrintWriter out = new PrintWriter(System.out);

		long startTime = System.nanoTime();

		new TaskA().solve(in, out);

		long endTime = System.nanoTime();
		out.println("elapsed time " + (endTime - startTime) / 1000000 + "milli secs");

		reader.close();
		out.flush();
	}
}
