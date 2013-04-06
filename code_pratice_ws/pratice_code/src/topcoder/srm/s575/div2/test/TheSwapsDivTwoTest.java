package topcoder.srm.s575.div2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s575.div2.*;  

/** 
* TheSwapsDivTwo Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 6, 2013</pre> 
* @version 1.0 
*/ 
public class TheSwapsDivTwoTest { 
    
    TheSwapsDivTwo testClass = new TheSwapsDivTwo();
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
    * Method: find(int[] sequence) 
    * 
    */ 
    @Test
    public void testFind() throws Exception {
		int[] nos;

		nos = new int[]{2,6};
		System.out.println(testClass.find(nos));
	}
    
        
} 
