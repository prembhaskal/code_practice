package practice.math_problems.test;

import java.math.BigInteger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import practice.math_problems.NthRoot;

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
		long from = 9000_000_700_000_000_00L;
		long to = from + 10000;
		int rootRange = 80;

		double diffPrecision = 0.1;

		for (long num = from; num < to; num++) {
			for (int rt = 2; rt < rootRange; rt++) {
				double expRoot = Math.pow(num, 1.0/rt);
				long actRoot = testClass.findIntegerRootUsingBinarySearch(num, rt);

				if ((long)expRoot != actRoot && Math.abs(actRoot - expRoot) > diffPrecision) {
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
		long num = 125_129_118_027_271_456L;
		int root = 7;
		int squareRoot = testClass.findIntegerRootUsingBinarySearch(num, root);
		System.out.println(root + " th root of the number " + num + " is " + squareRoot);

		double mathRoot = Math.pow(num, 1.0/root);
		System.out.println(root + " th root of the number " + num + " is " + mathRoot);
		System.out.println("test power " + Math.pow(277,7));
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
