package projecteuler.set4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntRightTriangles {


	/*
	 GENERAL IDEA

	 let triangle be x, y and z.
	 Perimeter p = x+y+z;
	 side sum m = x+y;

	 0.5p <= m <= 0.6p  ( max side sum when hypotenuse is the smallest.)

	 and for a fixed p and m, an integer solution of a triangle exists where there exists an integer (and > 0)
	  x = ( m+- sqrt(D))/2;
	  where D = m^2 - 4pm + 2p^2

	 for each p, check for each m between the bounds for a solution.

	*/

	// TODO try solving using pythagorean triplets.
	
	public int getValueWithMaxTriangles() {
		int maxSoln = 0;
		int maxFor = 0;

		for (int i=4;i<=1000;i++) {
			int soln = getNumberOfSolutionsForPerimeter(i);

			if (soln > maxSoln) {
				maxSoln = soln;
				maxFor = i;
			}
		}

		return maxFor;
	}

	public int getNumberOfSolutionsForPerimeter(int p) {
		int soln = 0;

		int low = p/2;
		int high = (int) (p * 0.6);

		for (int m = low; m <= high; m++) {
			soln += getNumberOfSolution(p,m);
		}

		return soln;
	}


	private int getNumberOfSolution(int p, int m) {
		int D = m * m;
		D = D - (4*p*m);
		D = D + (2 * p * p);

		Integer sqrt = getSquareRoot(D);

		if (sqrt==null) // if D is not perfect square
			return 0;

		int x1 = m + sqrt;
		int x2 = m - sqrt;

		int solns = 0;

		if (x1%2!=0) // we need only integer solutions.
			return 0;

		if (x1 > 0 && x2 > 0)
			return 1;

		return 0;
	}


	/**
	 *
	 * @param num
	 * @return null, if num is not a perfect square.
	 *         positive squareRoot, if num is perfect square.
	 */
	public Integer getSquareRoot(int num) {
		if ( num <= 0)
			return null;

		if (!canBePerfectSquare(num))
			return null;

		int root = 1;

		if (num==1)
			return 1;
		// trial division
		for (int i=2;i<=num/i;i++) {
			if (num%i==0) {
				int exp = 0;

				while (num%i==0) {
					num /= i;
					exp++;
				}

				if (exp%2!=0) {
					return null;
				} else {
					for (int j = 0; j < exp / 2; j++) {
						root = root * i;
					}
				}
			}
		}

		if (num > 1) {
			return null; // no isolated factor should be present for a perfect root.
		}

		return root;
	}


	Set<Integer> squareLastDigits = new HashSet<>(Arrays.asList(0,1,4,5,6,9));
	// sanity check for perfect square.
	private boolean canBePerfectSquare(int num) {
		// get last digit
		String str = num + "";
		int lastDigit = Integer.parseInt(str.charAt(str.length()-1) + "");

		if(squareLastDigits.contains(lastDigit))
			return true;

		return false;
	}
}
