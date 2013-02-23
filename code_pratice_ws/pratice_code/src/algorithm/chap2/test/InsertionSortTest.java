package algorithm.chap2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import algorithm.chap2.*;  

/** 
* InsertionSort Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jan 27, 2013</pre> 
* @version 1.0 
*/ 
public class InsertionSortTest { 
    
    InsertionSort testClass = new InsertionSort();
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
    * Method: main(String[] s) 
    * 
    */ 
    @Test
    public void testMain() throws Exception { 
        
    } 
    
        /** 
    * 
    * Method: sort1(int[] nums) 
    * 
    */ 
    @Test
    public void testSort1() throws Exception { 
        
    } 
    
        /** 
    * 
    * Method: sortRecursive(int[] nums, int elementIndex) 
    * 
    */ 
    @Test
    public void testSortRecursive() throws Exception { 
        
    } 
    
	/*
    * 
    * Method: shiftArrays(int[] nums, int start, int end) 
    * 
    */ 
    @Test
    public void testShiftArrays() throws Exception { 
        int[] nums = new int[]{1,2,4,5,6,7,8};
		testClass.shiftArrays(nums,2,5);
		testClass.printArray(nums);
    } 
    
        /** 
    * 
    * Method: printArray(int[] nums) 
    * 
    */ 
    @Test
    public void testPrintArray() throws Exception { 
        
    } 
    
        /** 
    * 
    * Method: fillArray(int[] nums) 
    * 
    */ 
    @Test
    public void testFillArray() throws Exception { 
        
    } 
    
        
} 
