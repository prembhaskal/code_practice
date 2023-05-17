package projecteuler.set6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set6.*;  

/** 
* P56PowerfulDigitSum Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 2, 2013</pre> 
* @version 1.0 
*/ 
public class P56PowerfulDigitSumTest { 
    
    P56PowerfulDigitSum testClass = new P56PowerfulDigitSum();
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
    * Method: getMaxSum() 
    * 
    */ 
    @Test
    public void testGetMaxSum() throws Exception { 
        int sum = testClass.getMaxSum();
		System.out.println("max sum is " + sum);
	}
    
        
} 
