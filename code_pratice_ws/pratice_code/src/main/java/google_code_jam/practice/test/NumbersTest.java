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
* Numbers Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 12, 2013</pre> 
* @version 1.0 
*/ 
public class NumbersTest { 
    
    Numbers testClass = new Numbers();
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
        Scanner in = new Scanner(getClass().getResourceAsStream("files/numbers.txt"));
//		PrintWriter out = new PrintWriter(System.out);
		File output  = new File("output/numbers_output.txt");
		PrintWriter out = new PrintWriter(output);
		testClass.solve(in,out);

		System.out.println("numbers result");
		double number = 3 + Math.pow(5, 0.5);
		for (int i=1;i<20;i++) {
			double result = Math.pow(number, i);
			System.out.println("power " + i + " --> " + result);
		}

		in.close();
		out.flush();
    } 
    
        
} 
