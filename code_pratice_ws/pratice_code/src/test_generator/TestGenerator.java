package test_generator;

import common.util.InputReader;
import java.io.PrintWriter;

public class TestGenerator {

	public void generateTest(InputReader in, PrintWriter out) {
		int size = 10000;
		out.println(size);

		out.print("1");
		for (int i = 2; i < size+1; i++) {
			out.print(" " + i);
		}
	}
}
