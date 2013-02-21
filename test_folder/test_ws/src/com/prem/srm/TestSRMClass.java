package com.prem.srm;

/**
 * User: premkumar.bhaskal
 * Date: 12/31/12
 * Time: 2:17 PM
 */
public class TestSRMClass {

	public static void main(String[] s) {
		testPowByMod();
	}

	private static void testPowByMod() {
		DivisibleSequence divisibleSequence = new DivisibleSequence();
		System.out.println(divisibleSequence.powMod(3,5));
		System.out.println(divisibleSequence.powModRec(3, 4));

		System.out.println(divisibleSequence.count(30,4));
		System.out.println(divisibleSequence.count(25883, 100000));
		System.out.println(divisibleSequence.count(1, 10000));

	}
}
