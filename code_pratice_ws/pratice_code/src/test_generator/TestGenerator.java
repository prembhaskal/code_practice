package test_generator;

import common.util.InputReader;
import java.io.PrintWriter;

public class TestGenerator {

	public void generateTest(InputReader in, PrintWriter out) {
		int size = 1048576;

		out.println(size);

		int value = 1234567890;

		for (int i = 0; i < size; i++) {
			out.println(1000000000);
		}
	}
}
