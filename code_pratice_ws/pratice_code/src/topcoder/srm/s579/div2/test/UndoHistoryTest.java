package topcoder.srm.s579.div2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s579.div2.*;  

/** 
* UndoHistory Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 31, 2013</pre> 
* @version 1.0 
*/ 
public class UndoHistoryTest { 
    
    UndoHistory testClass = new UndoHistory();
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
    * Method: minPresses(String[] lines) 
    * 
    */ 
    @Test
    public void testMinPresses() throws Exception { 
        String[] lines;

		lines = new String[]{"tomorrow", "topcoder"};

		System.out.println("min presses are " + testClass.minPresses(lines));
	}
    
        
} 
