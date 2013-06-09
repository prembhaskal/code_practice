package algorithm.chap4.test; 

import algorithm.UtilitiesClass;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import algorithm.chap4.*;  

/** 
* MinSubArrayProblem Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jun 9, 2013</pre> 
* @version 1.0 
*/ 
public class MinSubArrayProblemTest { 
    
    MinSubArrayProblem testClass = new MinSubArrayProblem();
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
    * Method: getMinSubArrayBruteForce(int[] num, boolean print) 
    * 
    */ 
    @Test
    public void testGetMinSubArrayBruteForce() throws Exception {
//		int[] num = new int[]{3,1,-2,-3,2,0,1,-8,-2,8};
		int[] num = UtilitiesClass.getRandomArray(8, 30);

		System.out.println("************** Array is ***************");
		UtilitiesClass.printArray(num);

		testClass.getMinSubArrayBruteForce(num, true);
		testClass.getMinSubArrayLinear(num, true);

		num = UtilitiesClass.getRandomArray(10000, 100);

		testClass.getMinSubArrayBruteForce(num, true);
		testClass.getMinSubArrayLinear(num, true);
	}

	@Test
	public void testGetMinSubRegressionTest() throws Exception {
		for (int i = 0; i < 100; i++) {
			int[] num = UtilitiesClass.getRandomArray(30, 100);

			int min1 = testClass.getMinSubArrayBruteForce(num, false);
			int min2 = testClass.getMinSubArrayLinear(num, false);

			assertTrue("algorithm fails here", min1==min2);
		}
	}
    
        
} 
