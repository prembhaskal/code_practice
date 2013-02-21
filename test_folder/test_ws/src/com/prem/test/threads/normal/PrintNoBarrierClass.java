package com.prem.test.threads.normal;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * User: premkumar.bhaskal
 * Date: 11/14/12
 * Time: 2:20 PM
 */
public class PrintNoBarrierClass implements Runnable{

	int start;
	int end;
	CyclicBarrier barrier;

	public PrintNoBarrierClass(int start, int end, CyclicBarrier barrier) {
		this.start = start;
		this.end = end;
		this.barrier = barrier;
	}

	public void run() {
		System.out.println("Printing numbers from " + start + " to " + end);

		for(int i=start; i <= end; i++) {
			System.out.println("-->" + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
