package projecteuler.set1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set1.*;  

/** 
* P5SmallestMultipleLCM Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 24, 2013</pre> 
* @version 1.0 
*/ 
public class P5SmallestMultipleLCMTest { 
    
    P5SmallestMultipleLCM testClass = new P5SmallestMultipleLCM();
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
    * Method: getSmallestMultiple1to20() 
    * 
    */ 
    @Test
    public void testGetSmallestMultiple1to20() throws Exception {
		System.out.println(testClass.getSmallestMultiple1to20());
		System.out.println(testClass.getSmallestMultipleUsingExponentFactors());
	}
    
        /** 
    * 
    * Method: getGCD(int a, int b) 
    * 
    */ 
    @Test
    public void testGetGCD() throws Exception {
		System.out.println("gcd of 16 and 24 is " + testClass.getGCD(16,24));
	}
    
        
} 
