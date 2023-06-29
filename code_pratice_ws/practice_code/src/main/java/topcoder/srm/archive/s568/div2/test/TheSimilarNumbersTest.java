package topcoder.srm.archive.s568.div2.test;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import topcoder.srm.archive.s568.div2.*;

/** 
* TheSimilarNumbers Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jan 29, 2013</pre> 
* @version 1.0 
*/ 
public class TheSimilarNumbersTest { 
    
    TheSimilarNumbers testClass = new TheSimilarNumbers();
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
    * Method: find(int lower, int upper) 
    * 
    */ 
    @Test
    public void testFind() throws Exception {
		System.out.println(testClass.find(5, 511));
		System.out.println(testClass.find(1,1000000));
		System.out.println(testClass.find(10,10110));
	}
    
        
} 
