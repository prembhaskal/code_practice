package projecteuler.set1;

public class p710001ThPrime {


	// 10001st prime number is 104743

	// by limiting the test to primes greater than square root of previously found primes,
	// even 1millionth prime could be found in less than 10 seconds on moderate pc.
	public long getPrime(int primeNumber) {
		long [] primes = new long[primeNumber];
		primes[0] = 2;
		primes[1] = 3;

		int num = 5;

		// counter to store new prime number
		int primesFoundTill = 1;
		while (true) {
			// divide by previously found primes.
			boolean isPrime = true;

			for (int primeIndex = 1;primeIndex < primeNumber;primeIndex++) {
				long prime = primes[primeIndex];
				if (prime!=0) {

					if (prime > num/prime) // we need to check only primes smaller than square-root of number being tested.
						break;

					// if divisible it is a composite.
					if (num%prime==0) {
						isPrime = false;
						break;
					}
				}
			}

			if (isPrime) { // we reach here if the number is prime.
				primes[++primesFoundTill] = num;
			}

			num += 2;

			if (primes[primeNumber-1] != 0)
				break;
		}

		return primes[primeNumber-1];
	}

}
