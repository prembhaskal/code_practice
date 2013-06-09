package algorithm.chap4.test; 

import algorithm.UtilitiesClass;
import java.util.Random;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import algorithm.chap4.*;

/** 
* MaxSubArrayProblem Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jun 5, 2013</pre> 
* @version 1.0 
*/ 
public class MaxSubArrayProblemTest { 
    
    MaxSubArrayProblem testClass = new MaxSubArrayProblem();
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
    * Method: getMaxSubArrayBruteForce(int[] num) 
    * 
    */ 
    @Test
    public void testGetMaxSubArrayBruteForce() throws Exception { 

		int[] num = getRandomArray(10,10);
//		int[] num = new int[]{3,1,-2,-3,2,0,1,-8,-2,8};
		System.out.println("************** Array is ***************");
		UtilitiesClass.printArray(num);

		testClass.getMaxSubArrayBruteForce(num);
		testClass.getMaxSubArrayLinear(num);

		num = getRandomArray(10000,100);
		System.out.println("************** Array is ***************");

		testClass.getMaxSubArrayLinear(num);
//		testClass.getMaxSubArrayBruteForce(num);
    } 

	// array has both +ve and -ve numbers in the range.
    private int[] getRandomArray(int size, int range) {
		int[] num = new int[size];

		Random random = new Random();
		Random evenOrOdd = new Random();

		for (int i=0;i<size;i++) {
			int rnd = random.nextInt(range);

			if (evenOrOdd.nextInt(2)==0)
				rnd = rnd * -1;

			num[i] = rnd;
		}

		return num;
	}
} 
