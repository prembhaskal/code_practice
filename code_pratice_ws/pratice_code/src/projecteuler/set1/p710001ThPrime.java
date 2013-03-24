package projecteuler.set1;

public class p710001ThPrime {


	// 10001st prime number is 104743
	public long getPrime(int primeNumber) {
		long [] primes = new long[primeNumber];
		primes[0] = 2;
//		primes[1] = 3;
//		primes[2] = 5;
//		primes[3] = 7;

		int num = 3;

		int primesFoundTill = 0;
		while (true) {
			// divide by previously found primes.
			boolean isPrime = true;
			long sqrt = (long) Math.sqrt(num);

			for (int primeIndex = 0;primeIndex < primeNumber;primeIndex++) {
				long prime = primes[primeIndex];
				if (prime!=0) {

					if (prime > sqrt) // we need to check only till sqrt of previously found primes
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
