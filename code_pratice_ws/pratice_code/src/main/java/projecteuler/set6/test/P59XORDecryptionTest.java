package projecteuler.set6.test; 

import common.util.InputReader;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set6.*;  

/** 
* P59XORDecryption Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 20, 2013</pre> 
* @version 1.0 
*/ 
public class P59XORDecryptionTest { 
    
    P59XORDecryption testClass = new P59XORDecryption();
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
    * Method: getEncryptionKey(String encryptedLine, Set<String> dictWords) 
    * 
    */ 
    @Test
    public void testGetEncryptionKey() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/cipher1.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String encryptedLine = reader.readLine();
		Set<String> dictWords = getDictWords();

		String key = testClass.getEncryptionKey(encryptedLine, dictWords);
		System.out.println("encryption key is " + key);

		inputStream.close();
	}


	private Set<String> getDictWords() throws IOException {
		InputStream inputStream = getClass().getResourceAsStream("files/words.txt");
		InputReader inputReader = new InputReader(inputStream);

		Set<String> dictWords = new HashSet<>();
		int count = inputReader.nextInt();

		for (int i = 0; i < count; i++) {
			dictWords.add(inputReader.next().toLowerCase());
		}

		inputStream.close();

		return dictWords;
	}
    
        
} 
