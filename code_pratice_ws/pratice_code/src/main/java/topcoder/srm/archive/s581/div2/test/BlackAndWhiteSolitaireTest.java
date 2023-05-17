package topcoder.srm.archive.s581.div2.test;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import topcoder.srm.archive.s581.div2.*;

/** 
* BlackAndWhiteSolitaire Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jun 4, 2013</pre> 
* @version 1.0 
*/ 
public class BlackAndWhiteSolitaireTest { 
    
    BlackAndWhiteSolitaire testClass = new BlackAndWhiteSolitaire();
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
    * Method: minimumTurns(String cardFront) 
    * 
    */ 
    @Test
    public void testMinimumTurns() throws Exception { 
        String cardFront;

		cardFront = "BBBW";
		System.out.println("min flips are " + testClass.minimumTurns(cardFront));

		cardFront = "WBWBW";
		System.out.println("min flips are " + testClass.minimumTurns(cardFront));
    } 
    
        
} 
