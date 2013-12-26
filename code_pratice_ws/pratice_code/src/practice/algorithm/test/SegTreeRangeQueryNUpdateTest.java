package practice.algorithm.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* SegTreeRangeQueryNUpdate Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 26, 2013</pre> 
* @version 1.0 
*/ 
public class SegTreeRangeQueryNUpdateTest { 
    
    SegTreeRangeQueryNUpdate testClass;
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

	int[] nums;

	@Before
	public void init() {
		nums = new int[]{1, 1, 1, 1, 1, 1, 1, 1};

	}

	private void testRangeSum() {
		for (int i = 0; i < nums.length; i++) {
			long expSum = 0;
			for (int j = i; j < nums.length; j++) {
				expSum += nums[j];
				long actualSum = testClass.rangeQuery(i, j);
				assertEquals("incorrect for range for range " + i + " - " + j, expSum, actualSum);
			}
		}
	}

	@Test
	public void testRangeUpdateRangeQuery() {
		testClass = new SegTreeRangeQueryNUpdate(nums);

		testRangeSum();
		rangeUpdate(0, 4, 1);
		testRangeSum();
	}

	private void rangeUpdate(int low, int high, int value) {
		for (int i = low; i <= high; i++) {
			nums[i] += value;
		}

		testClass.rangeUpdate(low, high, value);
	}
        
} 
