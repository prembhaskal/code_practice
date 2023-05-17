package projecteuler.set6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set6.*;  

/** 
* P60PrimePairSets Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jan 14, 2014</pre> 
* @version 1.0 
*/ 
public class P60PrimePairSetsTest { 
    
    P60PrimePairSets testClass = new P60PrimePairSets();
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
    * Method: getPrimePairSetSum() 
    * 
    */ 
    @Test
    public void testGetPrimePairSetSum() throws Exception { 
       int sum =  testClass.getPrimePairSetSum();
		System.out.println("sum is " + sum);
	}
    
        
} 
