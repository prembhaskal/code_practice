package coursera.algo.week1.test; 

import algorithm.UtilitiesClass;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo.week1.*;  

/** 
* CountInversions Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Feb 10, 2013</pre> 
* @version 1.0 
*/ 
public class CountInversionsTest { 
    
    CountInversions testClass = new CountInversions();
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
    * Method: solve(Scanner in, PrintWriter out) 
    * 
    */ 
    @Test
    public void testSolve() throws Exception { 
        Scanner in = new Scanner(getClass().getResourceAsStream("files/IntegerArray.txt"));
		PrintWriter out = new PrintWriter(System.out);
		testClass.solve(in,out);

		in.close();


		assertEquals(true,true);
		out.flush();
//		out.close();
    } 
    
        /** 
    * 
    * Method: countInversions(int[] nums) 
    * 
    */ 
    @Test
    public void testCountInversions() throws Exception { 
        int [] nums;

		nums = new int[] {1,1,1,1,2,4,5,7,2,3,5,6};
		System.out.println(testClass.countInversions(nums));
		UtilitiesClass.printArray(nums);


		nums = new int[] {9,8,7,6,5,4,3,2,1};
		System.out.println(testClass.countInversions(nums));
		UtilitiesClass.printArray(nums);

		nums = new int[] {1,2,3,4,5,6,7,8,9};
		System.out.println(testClass.countInversions(nums));
		UtilitiesClass.printArray(nums);
    } 
    
        
} 
