package projecteuler.set5.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set5.*;  

/** 
* P49PrimePermutation Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 13, 2013</pre> 
* @version 1.0 
*/ 
public class P49PrimePermutationTest { 
    
    P49PrimePermutation testClass = new P49PrimePermutation();
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
    * Method: get4DigitPrimePermutation() 
    * 
    */ 
    @Test
    public void testGet4DigitPrimePermutation() throws Exception { 
        testClass.get4DigitPrimePermutation();
    } 
    
        
} 
