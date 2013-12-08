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

	private int[] nums = new int[]{1,2,3,4,5,6,7,8};
    SegmentAddMinusTree testClass;
    long starttime;
    
    @Before
    public void before() throws Exception {
          starttime = System.nanoTime();
		testClass = new SegmentAddMinusTree(nums);
    } 
    
    @After
    public void after() throws Exception {
		long now = System.nanoTime();
		System.out.println("elapsed time " + (now-starttime)/1000000 + "milli secs");
    }

    private void testGetSumInRange() throws Exception {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i; j < nums.length; j++) {
				long expectedSum = getSumInRange(i, j);
				long actualSum = testClass.getSumInRange(i, j);

				if (expectedSum != actualSum) {
					System.out.println("BAM!!!!! something is wrong in the range " + i + " and " + j);
				}
			}
		}
	}

	private long getSumInRange(int i, int j) {
		long sum = 0;
		for (int k = i; k <= j; k++) {
			sum += nums[k];
		}
		return sum;
	}

	@Test
	public void testSumInRange() throws Exception {
		testGetSumInRange();
	}

	@Test
	public void testAddInRange() throws Exception {
		// add to index.
		int idx;
		int value;

		idx = 2;
		value = 5;
		nums[idx] += value;
		testClass.addToIndex(value, idx);
		testGetSumInRange();
	}
    
        
} 
