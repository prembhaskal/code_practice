package test_generator;

import common.util.InputReader;
import java.io.PrintWriter;

public class TestGenerator {

	public void generateTest(InputReader in, PrintWriter out) {
		int size = 16;
		out.println(size);

		for (int i = 0; i < size; i++) {
			out.println(30 + " " + (i+1) + " " + (i+1));
		}
	}
}
