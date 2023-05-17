package projecteuler.set1.test;

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import projecteuler.set1.P11LargesProdInGrid;

/** 
* P11LargesProdInGrid Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 29, 2013</pre> 
* @version 1.0 
*/ 
public class P11LargesProdInGridTest { 
    
    P11LargesProdInGrid testClass = new P11LargesProdInGrid();
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
    * Method: solve(Scanner in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception { 
        Scanner in = new Scanner(getClass().getResourceAsStream("files/prod_in_grid.txt"));
		PrintWriter out = new PrintWriter(System.out);

		testClass.solve(in, out);
		in.close();
		out.flush();
    } 
    
        
} 
