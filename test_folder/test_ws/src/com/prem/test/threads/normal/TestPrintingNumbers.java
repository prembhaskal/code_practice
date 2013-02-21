package com.prem.test.threads.normal;

import java.util.concurrent.CyclicBarrier;

/**
 * User: premkumar.bhaskal
 * Date: 11/12/12
 * Time: 10:44 AM
 */
public class TestPrintingNumbers {

	public static void main(String[] s) throws Exception {

		TestPrintingNumbers testClass = new TestPrintingNumbers();
//		testClass.testPrintingNumbers();
		testClass.testPrintNosSyncUsingBarrier();
	}


	private void testPrintingNumbers() {
		// thread1
		PrintNumber pnr1 = new PrintNumber(1,10);
		PrintNumber pnr2 = new PrintNumber(11, 20);

		// start thread 1
		Thread t1 = new Thread(pnr1);
		t1.start();
		Thread t2 = new Thread(pnr2);
		t2.start();


		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// wait till threads are complete
		System.out.println("This should be printed after all tasks have finished");
	}

	private void testPrintNosSyncUsingBarrier() throws Exception {
		CyclicBarrier barrier = new CyclicBarrier(3);
		PrintNoBarrierClass b1 = new PrintNoBarrierClass(1,10, barrier);
		PrintNoBarrierClass b2 = new PrintNoBarrierClass(11,20, barrier);

		new Thread(b1).start();
		new Thread(b2).start();

		// wait for tasks to finish
		// this barrier breaks after being hit by some pre-defined classes.
		barrier.await();

		System.out.println("All tasks completed");
	}

}
