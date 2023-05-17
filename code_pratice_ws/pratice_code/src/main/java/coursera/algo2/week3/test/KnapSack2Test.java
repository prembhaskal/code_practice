package coursera.algo2.week3.test; 

import common.util.InputReader;
import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week3.*;  

/** 
* KnapSack2 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 24, 2013</pre> 
* @version 1.0 
*/ 
public class KnapSack2Test { 
    
    KnapSack2 testClass = new KnapSack2();
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
    * Method: getMaxValues(InputReader in) 
    * 
    */ 
    @Test
    public void testGetMaxValues() throws Exception {
				InputStream inputStream = getClass().getResourceAsStream("files/knapsack_big.txt");
//		InputStream inputStream = getClass().getResourceAsStream("files/test1.txt");
		InputReader in = new InputReader(inputStream);

		int maxValue = testClass.getMaxValues(in);
		System.out.println("max value is --> " + maxValue);
		inputStream.close();
    } 
    
        
} 
