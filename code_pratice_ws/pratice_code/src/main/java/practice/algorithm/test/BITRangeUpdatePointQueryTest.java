package practice.algorithm.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* BITRangeUpdatePointQuery Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Dec 28, 2013</pre> 
* @version 1.0 
*/ 
public class BITRangeUpdatePointQueryTest { 
    
    BITRangeUpdatePointQuery testClass;
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
		nums = new int[]{1,4,1,3,6,9,0,3,6,1};
		testClass = new BITRangeUpdatePointQuery(nums);
	}

	@Test
	public void testFenTree() throws Exception {
		testPointQuery();

		rangeUpdate(2, 5, 5);
		testPointQuery();

		rangeUpdate(1, 6, 3);
		rangeUpdate(3, 7, 3);
		testPointQuery();
	}

	private void testPointQuery() {
		for (int i = 0; i < nums.length; i++) {
			int exp = nums[i];
			int act = testClass.pointQuery(i);
			assertTrue("failed for point " + i, exp == act);
		}
	}

	private void rangeUpdate(int low, int high, int val) {
		for (int i = low; i <= high; i++) {
			nums[i] += val;
		}

		testClass.rangeUpdate(low, high, val);
	}
    
        
} 
