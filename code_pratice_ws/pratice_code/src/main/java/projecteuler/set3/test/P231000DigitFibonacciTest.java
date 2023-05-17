package projecteuler.set3.test; 

import algorithm.UtilitiesClass;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* P231000DigitFibonacci Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 17, 2013</pre> 
* @version 1.0 
*/ 
public class P231000DigitFibonacciTest { 
    
    P231000DigitFibonacci testClass = new P231000DigitFibonacci();
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
    * Method: findFibonacciOfDigit(int digits) 
    * 
    */ 
    @Test
    public void testFindFibonacciOfDigit() throws Exception {
//		System.out.println("term with 3 digits is " + testClass.findFibonacciOfDigit(3));
		System.out.println("term with 1000 digits is " + testClass.findFibonacciOfDigit(1000));
	}
    
        /** 
    * 
    * Method: addBigNumbers(int[] a, int[] b) 
    * 
    */ 
    @Test
    public void testAddBigNumbers() throws Exception { 
        int[] a = new int[]{2,3,2};
		int[] b = new int[]{7,9,0};

		int[] result = testClass.addBigNumbers(a,b);
		UtilitiesClass.printArray(result);
    } 
    
        
} 
