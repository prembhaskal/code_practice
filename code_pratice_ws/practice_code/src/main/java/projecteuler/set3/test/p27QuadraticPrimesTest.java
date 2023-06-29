package projecteuler.set3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* p27QuadraticPrimes Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 7, 2013</pre> 
* @version 1.0 
*/ 
public class p27QuadraticPrimesTest { 
    
    p27QuadraticPrimes testClass = new p27QuadraticPrimes();
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
    * Method: solveQuadraticPrimes() 
    * 
    */ 
    @Test
    public void testSolveQuadraticPrimes() throws Exception {
		System.out.println(testClass.solveQuadraticPrimes());
	}
    
        
} 
