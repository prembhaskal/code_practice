package projecteuler.set3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* P22NamesScores Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 3, 2013</pre> 
* @version 1.0 
*/ 
public class P22NamesScoresTest { 
    
    P22NamesScores testClass = new P22NamesScores();
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
    * Method: printNamesScore(Scanner in, PrintWriter out, int totalNames) 
    * 
    */ 
    @Test
    public void testPrintNamesScore() throws Exception { 
        Scanner in = new Scanner(getClass().getResourceAsStream("files/names.txt"));
		PrintWriter out = new PrintWriter(System.out);

		testClass.printNamesScore(in, out, 5163);

		in.close();
		out.flush();
    } 
    
        
} 
