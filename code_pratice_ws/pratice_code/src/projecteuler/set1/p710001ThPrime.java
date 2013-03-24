package projecteuler.set1;

public class p710001ThPrime {

	public long getPrime(int primeNumber) {
		long [] primes = new long[primeNumber];
		primes[0] = 2;

		int num = 2;

		while (true) {
			// divide by previously found primes.
			for (int primeIndex = 0; primeIndex<primeNumber;primeIndex++) {
				long prime = primes[primeIndex];
				if (prime!=0) {
					if (num%prime==0) // if divisible it is a composite.
						break;
				}

				if (prime==0) { // we reach here if the number is prime.
					primes[primeIndex] = num;
					break;
				}
			}

			num++;

			if (primes[primeNumber-1] != 0)
				break;
		}

		return primes[primeNumber-1];
	}

}
