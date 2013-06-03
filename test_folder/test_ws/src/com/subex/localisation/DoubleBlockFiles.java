package com.subex.localisation;

import java.io.*;

public class DoubleBlockFiles {

	private File jsFolder = new File("D:\\project\\RA\\mywork\\WebClient-Localization\\localisation\\JS_files_595");

	private final String macthVarLine = "var .*_en_GB.*";


	public static void main(String[] s) {
		DoubleBlockFiles checker = new DoubleBlockFiles();
		try {
			checker.findFilesWithDoubleBlock();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("some error");
		}
	}

	public void findFilesWithDoubleBlock() throws Exception {
		for (File jsFile : jsFolder.listFiles()) {

			if (jsFile.getName().equals(".svn"))
				continue;

			if (hasDoubleVar(jsFile))
				System.out.println("File --> " + jsFile.getName());
		}
	}

	private boolean hasDoubleVar(File file) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		try {
			int varCount = 0;
			String line;
			while ((line = reader.readLine())!=null) {
				if (isVarLine(line))
					varCount++;

				if (varCount==2)
					return true;
			}

			return false;

		} finally {
			reader.close();
		}
	}

	private boolean isVarLine(String line) {
		if (line.matches(macthVarLine))
			return true;
		return false;
	}
}
