package topcoder.srm.s566iv2.test; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s566iv2.*;  

/** 
* PenguinPals Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 18, 2013</pre> 
* @version 1.0 
*/ 
public class PenguinPalsTest { 
    
    PenguinPals testClass = new PenguinPals();
    long starttime;
    
    @Before
    public void before() throws Exception {
          starttime = System.nanoTime(); 
    } 
    
    @After
    public void after() throws Exception {
        long now = System.nanoTime();
        System.out.println("elapsed time " + (now-starttime)/1000000000 + "secs");
    } 
    
        /** 
    * 
    * Method: findMaximumMatching(String colors) 
    * 
    */ 
    @Test
    public void testFindMaximumMatching() throws Exception { 
    	assertEquals(testClass.findMaximumMatching("BRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBRBR"), 24);
    } 
    
        
        /** 
    * 
    * Method: countWays2(char[] colors, int fromIndex, int toIndex) 
    * 
    */
                                     
        /** 
    * 
    * Method: countWays2DP(char[] colors, int fromIndex, int toIndex, int [][] dp) 
    * 
    */
                                     
    } 
