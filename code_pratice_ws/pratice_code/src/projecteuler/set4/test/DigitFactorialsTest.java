package projecteuler.set4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set4.*;  

/** 
* DigitFactorials Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 31, 2013</pre> 
* @version 1.0 
*/ 
public class DigitFactorialsTest { 
    
    DigitFactorials testClass = new DigitFactorials();
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
    * Method: getDigitFactorialSum() 
    * 
    */ 
    @Test
    public void testGetDigitFactorialSum() throws Exception {
		System.out.println("numbers sum is " + testClass.getDigitFactorialSum());
    } 
    
        
} 
