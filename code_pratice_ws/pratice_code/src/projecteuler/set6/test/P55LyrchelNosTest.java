package projecteuler.set6.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set6.*;  

/** 
* P55LyrchelNos Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 2, 2013</pre> 
* @version 1.0 
*/ 
public class P55LyrchelNosTest { 
    
    P55LyrchelNos testClass = new P55LyrchelNos();
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
    * Method: totalLyrchelNosBelow10k() 
    * 
    */ 
    @Test
    public void testTotalLyrchelNosBelow10k() throws Exception { 
        int total = testClass.totalLyrchelNosBelow10k();

		System.out.println("total lychrel nos are " + total);
	}
    
        
} 
