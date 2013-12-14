package topcoder.srm.s600.div2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s600.div2.*;  

/** 
* ORSolitaireDiv2 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 14, 2013</pre> 
* @version 1.0 
*/ 
public class ORSolitaireDiv2Test { 
    
    ORSolitaireDiv2 testClass = new ORSolitaireDiv2();
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
    * Method: getMinimum(int[] numbers, int goal) 
    * 
    */ 
    @Test
    public void testGetMinimum() throws Exception { 
        testClass.getMinimum(new int[]{}, 5);
    } 
    
        
} 
