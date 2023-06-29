package coursera.algo2.week1.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week1.*;  

/** 
* Question2 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 7, 2013</pre> 
* @version 1.0 
*/ 
public class Question2Test { 
    
    Question2 testClass = new Question2();
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
    * Method: solve(InputReader in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception {
//		InputStream inputStream = getClass().getResourceAsStream("files/test1.txt");
		InputStream inputStream = getClass().getResourceAsStream("files/jobs.txt");
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(System.out);

		testClass.solve(in, out);

		out.flush();
		inputStream.close();
    } 
    
        
} 
