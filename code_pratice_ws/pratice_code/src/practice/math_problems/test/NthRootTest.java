package practice.math_problems.test; 

import java.math.BigInteger;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.math_problems.*;  

/** 
* NthRoot Tester. 
* 
* @author <Premkumar Bhaskal> 
* @since <pre>Jun 8, 2014</pre> 
* @version 1.0 
*/ 
public class NthRootTest { 
    
    NthRoot testClass = new NthRoot();
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
    * Method: findNthRootNewtonMethod(long num, int root, double precision) 
    * 
    */ 
    @Test
    public void testFindNthRootNewtonMethod() throws Exception { 
        long num = 3;
		int whichRoot = 2;

		long root = testClass.findNthRootNewtonMethod(num, whichRoot, true);

		System.out.println( whichRoot + " th root of the number " + num + " is " + root);
	}

	@Test
	public void findNthRootCompareResult() {
		int range = 1000_00;
		int rootRange = 80;

		for (int num = 2; num < range; num++) {
			for (int rt = 2; rt < rootRange; rt++) {
				long expRoot = (long)Math.pow(num, 1.0/rt);
				long actRoot = testClass.findIntegerRootUsingBinarySearch(num, rt);

				assertEquals("root calculation is incorrect for num:" + num + " root:" + rt,
						expRoot, actRoot);
			}
		}
	}

	@Test
	public void testSquareRoot() {
		int num = 3;

		double squareRoot = testClass.getSquareRoot(num);
		System.out.println("square root of the number is " + squareRoot);
	}
    
    @Test
	public void testRootUsingBinarySearch() {
		int num = 2;
		int root = 3;
		int squareRoot = testClass.findIntegerRootUsingBinarySearch(num, root);
		System.out.println(root + " th root of the number " + num + " is " + squareRoot);
	}

	@Test
	public void testHighestExpo() {
		for (int i = 3; i < 100; i++) {
			int exp = testClass.findHighestExp(i);
			System.out.println("power:" + i + " highest expo:" + exp);

			long pow = testClass.pow(exp, i);
			System.out.println("power1 is " + pow);

			long power = testClass.pow(exp, i);
			System.out.println("power2 is " + power);

			BigInteger actualPower = BigInteger.valueOf(exp).pow(i);
			int val = actualPower.compareTo(BigInteger.valueOf(Long.MAX_VALUE));
			if (val == 1)
				System.out.println("ERROR for number " + exp + " and power " + i);

			System.out.println("actual power is " + actualPower);
		}
	}
}
