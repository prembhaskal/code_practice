package projecteuler.set7.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set7.*;  

/** 
* P63PowerfulDigitCount Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 7, 2014</pre> 
* @version 1.0 
*/ 
public class P63PowerfulDigitCountTest { 
    
    P63PowerfulDigitCount testClass = new P63PowerfulDigitCount();
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
    * Method: findTheDigits() 
    * 
    */ 
    @Test
    public void testFindTheDigits() throws Exception { 
        testClass.findTheDigits();
    } 
    
        
} 
