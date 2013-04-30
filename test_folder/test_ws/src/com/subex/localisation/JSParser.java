package com.subex.localisation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// file from ashwini
public class JSParser {
	
	private static final char[] items= new char[]{'\"','\"',':','\"','\"',','};
	private static final String START_TAG = "var ";
	private static final String END_TAG = "}";
	public static void main (String[] args) throws FileNotFoundException
	{

		new JSParser(args[0]);
	}
	
	JSParser(String filePath) throws FileNotFoundException
	{
		System.out.println("Startig processing of Files");
		File dir = new File(filePath);
		File[] fileList = dir.listFiles();
		int errorCount =0;
		for(File f : fileList)
		{
			String str = processFile(f);
			if(!str.equals(""))
			{
				System.out.println(f.getName() + ": "+ str);
				errorCount ++;
			}
		}
		System.out.println(errorCount + "errors found!");
		System.out.println("Done");
		
	}
	private String processFile(File dataFile) throws FileNotFoundException 
	{
		Scanner sc = new Scanner(dataFile);
		boolean endTagPresent = false;
		int pointer;
		int lineCount = 0;
		while(sc.hasNext())
		{
			pointer = 0;
			lineCount++;
			String line = sc.nextLine().trim();
			if(line.equals(""))
				continue;
			
			if(endTagPresent)
			{
				if(line.indexOf(END_TAG)<0)
					return "'}' is missing at line number: "+lineCount;
				endTagPresent = false;
				continue;
				
			}
			if(!(line.indexOf(START_TAG)<0))
			{
				//endTagPresent = false;
				continue;
			}

			
			char[] chArr = line.toCharArray();
			for(int i=0;i<chArr.length;i++)
			{
				if(chArr[i]==' ')
					continue;
				if(chArr[i]==items[pointer])
				{
					String str = checkSpecificValidations(chArr,i,pointer,lineCount);
					if(!str.equals(""))
						return str;
					pointer++;
				}
			}
			if(pointer==5)
				endTagPresent = true;
			else if(pointer<5)
				return "Wrong structure at line number: " +lineCount;
			
		}
		if(endTagPresent)
			return "'}' is missing at line number: "+lineCount;
			
		return "";
	}

	private String checkSpecificValidations(char[] chArr, int i, int pointer,int lineCount) {
		switch(pointer)
		{
		case 1:		//when it encounters 2nd " 
			pointer++;
			for(i=i+1;i<chArr.length;i++)
			{
				if(chArr[i]==' ')
					continue;
				else 
				{
					if(chArr[i]==items[pointer])
					{
						pointer++;
						if(pointer == 4)
							return "";
						 
					}
					else
						return "Wrong delimiter between key value pair at line number: " +lineCount;
				}
			}
			break;
		case 4: //check if nothing comes after the comma at the end of every line
			for(i=i+1;i<chArr.length;i++)
			{
				if(!(chArr[i]==' '||chArr[i]=='\t' || chArr[i] == items[pointer+1]))
					return "Extra characters found at end of line at line number : " +lineCount;
			}
			break;
		default:
			break;
			
		}
		return "";
		
	}
	

}
