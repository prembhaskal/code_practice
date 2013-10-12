package coursera.algo2.week5.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week5.*;  

/** 
* TSPUsingBits Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 12, 2013</pre> 
* @version 1.0 
*/ 
public class TSPUsingBitsTest { 
    
    TSPUsingBits testClass = new TSPUsingBits();
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
    * Method: getMinTotalTravelPeriod(InputReader in) 
    * 
    */ 
    @Test
    public void testGetMinTotalTravelPeriod() throws Exception {
		InputStream inputStream = getClass().getResourceAsStream("files/test.txt");
		InputReader in = new InputReader(inputStream);

		System.out.println("min val is " + testClass.getMinTotalTravelPeriod(in));

		inputStream.close();
	}
    
        
} 
