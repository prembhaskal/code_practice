package practice.algorithm.test; 

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* FIFOQueue Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 14, 2013</pre> 
* @version 1.0 
*/ 
public class FIFOQueueTest { 
    
    FIFOQueue testClass = new FIFOQueue();
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
    * Method: testArrayDeque() 
    * 
    */ 
    @Test
    public void testTestArrayDeque() throws Exception {
		Queue<Integer> arrayDeque = new ArrayDeque<>();
        testClass.testArrayDeque(arrayDeque);
    }

	public void testLinkedListFifo() throws Exception {
		Queue<Integer> linkedListFifo = new LinkedList<>();
		testClass.testArrayDeque(linkedListFifo);
	}
    
        
} 
