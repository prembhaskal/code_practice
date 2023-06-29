package projecteuler.set5.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set5.*;  

/** 
* P46GoldbachConjecture Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Nov 12, 2013</pre> 
* @version 1.0 
*/ 
public class P46GoldbachConjectureTest { 
    
    P46GoldbachConjecture testClass = new P46GoldbachConjecture();
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
    * Method: getNonSatisfyingNumber() 
    * 
    */ 
    @Test
    public void testGetNonSatisfyingNumber() throws Exception { 
        int nonSNum = testClass.getNonSatisfyingNumber();
		System.out.println("non satisfying num is " + nonSNum);
	}
    
        
} 
