package google_code_jam.cjam_13.test; 

import java.io.*;
import java.util.Scanner;

import common.util.InputReader;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import google_code_jam.cjam_13.*;  

/** 
* Bullseye Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 30, 2013</pre> 
* @version 1.0 
*/ 
public class BullseyeTest { 
    
    Bullseye testClass = new Bullseye();
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
		InputStream inputStream= getClass().getResourceAsStream("files/bulls_eye_input.txt");
		InputReader in = new InputReader(inputStream);

//		PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter(new File("output/bulls_eye_output.txt"));
		testClass.solve(in, out);

		inputStream.close();
		out.flush();
		out.close();
    } 
    

	@Test
	public void testgetMaxBlackRings() {
		long rings;
		rings = testClass.getMaxBlackRingsBigData(308436464205151562L, 1850618785230909388L);
		System.out.println("rings = " + rings);
	}
} 
