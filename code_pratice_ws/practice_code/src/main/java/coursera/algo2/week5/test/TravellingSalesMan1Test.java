package coursera.algo2.week5.test; 

import java.io.InputStream;
import java.util.Scanner;
import java.io.PrintWriter;

import common.util.InputReader;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo2.week5.*;  

/** 
* TravellingSalesMan1 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 10, 2013</pre> 
* @version 1.0 
*/ 
public class TravellingSalesMan1Test { 
    
    TravellingSalesMan1 testClass = new TravellingSalesMan1();
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
		InputStream inputStream = getClass().getResourceAsStream("files/tsp.txt");
		InputReader in = new InputReader(inputStream);

		double minLength = testClass.getMinTotalTravelPeriod(in);

		System.out.println("min length of TSP is " + minLength);
		inputStream.close();
    } 
    
        
} 
