package projecteuler.set1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set1.*;  

/** 
* P10SumOfPrimes Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 27, 2013</pre> 
* @version 1.0 
*/ 
public class P10SumOfPrimesTest { 
    
    P10SumOfPrimes testClass = new P10SumOfPrimes();
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
    * Method: getSumOfPrimes(int range) 
    * 
    */ 
    @Test
    public void testGetSumOfPrimes() throws Exception {
		long sum = testClass.getSumOfPrimes(10);
		System.out.println("sum of primes till 10 is " + sum);
		sum = testClass.getSumOfPrimes(2000000);
		System.out.println("sum of primes till 2000000 is " + sum);
	}
    
        
} 
