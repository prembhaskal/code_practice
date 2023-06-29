package projecteuler.set1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set1.*;  

/** 
* P2EvenFibonacci Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 17, 2013</pre> 
* @version 1.0 
*/ 
public class P2EvenFibonacciTest { 
    
    P2EvenFibonacci testClass = new P2EvenFibonacci();
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
    * Method: findEvenSum(int range) 
    * 
    */ 
    @Test
    public void testFindEvenSum() throws Exception {
		System.out.println(testClass.findEvenSum(4000000));
	}
    
        
} 
