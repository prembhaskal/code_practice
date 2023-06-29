package practice.algorithm.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* SegTreeRngMulAddPointQuery Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jan 8, 2014</pre> 
* @version 1.0 
*/ 
public class SegTreeRngMulAddPointQueryTest { 
    
    SegTreeRngMulAddPointQuery testClass;
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

	@Before
	public void init() {
		nums = new int[]{2, 5, 0, 6, 9};
		testClass = new SegTreeRngMulAddPointQuery(nums);
	}

	private void checkPointQuery() {
		for (int i = 0; i < nums.length; i++) {
			int exp = nums[i];
			int act = testClass.pointQuery(i);

			assertEquals("incorrect for index --> " + i, exp, act);
		}
	}

	@Test
	public void testTheTree() {
		checkPointQuery();

		rangeMultiply(0, 3, 3);
		checkPointQuery();

		rangeMultiply(1, 3, 2);
		rangeAdd(2, 4, 7);
		checkPointQuery();

		rangeMultiply(1, 3, 7);
		rangeAdd(1, 3, 4);
		rangeMultiply(1, 3, 6);
		rangeAdd(1, 3, 8);
		rangeAdd(1, 4, 7);
		rangeAdd(2, 4, 9);
		checkPointQuery();
	}


	private void rangeMultiply(int low, int high, int value) {
		for (int i = low; i <= high; i++) {
			nums[i] *= value;
		}
		testClass.rangeMultiply(value, low, high);
	}

	private void rangeAdd(int low, int high, int value) {
		for (int i = low; i <= high; i++) {
			nums[i] += value;
		}
		testClass.rangeAdd(value, low, high);
	}
} 
