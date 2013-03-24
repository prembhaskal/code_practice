package projecteuler.set1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set1.*;  

/** 
* p710001ThPrime Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 25, 2013</pre> 
* @version 1.0 
*/ 
public class p710001ThPrimeTest { 
    
    p710001ThPrime testClass = new p710001ThPrime();
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
    * Method: getPrime(int primeNumber) 
    * 
    */ 
    @Test
    public void testGetPrime() throws Exception {
		System.out.println("6th prime is " + testClass.getPrime(6));

		System.out.println("10001st prime is " + testClass.getPrime(10001));

		System.out.println("1000000th prime is " + testClass.getPrime(100000));
		System.out.println("1000000th prime is " + testClass.getPrime(1000000));
	}
    
        
} 
