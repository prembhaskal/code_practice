package projecteuler.set3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class p27QuadraticPrimes {

	List<Integer> primes;

	public int solveQuadraticPrimes() {
		int maxPrimes = 0;
		int maxA = 0;
		int maxB = 0;

		generatePrimes(100);

		Set<Integer> primeSet = new HashSet<Integer>(primes);

		for (int a=-1000;a<1000;a++) {
			for (int b=-1000;b<1000;b++) {
				int primesProd = 0;
				int n=0;
				while (true) {
					int checkNum = getEquationValue(a,b,n);

					if (checkNum > 1 && primeSet.contains(checkNum)) {
						primesProd++;
					} else {
						break;
					}

					n++;
				}

				if (primesProd > maxPrimes) {
					maxPrimes = primesProd;
					maxA = a;
					maxB = b;
				}

			}
		}

		System.out.println("max primes " + maxPrimes);
		System.out.println("coefficient are a --> " + maxA + "  and b -->" + maxB);

		int coefficientProd = maxA * maxB;
		System.out.println("product of coefficient is " + coefficientProd);
		return coefficientProd;
	}

	private int getEquationValue(int a, int b, int n) {
		int equationValue = n * n;
		equationValue += a *n;
		equationValue += b;
		return equationValue;
	}

	private void generatePrimes(int totalPrimes) {
		primes = new ArrayList<Integer>();

		primes.add(2);

		int num = 3;

		while (true) {
			boolean isPrime = true;

			for (int prime : primes) {

				if (prime > num/prime)
					break;

				if (num % prime==0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				primes.add(num);
				if (primes.size()==totalPrimes) {
					break;
				}
			}

			num += 2;
		}
	}
}
