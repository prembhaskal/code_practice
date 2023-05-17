package google_code_jam.cjam_13.test; 

import common.util.InputReader;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import google_code_jam.cjam_13.*;  

/** 
* Consonants Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 13, 2013</pre> 
* @version 1.0 
*/ 
public class ConsonantsTest { 
    
    Consonants testClass = new Consonants();
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
        InputStream inputStream = getClass().getResourceAsStream("files/consonant_input.txt");
		InputReader in = new InputReader(inputStream);

//		PrintWriter out = new PrintWriter(System.out);
		PrintWriter out = new PrintWriter(new File("output/consonant_output.txt"));

		testClass.solve(in, out);

		inputStream.close();
		out.close();
    }

	@Test
	public void testConsecutiveConsonants() {
		String str = "uartz";

		testClass.init();
		boolean result = testClass.containsConsecutiveConsonants(3,str);

		System.out.println("contains given consec consonants " + result);
	}
    
        
} 
