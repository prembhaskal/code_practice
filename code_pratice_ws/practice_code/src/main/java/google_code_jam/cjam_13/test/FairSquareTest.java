package google_code_jam.cjam_13.test; 

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import google_code_jam.cjam_13.*;  

/** 
* FairSquare Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 13, 2013</pre> 
* @version 1.0 
*/ 
public class FairSquareTest { 
    
    FairSquare testClass = new FairSquare();
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
        Scanner in = new Scanner(getClass().getResourceAsStream("files/fair_square_input.txt"));
//		PrintWriter out = new PrintWriter(System.out);

		File outputFile = new File("output/fair_square_output.txt");
		PrintWriter out = new PrintWriter(outputFile);

		testClass.solve(in, out);

		in.close();
		out.close();

	}

	@Test
	public void printFairSquares() {
		testClass.printFairSquare();
	}
        
} 
