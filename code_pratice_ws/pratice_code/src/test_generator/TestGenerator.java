package test_generator;

import common.util.InputReader;
import java.io.PrintWriter;

public class TestGenerator {

	public void generateTest(InputReader in, PrintWriter out) {
		int size = 40;
		out.println(size);

		out.print("2");
		for (int i = 1; i < size; i++) {
			out.print(" 2");
		}

		out.println();

		out.print("40");
		for (int i = 1; i < size; i++) {
			if (i%2==0)
				out.print(" 40");
			else
				out.print(" 60");
		}
	}
}
