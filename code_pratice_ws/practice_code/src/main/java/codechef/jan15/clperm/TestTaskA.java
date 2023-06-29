package codechef.jan15.clperm;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

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
