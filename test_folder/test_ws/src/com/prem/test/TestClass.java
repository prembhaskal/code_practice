package com.prem.test;

/**
 * User: premkumar.bhaskal
 * Date: 3/1/13
 * Time: 4:21 PM
 */
public class TestClass {


	public static void main(String[] s) {
		testDivision();
	}



	// test division 103993/33102

	private static void testDivision() {
		int a = 103993;
		int b = 33102;

		int [] quot = new int[33101];
		int[] rem = new int[33101];

		for (int i=0;i<33101;i++) {
			quot[i] = a/b;
			rem[i] = a%b;
			a = rem[i] * 10;
		}

		System.out.println("done");
	}

}
