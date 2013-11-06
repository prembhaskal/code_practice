package projecteuler.set5.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set5.*;  

/** 
* SubStringDivisibility Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 6, 2013</pre> 
* @version 1.0 
*/ 
public class SubStringDivisibilityTest { 
    
    SubStringDivisibility testClass = new SubStringDivisibility();
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
    * Method: getSumOfThatNos() 
    * 
    */ 
    @Test
    public void testGetSumOfThatNos() throws Exception { 
        long sum = testClass.getSumOfThatNos();
		System.out.println("total sum is " + sum);
	}
    
        
} 
