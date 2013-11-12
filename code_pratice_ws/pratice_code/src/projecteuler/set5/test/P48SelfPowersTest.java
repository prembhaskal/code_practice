package projecteuler.set5.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set5.*;  

/** 
* P48SelfPowers Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 13, 2013</pre> 
* @version 1.0 
*/ 
public class P48SelfPowersTest { 
    
    P48SelfPowers testClass = new P48SelfPowers();
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
    * Method: getLast10Digits(int range) 
    * 
    */ 
    @Test
    public void testGetLast10Digits() throws Exception { 
        long last = testClass.getLast10Digits(1000);

		System.out.println("last 10 digits are " + last);
	}
    
        
} 
