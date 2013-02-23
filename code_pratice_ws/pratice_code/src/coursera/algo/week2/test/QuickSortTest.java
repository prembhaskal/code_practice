package coursera.algo.week2.test; 

import algorithm.UtilitiesClass;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import coursera.algo.week2.*;  

/** 
* QuickSort Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Feb 17, 2013</pre> 
* @version 1.0 
*/ 
public class QuickSortTest { 
    
    QuickSort testClass = new QuickSort();
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
        Scanner in = new Scanner(getClass().getResourceAsStream("files/QuickSort.txt"));
		PrintWriter out = new PrintWriter(System.out);

		testClass.solve(in, out);

		in.close();
		out.flush();
    }


	@Test
	public void testQuickSort() throws Exception {
		int[] nums;

		nums = new int[] {4,3,9,0,1,3,6};
		System.out.println(testClass.quickSort(nums));
		UtilitiesClass.printArray(nums);

		nums = new int[]{6,5,4,3,2,1};
		System.out.println(testClass.quickSort(nums));
		UtilitiesClass.printArray(nums);
	}
    
        
} 
