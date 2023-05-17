package projecteuler.set2.test; 

import algorithm.UtilitiesClass;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import projecteuler.set2.*;  

/** 
* P20FactDigitSum Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Mar 31, 2013</pre> 
* @version 1.0 
*/ 
public class P20FactDigitSumTest { 
    
    P20FactDigitSum testClass = new P20FactDigitSum();
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
    * Method: findFactDigitSum(int num) 
    * 
    */ 
    @Test
    public void testFindFactDigitSum() throws Exception { 
        testClass.findFactDigitSum(1000);
    }

	@Test
	public void testMultiply() {

		int[] a = new int[]{2,5,3};
		int[] b = new int[]{1,7,8};

		int[] aProdB = testClass.multiplyTwoArrays(a,b);
		UtilitiesClass.printArrayNoSpaces(aProdB);

		int number = 18970;
		int [] numberArray = testClass.numberToArray(number);
		UtilitiesClass.printArrayNoSpaces(numberArray);
	}
    
        
} 
