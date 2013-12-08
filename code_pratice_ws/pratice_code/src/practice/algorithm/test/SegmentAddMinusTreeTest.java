package practice.algorithm.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* SegmentAddMinusTree Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 8, 2013</pre> 
* @version 1.0 
*/ 
public class SegmentAddMinusTreeTest { 
    
    SegmentAddMinusTree testClass;
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
    * Method: getSumInRange(int low, int high) 
    * 
    */ 
    @Test
    public void testGetSumInRange() throws Exception { 
        int[] nums = new int[]{1,2,3,4,5,6,7,8};

		testClass = new SegmentAddMinusTree(nums);

		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				long expectedSum = getSumInRange(nums, i, j);
				long actualSum = testClass.getSumInRange(i, j);

				if (expectedSum != actualSum) {
					System.out.println("BAM!!!!! something is wrong in the range " + i + " and " + j);
				}
			}
		}
	}

	private long getSumInRange(int[] nums, int i, int j) {
		long sum = 0;
		for (int k = i; k <= j; k++) {
			sum += nums[k];
		}
		return sum;
	}
    
        
} 
