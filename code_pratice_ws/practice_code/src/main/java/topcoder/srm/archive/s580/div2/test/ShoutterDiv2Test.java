package topcoder.srm.archive.s580.div2.test;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import topcoder.srm.archive.s580.div2.*;

/** 
* ShoutterDiv2 Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 27, 2013</pre> 
* @version 1.0 
*/ 
public class ShoutterDiv2Test { 
    
    ShoutterDiv2 testClass = new ShoutterDiv2();
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
    * Method: count(int[] s, int[] t) 
    * 
    */ 
    @Test
    public void testCount() throws Exception { 
        int[] s;
		int[] t;

		s = new int[]{1, 2, 4};
		t = new int[]{3,4,6};

		System.out.println("no of pairs are " + testClass.count(s,t));
		System.out.println("no of pairs are " + testClass.countBruteForce(s,t));


		s = new int[]{0};
		t = new int[]{100};

		System.out.println("no of pairs are " + testClass.count(s,t));
		System.out.println("no of pairs are " + testClass.countBruteForce(s,t));


		s = new int[]{0,0,0};
		t = new int[]{1,1,1};

		System.out.println("no of pairs are " + testClass.count(s,t));
		System.out.println("no of pairs are " + testClass.countBruteForce(s,t));


		s = new int[]{9,26,8,35,3,58,91,24,10,26,22,18,15,12,15,27,15,60,76,19,12,16,37,35,25,4,22,47,65,3,2,23,26,33,7,11,34,74,67,32,15,45,20,53,60,25,74,13,44,51};
		t = new int[]{26,62,80,80,52,83,100,71,20,73,23,32,80,37,34,55,51,86,97,89,17,81,74,94,79,85,77,97,87,8,70,46,58,70,97,35,80,76,82,80,19,56,65,62,80,49,79,28,75,78};

		System.out.println("no of pairs are " + testClass.count(s,t));
		System.out.println("no of pairs are " + testClass.countBruteForce(s,t));
	}
    
        
} 
