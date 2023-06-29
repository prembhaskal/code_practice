package topcoder.srm.archive.s579.div2.test;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import topcoder.srm.archive.s579.div2.*;

/** 
* PrimalUnlicensedCreatures Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 19, 2013</pre> 
* @version 1.0 
*/ 
public class PrimalUnlicensedCreaturesTest { 
    
    PrimalUnlicensedCreatures testClass = new PrimalUnlicensedCreatures();
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
    * Method: maxWins(int initialLevel, int[] grezPower) 
    * 
    */ 
    @Test
    public void testMaxWins() throws Exception { 
        int level;
		int[] grezPower;

		level = 31;
		grezPower = new int[]{10, 20, 30};

		System.out.println("max wins are " + testClass.maxWins(level, grezPower));


		level = 20;
		grezPower = new int[]{24, 5, 6, 38};

		System.out.println("max wins are " + testClass.maxWins(level, grezPower));
	}
    
        
} 
