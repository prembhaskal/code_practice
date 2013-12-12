package projecteuler.set6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set6.*;  

/** 
* P58SpiralPrime Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 13, 2013</pre> 
* @version 1.0 
*/ 
public class P58SpiralPrimeTest { 
    
    P58SpiralPrime testClass = new P58SpiralPrime();
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
    * Method: getSideLength() 
    * 
    */ 
    @Test
    public void testGetSideLength() throws Exception { 
        int length = testClass.getSideLength();
		System.out.println("the length is " + (length != -1 ? "" + length : "NOT FOUND"));
	}
    
        
} 
