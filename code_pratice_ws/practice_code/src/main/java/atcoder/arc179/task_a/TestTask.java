package atcoder.arc179.task_a;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;

public class TestTask {

	public static void main(String[] s) throws IOException {
		InputStream inputStream = TestTask.class.getClassLoader().getResourceAsStream("test.txt");
		URL resource = TestTask.class.getClassLoader().getResource("test.txt");
		System.out.println(resource.getPath());
		InputReader in = new InputReader(inputStream);

		PrintWriter out = new PrintWriter(System.out);

		long startTime = System.nanoTime();

		new Task().solve(in, out);

		long endTime = System.nanoTime();
		out.println("elapsed time " + (endTime - startTime) / 1000000 + "milli secs");

		inputStream.close();
		out.flush();
	}
}
