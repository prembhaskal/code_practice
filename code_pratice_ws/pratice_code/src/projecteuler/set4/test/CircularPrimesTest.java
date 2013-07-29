package projecteuler.set4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set4.*;  

/** 
* CircularPrimes Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jul 29, 2013</pre> 
* @version 1.0 
*/ 
public class CircularPrimesTest { 
    
    CircularPrimes testClass = new CircularPrimes();
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
    * Method: findCircularPrimes(int range) 
    * 
    */ 
    @Test
    public void testFindCircularPrimes() throws Exception { 
        int range = 1000000;

		int cirPrimes = testClass.findCircularPrimes(range);

		System.out.println("no of circular primes below " + range + " are " + cirPrimes);
	}

	@Test
	public void testNumberRotation() {
		int num = 4376;
		testClass.rotateNumber(num);
	}
    
        
} 
