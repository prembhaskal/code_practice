package practice.algorithm.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.algorithm.*;  

/** 
* PrintCombination Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Oct 9, 2013</pre> 
* @version 1.0 
*/ 
public class PrintCombinationTest { 
    
    PrintCombination testClass = new PrintCombination();
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
    * Method: printCombination(int[] nums, int sizeRange) 
    * 
    */ 
    @Test
    public void testPrintCombination() throws Exception { 
        int[] nums = new int[]{1,2,3};
		testClass.printCombination(nums, 2, true);

		int binaryNo = 0b0111;
		System.out.println("binary no is " + binaryNo);
	}

	@Test
    public void testFindAllCombination() {
		int[] nums = new int[25];
		for (int i = 0; i < nums.length; i++) {
			nums[i] = i+1;
		}

		for (int i = 1; i < 25; i++) {
			testClass.printCombination(nums, i, false);
		}
	}

	@Test
	public void testPrintCombinationUsingBits() {
		int length = 5;
		int setBits = 3;

		testClass.printCombinationOfBits(length, setBits, true);
	}
} 
