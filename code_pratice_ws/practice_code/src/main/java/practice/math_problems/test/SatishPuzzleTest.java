package practice.math_problems.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.math_problems.*;  

/** 
* SatishPuzzle Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jan 21, 2014</pre> 
* @version 1.0 
*/ 
public class SatishPuzzleTest { 
    
    SatishPuzzle testClass = new SatishPuzzle();
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
    * Method: solve() 
    * 
    */ 
    @Test
    public void testSolve() throws Exception { 
        testClass.solve();
    } 
    
        
} 
