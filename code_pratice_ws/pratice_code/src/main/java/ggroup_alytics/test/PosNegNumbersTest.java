package ggroup_alytics.test; 

import java.util.Scanner;
import java.io.PrintWriter;

import algorithm.UtilitiesClass;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import ggroup_alytics.*;  

/** 
* PosNegNumbers Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>May 29, 2013</pre> 
* @version 1.0 
*/ 
public class PosNegNumbersTest { 
    
    PosNegNumbers testClass = new PosNegNumbers();
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
    * Method: reArrangePositiveNegativeNumbers(int[] nums) 
    * 
    */ 
    @Test
    public void testReArrangePositiveNegativeNumbers() throws Exception { 
        int[] nums;

		nums = new int[]{1,2,-3,4,-5,6,7,8,-9};

		testClass.reArrangePositiveNegativeNumbers(nums);
		UtilitiesClass.printArray(nums);

		nums = new int[]{-9, 6, -3, 8, -1, 4, -5, 7, -2};

		testClass.reArrangePositiveNegativeNumbers(nums);
		UtilitiesClass.printArray(nums);


		nums = new int[]{8, -2, 6, -1, 7, -3, -4, -5, -9};

		testClass.reArrangePositiveNegativeNumbers(nums);
		UtilitiesClass.printArray(nums);
    } 
    

} 
