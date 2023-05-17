package projecteuler.set2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set2.*;  

/** 
* P14CollatzSequence Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 31, 2013</pre> 
* @version 1.0 
*/ 
public class P14CollatzSequenceTest { 
    
    P14CollatzSequence testClass = new P14CollatzSequence();
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
    * Method: getLongestCollatzSequence() 
    * 
    */ 
    @Test
    public void testGetLongestCollatzSequence() throws Exception { 
        testClass.getLongestCollatzSequence();
//		System.out.println("sequence length is " + testClass.getSequenceLengthForNum(63728127));
    }

    
        
} 
