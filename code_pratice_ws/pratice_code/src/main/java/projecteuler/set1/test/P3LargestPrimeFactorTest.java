package projecteuler.set1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set1.*;  

/** 
* P3LargestPrimeFactor Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 24, 2013</pre> 
* @version 1.0 
*/ 
public class P3LargestPrimeFactorTest { 
    
    P3LargestPrimeFactor testClass = new P3LargestPrimeFactor();
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
    * Method: findLargestPrimeFactor(int num) 
    * 
    */ 
    @Test
    public void testFindLargestPrimeFactor() throws Exception {
		System.out.println("largest prime of 12 is " + testClass.findLargestPrimeFactor(12));
		System.out.println("largest prime of 35 is " + testClass.findLargestPrimeFactor(35));
		System.out.println("largest prime of 13195 is " + testClass.findLargestPrimeFactor(13195));
		long num = 600851475143l;
		System.out.println("largest prime of 600851475143 is " + testClass.findLargestPrimeFactor(num));
	}
    
        
} 
