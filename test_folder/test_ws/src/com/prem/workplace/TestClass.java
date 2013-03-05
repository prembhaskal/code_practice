package com.prem.workplace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * User: premkumar.bhaskal
 * Date: 2/22/13
 * Time: 11:45 AM
 */
public class TestClass {


	public static void main(String[] s) {
		TestClass testClass = new TestClass();
		testClass.generateData();
	}

	private void generateData() {
		File file = new File("testData.txt");
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);

			String line;
			for (int i=1;i<=100000;i++) {
				line = "";
				line += i + ",";
				line += "name_" + i;
				out.println(line);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}


	}
}
