package com.prem.test.threads.executors;

import java.util.concurrent.Callable;

/**
 * User: premkumar.bhaskal
 * Date: 11/14/12
 * Time: 12:52 PM
 */
public class PrintNumberCallable implements Callable<Boolean> {

	private int start;
	private int end;

	public PrintNumberCallable(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public Boolean call() throws Exception {
		printNumbers();
		return true;
	}

	private void printNumbers() {
		System.out.println("Printing numbers from " + start + " to " + end);

		for(int i=start; i <= end; i++) {
			System.out.println("-->" + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
