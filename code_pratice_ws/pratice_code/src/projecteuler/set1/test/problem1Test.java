package projecteuler.set1.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set1.*;  

/** 
* problem1 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 17, 2013</pre> 
* @version 1.0 
*/ 
public class problem1Test { 
    
    problem1 testClass = new problem1();
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
    * Method: findSumOfThreeAndFiveMultiple() 
    * 
    */ 
    @Test
    public void testFindSumOfThreeAndFiveMultiple() throws Exception {
		System.out.println(testClass.findSumOfThreeAndFiveMultiple());
    } 
    
        
} 
