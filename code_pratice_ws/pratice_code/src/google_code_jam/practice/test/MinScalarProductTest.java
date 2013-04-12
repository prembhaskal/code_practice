package google_code_jam.practice.test; 

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import google_code_jam.practice.*;  

/** 
* MinScalarProduct Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 12, 2013</pre> 
* @version 1.0 
*/ 
public class MinScalarProductTest { 
    
    MinScalarProduct testClass = new MinScalarProduct();
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
        Scanner in = new Scanner(getClass().getResourceAsStream("files/min_scalar_prod.txt"));
		File outputFile = new File("output/min_scalar_prod_output.txt");
//		PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter(outputFile);
		testClass.solve(in, out);

		System.out.println();

		in.close();
		out.flush();
    } 
    
        
} 
