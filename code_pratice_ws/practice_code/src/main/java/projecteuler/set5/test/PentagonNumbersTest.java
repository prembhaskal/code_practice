package projecteuler.set5.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set5.*;  

/** 
* PentagonNumbers Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 8, 2013</pre> 
* @version 1.0 
*/ 
public class PentagonNumbersTest { 
    
    PentagonNumbers testClass = new PentagonNumbers();
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
    * Method: getMinDValue(int range) 
    * 
    */ 
    @Test
    public void testGetMinDValue() throws Exception { 
        int smallest  = testClass.getMinDValue(2500);
		System.out.println("smallest sum is " + smallest);
	}
    
        
} 
