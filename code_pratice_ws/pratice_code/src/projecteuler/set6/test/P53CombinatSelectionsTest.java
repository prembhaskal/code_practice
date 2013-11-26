package projecteuler.set6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set6.*;  

/** 
* P53CombinatSelections Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 26, 2013</pre> 
* @version 1.0 
*/ 
public class P53CombinatSelectionsTest { 
    
    P53CombinatSelections testClass = new P53CombinatSelections();
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
    * Method: NCRGreaterThanMillion() 
    * 
    */ 
    @Test
    public void testNCRGreaterThanMillion() throws Exception { 
        int count = testClass.NCRGreaterThanMillion();
		System.out.println("nCr more than million are " + count);
	}
    
        
} 
