package practice.algorithm.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* FenwickTree Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 19, 2013</pre> 
* @version 1.0 
*/ 
public class FenwickTreeTest { 
    
    FenwickTree testClass;
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

	private int[] nums;

	private void testRangeSum() {
		long actualSum = 0;
		long expSum = 0;
		for (int i = 0; i < nums.length; i++) {
			expSum = 0;
			for (int j = i; j < nums.length; j++) {
				expSum += nums[j];
				actualSum = testClass.getSum(i, j);

				if (expSum != actualSum) {
					fail("problem for sum from -->" + i + " to -->" + j);
				}
			}
		}
	}

	@Test
	public void testFenwickTree() {
		nums = new int[]{3,4,9,1,4,7,6,0};

		testClass = new FenwickTree(nums);
		testRangeSum();

		int val = +3;
		int updateIdx = 4;
		nums[updateIdx] += val;
		testClass.updateTree(updateIdx, val);
		testRangeSum();

		val = -6; updateIdx = 3;
		nums[updateIdx] += val;
		testClass.updateTree(updateIdx, val);
		testRangeSum();

		val = 7; updateIdx = 0;
		nums[updateIdx] -= val;
		testClass.updateTree(updateIdx, -val);


		val = -4; updateIdx = 1;
		nums[updateIdx] += val;
		testClass.updateTree(updateIdx, val);

		testRangeSum();
	}
    
        
} 
