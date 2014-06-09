package practice.math_problems.test; 

import java.math.BigInteger;
import java.util.Scanner;
import java.io.PrintWriter;
import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import practice.math_problems.*;
import sun.security.util.BigInt;

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

		double root = testClass.findNthRootNewtonMethod(num, whichRoot, true, 0.001);

		System.out.println( whichRoot + " th root of the number " + num + " is " + root);
	}

	@Test
	public void findNthRootCompareResult() {
		long from = 2000_100_050_00L;
		long to = from + 10000;
		int rootRange = 80;

		for (long num = from; num < to; num++) {
			for (int rt = 2; rt < rootRange; rt++) {
				double expRoot = Math.pow(num, 1.0/rt);
				long actRoot = testClass.findIntegerRootUsingBinarySearch(num, rt);

//				assertEquals("root calculation is incorrect for num:" + num + " root:" + rt,
//						expRoot, actRoot);

				if ((long)expRoot != actRoot) {
					System.out.println("ERROR: incorrect value for number " + num + " and root " + rt);
					System.out.println("Exp: " + expRoot + " Actual: " + actRoot);
				}
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
		long num = 123_345_395_948_485_987L;
		int root = 2;
		int squareRoot = testClass.findIntegerRootUsingBinarySearch(num, root);
		System.out.println(root + " th root of the number " + num + " is " + squareRoot);

		double mathRoot = Math.pow(num, 1.0/root);
		System.out.println(root + " th root of the number " + num + " is " + mathRoot);

		int val = (-2 + 7) % 7;
		System.out.println(val);
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

	@Test
	public void testHighestExpPrecision() {
		for (int i = 2; i < 80; i++) {
			double highExp = testClass.findHigestExpUsingPrecision(i);
			System.out.println("power:" + i + " -- high exp:" + highExp);

			BigInteger actualPower = BigInteger.valueOf((long) highExp).pow(i);
			System.out.println("actual power is " + actualPower);
			int val = actualPower.compareTo(BigInteger.valueOf(Long.MAX_VALUE));
			if (val == 1) {
				System.out.println("ERROR for number " + highExp + " and power " + i);
				BigInteger difference = actualPower.subtract(BigInteger.valueOf(Long.MAX_VALUE));
				System.out.println("Difference is " + difference);
			}


			System.out.println("\n\n");
		}
	}
}
