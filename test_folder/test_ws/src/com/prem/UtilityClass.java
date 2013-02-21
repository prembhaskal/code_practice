package com.prem;

/**
 * User: premkumar.bhaskal
 * Date: 1/7/13
 * Time: 6:26 PM
 */
public class UtilityClass {

	public static void main(String[] s) {
		testGCD();
	}

	public static void testGCD() {
		System.out.println(findGCD(24,16));
	}

	// GCD using euclid's algorithm
	public static int findGCD(int a, int b) {
		int num, div, rem;
		if (a > b) {
			num = a;
			div = b;
		} else {
			num = b;
			div = a;
		}

		rem = num%div;
		while (rem!=0) {
			num = div;
			div = rem;
			rem = num%div;
		}

		return div;
	}
}
