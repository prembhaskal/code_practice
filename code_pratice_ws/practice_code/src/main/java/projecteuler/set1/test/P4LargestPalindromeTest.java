package projecteuler.set1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set1.*;  

/** 
* P4LargestPalindrome Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 24, 2013</pre> 
* @version 1.0 
*/ 
public class P4LargestPalindromeTest { 
    
    P4LargestPalindrome testClass = new P4LargestPalindrome();
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
    * Method: findLargestPalindromeTwo3DigitMultiple() 
    * 
    */ 
    @Test
    public void testFindLargestPalindromeTwo3DigitMultiple() throws Exception {
		System.out.println("largest palindrome having two 3 digit factor" + testClass.findLargestPalindromeTwo3DigitMultiple());
	}
    
        /** 
    * 
    * Method: isPalindrome(int num) 
    * 
    */ 
    @Test
    public void testIsPalindrome() throws Exception {
		System.out.println("number 3212 is palindrome " + testClass.isPalindrome(3212));
	}
    
        
} 
