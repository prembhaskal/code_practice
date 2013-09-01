package projecteuler.set5.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set5.*;  

/** 
* ChampernowneConstant Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Sep 1, 2013</pre> 
* @version 1.0 
*/ 
public class ChampernowneConstantTest { 
    
    ChampernowneConstant testClass = new ChampernowneConstant();
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
    * Method: getResult() 
    * 
    */ 
    @Test
    public void testGetResult() throws Exception { 
        int prod = testClass.getResult();

		System.out.println(prod);
	}
    
        
} 
