package projecteuler.set3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* P23NonAbundantSums Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 3, 2013</pre> 
* @version 1.0 
*/ 
public class P23NonAbundantSumsTest { 
    
    P23NonAbundantSums testClass = new P23NonAbundantSums();
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
    * Method: getSumOfNonAbundantSums() 
    * 
    */ 
    @Test
    public void testGetSumOfNonAbundantSums() throws Exception { 
        testClass.getSumOfNonAbundantSums();
    }

	@Test
	public void testSumOfDivisorsUsingPrimeFactorsPower() {
		System.out.println(testClass.getSumOfDivisorsUsingPrimeFactors(12));
	}
    
        
} 
