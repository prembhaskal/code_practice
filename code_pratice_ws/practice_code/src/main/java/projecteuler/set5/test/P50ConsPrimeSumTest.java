package projecteuler.set5.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set5.*;  

/** 
* P50ConsPrimeSum Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 13, 2013</pre> 
* @version 1.0 
*/ 
public class P50ConsPrimeSumTest { 
    
    P50ConsPrimeSum testClass = new P50ConsPrimeSum();
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
    * Method: getMaxPrimeWithConsPrimes(int range) 
    * 
    */ 
    @Test
    public void testGetMaxPrimeWithConsPrimes() throws Exception { 
        testClass.getMaxPrimeWithConsPrimes(1000000);
    } 
    
        
} 
