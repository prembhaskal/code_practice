package com.subex.localisation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
Class to merge a spanish and english js files
*/
public class TestFiles {

	
	public static void main(String[] args) throws Exception {
		
		// get the path of all the spanish folder files
//		File spanishFolder = new File("C:\\project\\ra\\mywork\\WebClient-Localization\\ALL_JS_Files\\Spanish_Original");
//		listFileNames(spanishFolder);
		
		// test the append function
//		File originalFile = new File("C:\\project\\ra\\mywork\\WebClient-Localization\\ALL_JS_Files\\AboutDetailEnglish.js");
//		File spanishFile = new File("C:\\project\\ra\\mywork\\WebClient-Localization\\ALL_JS_Files\\AboutDetails.js");
//		appendContent(originalFile, spanishFile);
		
		// finding same file name
//		String fileName = "ManualDataLoadDetail.js";
//		File spanishFolder = new File("C:\\project\\ra\\mywork\\WebClient-Localization\\ALL_JS_Files\\Spanish_Original");
//		getSameNameFile(fileName, spanishFolder);
		
		// test copying files
//		File sourceFile = new File("C:\\project\\ra\\mywork\\WebClient-Localization\\ALL_JS_Files\\Both\\TimeLine.js");
//		File unConvertedFolder = new File("C:\\project\\ra\\mywork\\WebClient-Localization\\ALL_JS_Files\\Unconverted_Files");
//		copyFilesToDestination(sourceFile, unConvertedFolder);
		
		// converting all the files.
		File finalFolder = new File("C:\\project\\ra\\mywork\\WebClient-Localization\\ALL_JS_Files\\Both");
		File spanishFolder = new File("C:\\project\\ra\\mywork\\WebClient-Localization\\ALL_JS_Files\\Spanish_Original");
		File unConvertedFolder = new File("C:\\project\\ra\\mywork\\WebClient-Localization\\ALL_JS_Files\\Unconverted_Files");
		appendAllFiles(finalFolder, spanishFolder, unConvertedFolder);
//		
		

	}
	
	public static void listFileNames(File folder) {

		for(File fileEntry : folder.listFiles()) {
			System.out.println(fileEntry.getAbsolutePath());
			System.out.println(fileEntry.getName());
		}
	}
	
	
	public static File getSameNameFile(String fileName, File folder) {
		
		for(File fileEntry : folder.listFiles()) {
			if(fileName.equalsIgnoreCase(fileEntry.getName())) {
				//System.out.println(fileName + " matches with " + fileEntry.getName());
				return fileEntry;
			}
		}
		System.out.println("No file matched for file with name " + fileName); 
		return null;
	}
	
	public static void appendContent(File originalFile, File newFile) throws IOException {
		// writer to append the content
		Charset charset = Charset.forName("UTF-8");
//		Charset charset = Charset.forName("US-ASCII");
		BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(originalFile, true), charset));
		
		// reading the content from source file
		BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(newFile), charset));
		
		String str;
		// write an empty line
		writer.write("\r\n");

		while((str = reader.readLine()) != null ) {
			writer.write(str);
			// retaining the carriage_returns and line feeds. 
			writer.write("\r\n"); 
		}
		
		
		writer.close();
		reader.close();
		
		System.out.println("appended " + originalFile.getName() + " with the content of " + newFile.getName());
	}
	
	
	public static void appendAllFiles(File finalFolder, File spanishFolder, File unConvertedFolder) throws Exception {
		int filesMatched = 0;
		int fileNotMatched = 0;
		
		for(File finalFile : finalFolder.listFiles()) {
			// get the file whose name is matching 
			File spanishFile = getSameNameFile(finalFile.getName(), spanishFolder);
			

			if(spanishFile != null) {
				appendContent(finalFile, spanishFile);
				filesMatched++;
			} else {
				fileNotMatched++;
				// copy the file to a new location
				copyFilesToDestination(finalFile, unConvertedFolder);
				
				// remove the file from final folder if it is not matching with any spanish file.
				finalFile.delete();
			}
			
		}
		
//		System.out.println("files matched -->" + filesMatched);
		System.out.println("files not matched -->" + fileNotMatched);
	}
	
	public static void copyFilesToDestination(File sourceFile, File unConvertedFolder) throws Exception {
		File destFile = new File(unConvertedFolder.getAbsolutePath() + "\\" + sourceFile.getName());
		copyFile(sourceFile, destFile);
		System.out.println("File Copied -->" + destFile.getName());
	}
	
	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();

			long count = 0;
			long size = source.size();
			while ((count += destination.transferFrom(source, count, size
					- count)) < size)
				;
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
}
