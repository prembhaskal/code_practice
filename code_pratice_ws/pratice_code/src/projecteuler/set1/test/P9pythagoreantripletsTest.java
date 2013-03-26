package projecteuler.set1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set1.*;  

/** 
* P9pythagoreantriplets Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 27, 2013</pre> 
* @version 1.0 
*/ 
public class P9pythagoreantripletsTest { 
    
    P9pythagoreantriplets testClass = new P9pythagoreantriplets();
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
    * Method: getTriplets() 
    * 
    */ 
    @Test
    public void testGetTriplets() throws Exception { 
        testClass.getTriplets();
    } 
    
        
} 
