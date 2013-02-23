package algorithm.chap2.test; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import algorithm.chap2.*;  

/** 
* SumInArray Tester. 
* 
* @author <Authors name> 
* @since <pre>Jan 19, 2013</pre> 
* @version 1.0 
*/ 
public class SumInArrayTest { 
    
    SumInArray testClass = new SumInArray();
    long starttime;
    
    @Before
    public void before() throws Exception {
          starttime = System.nanoTime(); 
    } 
    
    @After
    public void after() throws Exception {
        long now = System.nanoTime();
        System.out.println("elapsed time " + (now-starttime)/1000000000 + "secs");
    } 
    
        /** 
    * 
    * Method: isSumofTwoPossible(int[] array, long sum) 
    * 
    */ 
    @Test
    public void testIsSumofTwoPossible() throws Exception {
		int[] array;
		long sum;

		array = new int[] {1,3,9,5,2};
		sum = 12;
    	assertEquals(testClass.isSumofTwoPossible(array, sum), true);

		array = new int[] {9,4,-1,-4,-3};
		sum = -7;
		assertEquals(testClass.isSumofTwoPossible(array, sum), true);
    } 
    
        
    } 
