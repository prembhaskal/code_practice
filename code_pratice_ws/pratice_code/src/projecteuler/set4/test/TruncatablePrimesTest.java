package projecteuler.set4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set4.*;  

/** 
* TruncatablePrimes Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Aug 1, 2013</pre> 
* @version 1.0 
*/ 
public class TruncatablePrimesTest { 
    
    TruncatablePrimes testClass = new TruncatablePrimes();
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
    * Method: findTruncatablePrimes(int range) 
    * 
    */ 
    @Test
    public void testFindTruncatablePrimes() throws Exception { 
//        testClass.isTruncatablePrime(3797);
		System.out.println("sum of nos is " + testClass.findTruncatablePrimes(1000000));
    } 
    
        
} 
