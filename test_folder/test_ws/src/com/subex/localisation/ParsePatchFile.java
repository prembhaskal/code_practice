package com.subex.localisation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: premkumar.bhaskal
 * Date: 4/29/13
 * Time: 6:21 PM
 */

// link to test javascript http://www.regular-expressions.info/javascriptexample.html
public class ParsePatchFile {

	private String diffJSFile = null;
	private String newLineForTranslation;
	Map<String, List<String>> fileVsTranslationLines = new HashMap<String, List<String>>();

//	private String parseFilePath = "D:\\project\\RA\\mywork\\WebClient-Localization\\QFE_595_localisation\\js_files_diff.patch";
	private String parseFilePath = "D:\\project\\RA\\mywork\\WebClient-Localization\\QFE_595_localisation\\properties_diff.patch";

	private String outputFolder = "D:\\project\\RA\\mywork\\WebClient-Localization\\QFE_595_localisation\\files_for_translation";
	private String newJsFilesList = "D:\\project\\RA\\mywork\\WebClient-Localization\\QFE_595_localisation\\new_files_in_595.txt";

	private String qfe595FilesPath = "D:\\project\\RA\\mywork\\WebClient-Localization\\localisation\\JS_files_595\\";

	private final String matchAdditionalLines = "^[\\+]{1}[\t].*";
	private final String replaceAdditionalLines = "^[\\+]{1}[\t]+";

	// for properties patch, we should remove the lines with '+++' in the beginning, since there is no tab in the match string,
	// and so the match string will match both single and triple '+',
	// TODO write a common match string, 'SINGLE_PLUS' + 'WORD|TAB|MULTIPLE_TAB' + 'WORD(S)';
	private final String matchAdditionalPropLines = "^[\\+]{1}.*";
	private final String replaceAdditionalPropLines = "^[\\+]{1}";


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
		System.out.println("-- Started copying...");
		File parseFile = new File(parseFilePath);
		parsePatchFile(parseFile);
		createJSFilesForTranslation();
		copyNewFiles();
		System.out.println("-- Completed copying --");
	}

	private void createJSFilesForTranslation() throws FileNotFoundException {
		for (String fileName : fileVsTranslationLines.keySet()) {
			List<String> linesForTranslation = fileVsTranslationLines.get(fileName);
			File file = createFileForTranslation(fileName);
			writeToFile(file, linesForTranslation);
		}
	}

	private void copyNewFiles() throws IOException {
		List<String> newJSFiles = getNewfilesName();

		for (String fileName : newJSFiles) {
			String filePath = qfe595FilesPath + "" + fileName;
			File sourceFile = new File(filePath);
			File destFile = new File(outputFolder, fileName);

			TestFiles.copyFile(sourceFile, destFile);
		}
	}



	private List<String> getNewfilesName() throws IOException {
		BufferedReader reader = null;
		List<String> fileNames = new ArrayList<String>();
		try {
			File file = new File(newJsFilesList);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			String str;
			while ((str=reader.readLine())!=null) {
				fileNames.add(str);
			}

			return fileNames;

		} finally {
			if (reader!=null)
				reader.close();
		}
	}

	private void writeToFile(File file, List<String> lines) throws FileNotFoundException {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file);

			for (String line : lines)
				writer.println(line);

		} finally {
			if (writer!=null)
				writer.close();
		}
	}

	private void parsePatchFile(File parseFile) throws Exception {
		BufferedReader reader = null;
		try {
			 reader = new BufferedReader(new InputStreamReader(new FileInputStream(parseFile)));

			while (true) {
				String line = reader.readLine();
				if (line==null) {
					break;
				}

				// check if start of diff for new js file
				if (isStartOfDiffForNewFile(line)) {
//					String start = getNewDiffStartLine();
//					System.out.println(start);

					// create an entry for this file
					fileVsTranslationLines.put(diffJSFile, new ArrayList<String>());
				}
				else if (isNewLineForTranslation(line)){
//					System.out.println(newLineForTranslation);
					// add the lines for translation in the corresponding entry
					List<String> diffsForFile = fileVsTranslationLines.get(diffJSFile);
					diffsForFile.add(newLineForTranslation);
				}

			}

		} catch (FileNotFoundException e) {
			throw new Exception(e);
		} finally {
			if (reader!=null)
				reader.close();
		}
	}

	private File createFileForTranslation(String fileName) {
		fileName = fileName.trim();
		return new File(outputFolder, fileName);
	}

	// the matching pattern are for svn patch file created using SVN diff.
	// it may differ with versions of svn.
	private boolean isStartOfDiffForNewFile(String line) {
		// line starting with "Index: ".
		if (line.matches("^Index: .*")) {
			diffJSFile = line.replace("Index: ", "");// extracting the JS file name from it.
			return true;
		}
		return false;
	}

	private boolean isNewLineForTranslation(String line) {
		newLineForTranslation = null;
		// line starting with "+" and a "TAB", indicates new|modified line
		if (line.matches(matchAdditionalPropLines)) {
			newLineForTranslation = line.replaceFirst(replaceAdditionalPropLines,"");// extracting the line for translation
			return true;
		}

		return false;
	}

	private String getNewDiffStartLine() {
		return "****************** " + diffJSFile + " **************************";
	}
}
