package practice.string_problems.test; 

import common.util.InputReader;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.string_problems.*;  

/** 
* DictionaryTrie Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jun 29, 2014</pre> 
* @version 1.0 
*/ 
public class DictionaryTrieTest { 
    
    DictionaryTrie testClass = new DictionaryTrie();
    long starttime;
    
    @Before
    public void before() throws Exception {
          starttime = System.nanoTime(); 
    } 
    
    @After
    public void after() throws Exception {
		long now = System.nanoTime();
		System.out.println("elapsed time " + (now-starttime)/1000000 + "milli secs");
    } 
    
        /** 
    * 
    * Method: buildTrieUsingDictionary(InputReader inputReader) 
    * 
    */ 
    @Test
    public void testBuildTrieUsingDictionary() throws Exception {
		File dictionaryFile = new File("files/words.txt");
		InputStream inputStream = getClass().getResourceAsStream(dictionaryFile.getPath());
		InputReader inputReader = new InputReader(inputStream);

		testClass.buildTrieUsingDictionary(inputReader);

		// search the scramble
		int rows = 4;
		int cols = 4;
		String scrambleStr = "fxie amlo ewbx astu";
		String[] inter = scrambleStr.split(" ");
		char[][] scramble = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			scramble[i] = inter[i].toCharArray();
		}

		List<String> scrambleWords = testClass.searchWordsInScramble(scramble, rows, cols);

		for (String str : scrambleWords)
			System.out.println(str);

		System.out.println("TOTAL words " + scrambleWords.size());

		inputStream.close();
    } 
    
        /** 
    * 
    * Method: addInTrie(String word) 
    * 
    */ 
    @Test
    public void testAddInTrie() throws Exception {
		testClass.addInTrie("RUSTY");
    } 
    
        
} 
