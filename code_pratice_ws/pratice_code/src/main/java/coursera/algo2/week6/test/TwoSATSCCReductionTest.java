package coursera.algo2.week6.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week6.*;  

/** 
* TwoSATSCCReduction Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 19, 2013</pre> 
* @version 1.0 
*/ 
public class TwoSATSCCReductionTest { 
    
    TwoSATSCCReduction testClass = new TwoSATSCCReduction();
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
    * Method: isSAT(InputReader in) 
    * 
    */ 
    @Test
    public void testIsSAT() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/2sat1.txt");
		InputReader in = new InputReader(inputStream);

		boolean isSAT = testClass.isSAT(in);

		System.out.println("is SAT --> " + isSAT);

		inputStream.close();
	}


	@Test
	public void testIsSatForAllFiles() throws Exception {
		for (int i = 1; i < 7; i++) {
			String file = "files/2sat" + i + ".txt";
			InputStream inputStream = getClass().getResourceAsStream(file);
			InputReader in = new InputReader(inputStream);

			boolean isSAT = testClass.isSAT(in);

			System.out.println("is SAT  for " + i + " -- "+ isSAT);
		}
	}
        
} 
