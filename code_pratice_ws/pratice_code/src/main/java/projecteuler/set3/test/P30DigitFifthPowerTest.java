package projecteuler.set3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* P30DigitFifthPower Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 1, 2013</pre> 
* @version 1.0 
*/ 
public class P30DigitFifthPowerTest { 
    
    P30DigitFifthPower testClass = new P30DigitFifthPower();
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
    * Method: getDigitFifthPowerNumbersSum() 
    * 
    */ 
    @Test
    public void testGetDigitFifthPowerNumbersSum() throws Exception { 
        int sum = testClass.getDigitFifthPowerNumbersSum();
		System.out.println("sum is " + sum);
	}
    
        
} 
