package projecteuler.set4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set4.*;  

/** 
* DoubleBasePalin Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Aug 1, 2013</pre> 
* @version 1.0 
*/ 
public class DoubleBasePalinTest { 
    
    DoubleBasePalin testClass = new DoubleBasePalin();
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
    * Method: getSumOfDoubleBasePalin(int range) 
    * 
    */ 
    @Test
    public void testGetSumOfDoubleBasePalin() throws Exception { 
        int range = 1000000;
		System.out.println("sum of double base palin is " + testClass.getSumOfDoubleBasePalin(range));
	}



	@Test
	public void testIsPalindrome() {
		int num = 595;
		System.out.println(testClass.isPalindrome(num));
	}

	@Test
	public void testBinaryNumberPalindrome() {
		int num = 9;
		System.out.println(testClass.isBinaryRepPalindrome(num));
	}
        
} 
