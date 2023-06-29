package topcoder.srm.archive.s600.div2.test;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import topcoder.srm.archive.s600.div2.*;

/** 
* TheShuttles Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 14, 2013</pre> 
* @version 1.0 
*/ 
public class TheShuttlesTest { 
    
    TheShuttles testClass = new TheShuttles();
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
    * Method: getLeastCost(int[] cnt, int baseCost, int seatCost) 
    * 
    */ 
    @Test
    public void testGetLeastCost() throws Exception { 
        int[] cnt = new int[]{51, 1, 77, 14, 17, 10, 80};
		int baseCost = 32;
		int costPerSeat  = 40;

		int cost = testClass.getLeastCost(cnt, baseCost, costPerSeat);

		System.out.println("min cost is " + cost);
	}
    
        
} 
