package practice.math_problems;

import java.math.BigInteger;

public class CheckPrime {

	public boolean isPrime(long prime, int certainty) {
		certainty = Math.max(10,certainty);

		BigInteger number = BigInteger.valueOf(prime);
		boolean isPrime = number.isProbablePrime(certainty);
		return isPrime;
	}

	public boolean isPrime(long prime) {
		return isPrime(prime,10);
	}

}
