package algorithm.chap2.test; 

import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import algorithm.chap2.*;  

/** 
* BinarySearch Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jan 27, 2013</pre> 
* @version 1.0 
*/ 
public class BinarySearchTest { 
    
    BinarySearch testClass = new BinarySearch();
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
    * Method: search1(int[] nums, int key) 
    * 
    */ 
    @Test
    public void testSearch1() throws Exception { 
        
    } 
    
        /** 
    * 
    * Method: search2(int[] nums, int key) 
    * 
    */ 
    @Test
    public void testSearch2() throws Exception {
		// values should be sorted in ascending order
        int[] nums = new int[]{0,4,6,12,13};
		assertEquals(true,testClass.search2(nums,13));
    } 
    
        
} 
