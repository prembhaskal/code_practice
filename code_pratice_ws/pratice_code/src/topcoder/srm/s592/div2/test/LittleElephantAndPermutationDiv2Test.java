package topcoder.srm.s592.div2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s592.div2.*;  

/** 
* LittleElephantAndPermutationDiv2 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 28, 2013</pre> 
* @version 1.0 
*/ 
public class LittleElephantAndPermutationDiv2Test { 
    
    LittleElephantAndPermutationDiv2 testClass = new LittleElephantAndPermutationDiv2();
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
    * Method: getNumber(int N, int K) 
    * 
    */ 
    @Test
    public void testGetNumber() throws Exception { 
        int N;
		int K;

		N = 10; K = 47;
		System.out.println("answer --> " + testClass.getNumber(N, K));
	}
    
        
} 
