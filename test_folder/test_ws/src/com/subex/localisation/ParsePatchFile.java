package com.subex.localisation;

import java.io.*;

/**
 * User: premkumar.bhaskal
 * Date: 4/29/13
 * Time: 6:21 PM
 */

// link to test javascript http://www.regular-expressions.info/javascriptexample.html
public class ParsePatchFile {

	private String diffJSFile;
	private String newLineForTranslation;

	private String parseFilePath = "D:\\project\\RA\\mywork\\WebClient-Localization\\QFE_595_localisation\\js_files_diff.patch";

	public static void main(String[] args) {
		try {
			ParsePatchFile parsePatchFile = new ParsePatchFile();
			parsePatchFile.getPatchFileAndStartParsing();
		} catch (Exception e) {
			System.out.println("Error: Parsing Failed");
			e.printStackTrace();
		}
	}

	private void getPatchFileAndStartParsing() throws Exception {
		File parseFile = new File(parseFilePath);
		parsePatchFile(parseFile);
	}

	private void parsePatchFile(File parseFile) throws Exception {
		BufferedReader reader = null;
		try {
			 reader = new BufferedReader(new InputStreamReader(new FileInputStream(parseFile)));

			boolean eof = false;

			while (!eof) {
				String line = reader.readLine();
				if (line==null) {
					eof = true;
					break;
				}

				// check if start of diff for new js file
				if (isStartOfDiffForNewFile(line)) {
					String start = getNewDiffStartLine();
					System.out.println(start);
				}
				else if (isNewLineForTranslation(line)){
					System.out.println(newLineForTranslation);
				}

			}

		} catch (FileNotFoundException e) {
			throw new Exception(e);
		} finally {
			if (reader!=null)
				reader.close();
		}
	}

	private boolean isStartOfDiffForNewFile(String line) {
		diffJSFile = null;
		if (line.matches("^Index: .*")) {
			diffJSFile = line.replace("Index: ", "");
			return true;
		}
		return false;
	}

	private boolean isNewLineForTranslation(String line) {
		newLineForTranslation = null;
		// line starting with "+" and a "TAB"
		if (line.matches("^[\\+]{1}[\t].*")) {
			newLineForTranslation = line.replaceFirst("^[\\+]{1}[\t]+","");
			return true;
		}

		return false;
	}

	private String getNewDiffStartLine() {
		return "****************** " + diffJSFile + " **************************";
	}
}
