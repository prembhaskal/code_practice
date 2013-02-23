package topcoder.srm.s568.div2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s568.div2.*;  

/** 
* BallsSeparating Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jan 29, 2013</pre> 
* @version 1.0 
*/ 
public class BallsSeparatingTest { 
    
    BallsSeparating testClass = new BallsSeparating();
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
    * Method: minOperations(int[] red, int[] green, int[] blue) 
    * 
    */ 
    @Test
    public void testMinOperations() throws Exception {
		int[] red;
		int[] green;
		int[] blue;


		red = new int[] {1,1,1};
		green = new int[] {1,1,1};
		blue= new int[] {1,1,1};

		System.out.println(testClass.minOperations(red,green,blue));
	}
    
        
} 
