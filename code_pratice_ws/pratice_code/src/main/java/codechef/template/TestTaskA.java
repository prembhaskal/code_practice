package codechef.template;

import java.io.*;

public class TestTaskA {

	public static void main(String[] s) throws IOException {
		InputStream inputStream = TestTaskA.class.getResourceAsStream("test.txt");
		InputReader in = new InputReader(inputStream);

		PrintWriter out = new PrintWriter(System.out);

		long startTime = System.nanoTime();

		new TaskA().solve(in, out);

		long endTime = System.nanoTime();
		out.println("elapsed time " + (endTime - startTime) / 1000000 + "milli secs");

		inputStream.close();
		out.flush();
	}
}
