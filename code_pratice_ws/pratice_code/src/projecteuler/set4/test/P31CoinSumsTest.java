package projecteuler.set4.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set4.*;  

/** 
* P31CoinSums Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 7, 2013</pre> 
* @version 1.0 
*/ 
public class P31CoinSumsTest { 
    
    P31CoinSums testClass = new P31CoinSums();
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
    * Method: getWays(int amount) 
    * 
    */ 
    @Test
    public void testGetWays() throws Exception { 
        long ways = testClass.getWays(200);

		System.out.println("no of ways = " + ways);
	}
    
        
} 
