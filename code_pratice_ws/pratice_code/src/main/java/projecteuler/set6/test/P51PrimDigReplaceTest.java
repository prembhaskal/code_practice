package projecteuler.set6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set6.*;  

/** 
* P51PrimDigReplace Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 17, 2013</pre> 
* @version 1.0 
*/ 
public class P51PrimDigReplaceTest { 
    
    P51PrimDigReplace testClass = new P51PrimDigReplace();
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
    * Method: getSmallestPrime(int primeValue) 
    * 
    */ 
    @Test
    public void testGetSmallestPrime() throws Exception {
		int primValue = 2;
		int digitsInPrime = 6;
        testClass.getSmallestPrime(primValue, digitsInPrime);
    } 
    
        
} 
