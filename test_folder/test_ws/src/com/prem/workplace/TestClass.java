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
//		testClass.generateData();
	}

	private void generateData() {
		File file = new File("testData.txt");
		PrintWriter out = null;
		try {
			out = new PrintWriter(file);

			String line;
			for (int i=1;i<=1000;i++) {
				line = "";
				line += i + "\t";
				line += i + "\t";
				line += "string_no_" + i + "\t";
				line += "2013-02-20 20:11:00" + "\t";
				line += i + ".01";

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
