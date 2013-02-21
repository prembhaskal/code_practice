package com.prem.test.threads.executors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * User: premkumar.bhaskal
 * Date: 11/14/12
 * Time: 12:57 PM
 */
public class TestExecutors {

	public static void main(String[] s) throws Exception {
		TestExecutors testclass = new TestExecutors();
		testclass.testBigDecimal();
//		testclass.testFixedThreadPoolExecutors();
	}





	private void testFixedThreadPoolExecutors() throws Exception {
		ExecutorService executors = Executors.newFixedThreadPool(2);
		List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>();

		Callable<Boolean> print1 = new PrintNumberCallable(1,10);
		Callable<Boolean> print2 = new PrintNumberCallable(11,20);

		futures.add(executors.submit(print1));
		futures.add(executors.submit(print2));

		executors.shutdown();

		// wait till tasks are finished.
		for(Future<Boolean> future : futures) {
			future.get();
		}

		System.out.println("This line should be printed only after all threads have completed execution");
	}

	private void testBigDecimal() {
		double decimal = 99.9842;
		BigDecimal ourNum = BigDecimal.valueOf(decimal);
		BigDecimal ten = new BigDecimal(10);
		BigDecimal tenPow6 = ten.pow(6);
		BigDecimal result = ourNum.multiply(tenPow6);
		result.toBigInteger();
		System.out.println(result.toString());
		System.out.println(String.valueOf(result));
		System.out.println(String.valueOf(new BigDecimal(decimal * Math.pow(10,6))));
	}
}
