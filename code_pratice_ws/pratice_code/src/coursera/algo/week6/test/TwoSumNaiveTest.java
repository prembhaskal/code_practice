package coursera.algo.week6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo.week6.*;  

/** 
* TwoSumNaive Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 12, 2013</pre> 
* @version 1.0 
*/ 
public class TwoSumNaiveTest { 
    
    TwoSumNaive testClass = new TwoSumNaive();
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
    * Method: solve(Scanner in, PrintWriter out, int total) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception { 
        Scanner in = new Scanner(getClass().getResourceAsStream("files/HashInt.txt"));
//		Scanner in = new Scanner(getClass().getResourceAsStream("files/two_sum_medium.txt"));

		PrintWriter out = new PrintWriter(System.out);

		testClass.solve(in, out, 500000, 2500, 4000);
//		testClass.solve(in, out, 100, 30, 60); // answer 9
//		testClass.solve(in, out, 100, 60,100); // answer 28


		in.close();
		out.flush();
    } 
    
        
} 
