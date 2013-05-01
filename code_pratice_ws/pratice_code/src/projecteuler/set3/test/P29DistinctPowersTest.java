package projecteuler.set3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* P29DistinctPowers Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 1, 2013</pre> 
* @version 1.0 
*/ 
public class P29DistinctPowersTest { 
    
    P29DistinctPowers testClass = new P29DistinctPowers();
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
    * Method: getDistinctPowers(int range) 
    * 
    */ 
    @Test
    public void testGetDistinctPowers() throws Exception { 
        int result = testClass.getDistinctPowers(100);
		System.out.println("distinct powers are " + result);
	}
    
        
} 
