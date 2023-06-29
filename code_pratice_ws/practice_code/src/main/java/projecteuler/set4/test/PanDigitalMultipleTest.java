package projecteuler.set4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set4.*;  

/** 
* PanDigitalMultiple Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Aug 25, 2013</pre> 
* @version 1.0 
*/ 
public class PanDigitalMultipleTest { 
    
    PanDigitalMultiple testClass = new PanDigitalMultiple();
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
    * Method: getLargest9digitPanMultiple() 
    * 
    */ 
    @Test
    public void testGetLargest9digitPanMultiple() throws Exception { 
        int maxPan = testClass.getLargest9digitPanMultiple();

		System.out.println("max pandigital is " + maxPan);
	}
    
        
} 
