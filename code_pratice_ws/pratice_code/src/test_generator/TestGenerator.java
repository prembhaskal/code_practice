package test_generator;

import common.util.InputReader;
import java.io.PrintWriter;

public class TestGenerator {

	public void generateTest(InputReader in, PrintWriter out) {
		int size = 200000;
		int queries = 30;
		out.println(size + " " + queries);


		for (int i = 0; i < size; i++) {
			out.print((i%100 + 1) + " ");
		}

		out.println();

		for (int i = 69; i <= 99; i++) {
			out.print(i + " ");
		}
	}
}
