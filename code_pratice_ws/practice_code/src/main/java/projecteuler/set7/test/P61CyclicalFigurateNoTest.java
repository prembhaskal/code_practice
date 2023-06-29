package projecteuler.set7.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set7.*;  

/** 
* P61CyclicalFigurateNo Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jan 21, 2014</pre> 
* @version 1.0 
*/ 
public class P61CyclicalFigurateNoTest { 
    
    P61CyclicalFigurateNo testClass = new P61CyclicalFigurateNo();
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
    * Method: getSixCyclicDigitSum() 
    * 
    */ 
    @Test
    public void testGetSixCyclicDigitSum() throws Exception { 
        int sum = testClass.getSixCyclicDigitSum();
    } 
    
        
} 
