package practice.algorithm.recursive.test; 

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import practice.algorithm.recursive.*;

/** 
* PermutationFinding Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jun 27, 2014</pre> 
* @version 1.0 
*/ 
public class PermutationFindingTest { 
    
    PermutationFinding testClass = new PermutationFinding();
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
    * Method: printPermutationByRotation(int[] nums)
    * 
    */ 
    @Test
    public void testPrintPermutationLexicographic() throws Exception {
		int[] nums = new int[]{1,2,3,4,5};
        testClass.printPermutationByRotation(nums);
    } 
    

	@Test
	public void testPrintPermutationBySwapping() {
		int[] nums = new int[]{1,2,3,4,5};
		testClass.printPermutationSwapping(nums);
	}
} 
