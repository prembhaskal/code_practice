package com.prem.test.threads.normal;

/**
 * User: premkumar.bhaskal
 * Date: 11/12/12
 * Time: 10:34 AM
 */
public class PrintNumber implements Runnable {

	int startNum;
	int endNum;
	public PrintNumber(int startNum, int endNum) {
		this.startNum = startNum;
		this.endNum = endNum;
	}


	@Override
	public void run() {
		System.out.println("Printing numbers from " + startNum + " to " + endNum);

		for(int i=startNum; i <= endNum; i++) {
			System.out.println("-->" + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
