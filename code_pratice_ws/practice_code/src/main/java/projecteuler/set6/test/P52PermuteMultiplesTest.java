package projecteuler.set6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set6.*;  

/** 
* P52PermuteMultiples Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 26, 2013</pre> 
* @version 1.0 
*/ 
public class P52PermuteMultiplesTest { 
    
    P52PermuteMultiples testClass = new P52PermuteMultiples();
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
    * Method: get123456Permutation() 
    * 
    */ 
    @Test
    public void testGet123456Permutation() throws Exception { 
        int lowestPermute = testClass.get123456Permutation();
		System.out.println("lowest permute is " + lowestPermute);
	}
    
        
} 
