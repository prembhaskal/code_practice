package projecteuler.set2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set2.*;  

/** 
* P16PowerDigitSum Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 31, 2013</pre> 
* @version 1.0 
*/ 
public class P16PowerDigitSumTest { 
    
    P16PowerDigitSum testClass = new P16PowerDigitSum();
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
    * Method: getSumOfDigits() 
    * 
    */ 
    @Test
    public void testGetSumOfDigits() throws Exception { 
        testClass.getSumOfDigits();
    } 
    
        
} 
