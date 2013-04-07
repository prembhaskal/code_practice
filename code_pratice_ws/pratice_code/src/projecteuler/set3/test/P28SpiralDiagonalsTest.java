package projecteuler.set3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* P28SpiralDiagonals Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 7, 2013</pre> 
* @version 1.0 
*/ 
public class P28SpiralDiagonalsTest { 
    
    P28SpiralDiagonals testClass = new P28SpiralDiagonals();
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
    * Method: getDiagonalSumOfSpiral(int size) 
    * 
    */ 
    @Test
    public void testGetDiagonalSumOfSpiral() throws Exception { 
//        testClass.getDiagonalSumOfSpiral(1000001);

		System.out.println("diagonal sum by adding corners " + testClass.getDiagonalSumByAddingCorners(1000001));
    } 
    
        
} 
