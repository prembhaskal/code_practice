package projecteuler.set6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set6.*;  

/** 
* P57SquareRootConvergent Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 12, 2013</pre> 
* @version 1.0 
*/ 
public class P57SquareRootConvergentTest { 
    
    P57SquareRootConvergent testClass = new P57SquareRootConvergent();
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
    * Method: getReqdFractions() 
    * 
    */ 
    @Test
    public void testGetReqdFractions() throws Exception { 
        int totalCount = testClass.getReqdFractions();
		System.out.println("fractions with count of numerator higher is " + totalCount);
	}
    
        
} 
