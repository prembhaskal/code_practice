package projecteuler.set5.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set5.*;  

/** 
* CodedTriangleNumbers Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 8, 2013</pre> 
* @version 1.0 
*/ 
public class CodedTriangleNumbersTest { 
    
    CodedTriangleNumbers testClass = new CodedTriangleNumbers();
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
    * Method: getCountOfTriangledNumbers(InputReader in) 
    * 
    */ 
    @Test
    public void testGetCountOfTriangledNumbers() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/words.txt");
		InputReader in = new InputReader(inputStream);

		int totalWords = testClass.getCountOfTriangledNumbers(in);

		System.out.println("total words are " + totalWords);
	}
    
        
} 
