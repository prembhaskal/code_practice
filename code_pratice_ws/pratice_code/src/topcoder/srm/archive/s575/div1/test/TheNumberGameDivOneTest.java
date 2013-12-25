package topcoder.srm.archive.s575.div1.test;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import topcoder.srm.archive.s575.div1.*;

/** 
* TheNumberGameDivOne Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Apr 8, 2013</pre> 
* @version 1.0 
*/ 
public class TheNumberGameDivOneTest { 
    
    TheNumberGameDivOne testClass = new TheNumberGameDivOne();
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
    * Method: find(long num) 
    * 
    */ 
    @Test
    public void testFind() throws Exception {
		System.out.println(testClass.find(9001));
	}
    
        
} 
