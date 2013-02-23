package topcoder.srm.s567.div2.test; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import topcoder.srm.s567.div2.*;  

/** 
* TheSquareRootDilemma Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 21, 2013</pre> 
* @version 1.0 
*/ 
public class TheSquareRootDilemmaTest { 
    
    TheSquareRootDilemma testClass = new TheSquareRootDilemma();
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
    * Method: countPairs(int N, int M) 
    * 
    */ 
    @Test
    public void testCountPairs() throws Exception { 
//    	printAllFactorsAndExponents(29);
		assertEquals(3,testClass.countPairs(10,1));
    }



	private void printAllFactorsAndExponents(int N) {

		for (int i=2;i*i<=N;i++) {
			int cnt = 0;
			while (N%i==0) {
				cnt++;
				N /= i;
			}

			if (cnt > 0)
				System.out.println("factor --> " + i + " and power --> " + cnt);
		}

		if (N>1) {
			System.out.println("factor --> " + N + " and power --> " + 1);
		}
	}
    
        
}
