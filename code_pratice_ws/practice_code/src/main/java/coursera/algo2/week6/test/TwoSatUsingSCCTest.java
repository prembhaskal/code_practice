package coursera.algo2.week6.test; 

import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;

import common.util.InputReader;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week6.*;  

/** 
* TwoSatUsingSCC Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 18, 2013</pre> 
* @version 1.0 
*/ 
public class TwoSatUsingSCCTest { 
    
    TwoSatUsingSCC testClass = new TwoSatUsingSCC();
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
    * Method: isSatisifiable(InputReader in) 
    * 
    */ 
    @Test
    public void testIsSatisfiable() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/2sat6.txt");
		InputReader in = new InputReader(inputStream);

		boolean isSAT = testClass.isSatisfiable(in);

		System.out.println("is SAT " + isSAT);

		inputStream.close();
	}

	@Test
	public void testIsSatForAllFiles() throws Exception {
		for (int i = 1; i < 7; i++) {
			String file = "files/2sat" + i + ".txt";
			InputStream inputStream = getClass().getResourceAsStream(file);
			InputReader in = new InputReader(inputStream);

			boolean isSAT = testClass.isSatisfiable(in);

			System.out.println("is SAT  for " + i + " -- "+ isSAT);
		}
	}
    
        
} 
