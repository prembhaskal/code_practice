package projecteuler.set2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import projecteuler.set2.*;

/** 
* P18P67MaxPath Tester.
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 31, 2013</pre> 
* @version 1.0 
*/ 
public class P18MaxPathTest { 
    
    P18P67MaxPath testClass = new P18P67MaxPath();
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
    * Method: solve(Scanner in, PrintWriter out, int size) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception { 
//        Scanner in = new Scanner(getClass().getResourceAsStream("files/P18_tree.txt"));
		Scanner in = new Scanner(getClass().getResourceAsStream("files/P67_triangle.txt"));

		PrintWriter out = new PrintWriter(System.out);

//		testClass.solve(in, out, 15);
		testClass.solve(in, out, 100);

		in.close();
		out.flush();
    } 
    
        
} 
