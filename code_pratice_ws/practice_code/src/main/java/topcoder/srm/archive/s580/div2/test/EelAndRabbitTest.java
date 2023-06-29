package topcoder.srm.archive.s580.div2.test;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import topcoder.srm.archive.s580.div2.*;

/** 
* EelAndRabbit Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 27, 2013</pre> 
* @version 1.0 
*/ 
public class EelAndRabbitTest { 
    
    EelAndRabbit testClass = new EelAndRabbit();
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
    * Method: getmax(int[] l, int[] t) 
    * 
    */ 
    @Test
    public void testGetmax() throws Exception { 
        int[] l;
		int[] t;

		l = new int[]{2, 4, 3, 2, 2, 1, 10};
		t = new int[]{2, 6, 3, 7, 0, 2, 0};

		System.out.println("max eeel catched are " + testClass.getmax(l,t));
	}
    
        /** 
    * 
    * Method: compareTo(Eel o) 
    * 
    */ 
    @Test
    public void testCompareTo() throws Exception { 
        
    } 
    
        
} 
