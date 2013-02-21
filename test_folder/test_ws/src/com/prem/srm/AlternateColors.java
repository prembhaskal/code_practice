package com.prem.srm;

/**
 * User: premkumar.bhaskal
 * Date: 1/7/13
 * Time: 10:57 AM
 */
public class AlternateColors {

	public static void main(String[] s) {
		AlternateColors colors = new AlternateColors();

		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(5)); // 1 soln x=y=1
		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(1)); // no soln

		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(20)); // 4 soln (x,y) = (0,10),(2,7),(4,4),(6,1);

		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(6));

		System.out.println(colors.noOfPositiveSolnFor3xPlus2y(24));
	}

	// solutions for eqn. 3x + 2y = c;
	public int noOfPositiveSolnFor3xPlus2y(int c) {
		// for 3x+2y = c, the co-efficients of extended euclidean algorithm
		// are n=1 and m=-1; solution for 3n+2m=gcd(3,2)=1;
		// range for k..... -nc/|b| <= k <= mc/|a|
		double lower_limit = (-1)*c/2.0;
		double upper_limit =  (-1)*c/3.0;

		int lowlimit = (int)Math.ceil(lower_limit);
		int uplimit = (int)Math.ceil(upper_limit);

		int extra = 0;
		if (c%6==0) // add 1 in case both a and b are exactly divisible
			extra++;

		return Math.abs(uplimit - lowlimit) + extra;
	}
	// returns coefficients such that a*n + b*m = gcd(a,b);
	// extended euclidean algorithm.

	// NOT IMPLEMENTED
	public int[] coefficientsForExtEuclideanEqn(int a, int b) {
		throw  new RuntimeException();
	}

}
