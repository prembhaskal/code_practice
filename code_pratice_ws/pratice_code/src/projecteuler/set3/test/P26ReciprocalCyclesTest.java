package projecteuler.set3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* P26ReciprocalCycles Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 4, 2013</pre> 
* @version 1.0 
*/ 
public class P26ReciprocalCyclesTest { 
    
    P26ReciprocalCycles testClass = new P26ReciprocalCycles();
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
    * Method: findNumberWithMaxReciprocalCycles() 
    * 
    */ 
    @Test
    public void testFindNumberWithMaxReciprocalCycles() throws Exception { 
        testClass.findNumberWithMaxReciprocalCycles();
    } 
    
        /** 
    * 
    * Method: getRepeatingDigitsInReciprocal(int divisor) 
    * 
    */ 
    @Test
    public void testGetRepeatingDigitsInReciprocal() throws Exception {
		System.out.println("rep digits for 2 are " + testClass.getRepeatingDigitsInReciprocal(7));
	}
    
        
} 
