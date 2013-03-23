package projecteuler.set3.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set3.*;  

/** 
* P24MillionthPermutation Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 23, 2013</pre> 
* @version 1.0 
*/ 
public class P24MillionthPermutationTest { 
    
    P24MillionthPermutation testClass = new P24MillionthPermutation();
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
    * Method: findPermutation() 
    * 
    */ 
    @Test
    public void testFindPermutation() throws Exception { 
        testClass.findPermutation("0123456789");
    } 
    
        
} 
