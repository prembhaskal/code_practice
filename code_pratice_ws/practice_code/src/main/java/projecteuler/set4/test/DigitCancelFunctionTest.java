package projecteuler.set4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set4.*;  

/** 
* DigitCancelFunction Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 30, 2013</pre> 
* @version 1.0 
*/ 
public class DigitCancelFunctionTest { 
    
    DigitCancelFunction testClass = new DigitCancelFunction();
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
    * Method: isDigitCancelFunction(int numerator, int denominator) 
    * 
    */ 
    @Test
    public void testIsDigitCancelFunction() throws Exception { 
        int numer = 30;
		int denom = 50;

		System.out.println("is digit cancelling function --> " + testClass.isDigitCancelFunction(numer, denom));
	}
    
    @Test
	public void testGetAllDigitCancelFunctions() {
		testClass.getAllDigitCancellingFunctions();
	}
} 
