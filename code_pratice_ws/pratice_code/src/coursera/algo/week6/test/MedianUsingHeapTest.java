package coursera.algo.week6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo.week6.*;  

/** 
* MedianUsingHeap Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 14, 2013</pre> 
* @version 1.0 
*/ 
public class MedianUsingHeapTest { 
    
    MedianUsingHeap testClass = new MedianUsingHeap();
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
    * Method: solve(Scanner in, PrintWriter out, int nos) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception { 
//        Scanner in = new Scanner(getClass().getResourceAsStream("files/test_median_1.txt"));
		Scanner in = new Scanner(getClass().getResourceAsStream("files/Median.txt"));

		PrintWriter out = new PrintWriter(System.out);

//		testClass.solve(in, out, 10);
		testClass.solve(in, out, 10000);

		in.close();
		out.flush();
    } 
    
        
} 
