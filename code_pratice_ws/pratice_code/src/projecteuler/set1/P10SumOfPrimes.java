package projecteuler.set1;

public class P10SumOfPrimes {

	private int[] primes = new int[200000];

	public long getSumOfPrimes(int range) {
		long sum = 0;

		getAllPrimes(range);

		for (int prime : primes) {
			sum += prime;
		}

		return sum;
	}

	public void printPrimes(int range) {
		getAllPrimes(range);

		for (int i=0;primes[i]!=0;i++) {
			System.out.println(primes[i]);
		}
	}

	private void getAllPrimes(int range) {
		primes[0] = 2;
		primes[1] = 3;

		int countOfPrimes = 2;

		int num = 5;

		while (num < range) {

			boolean isPrime = true;

			for ( int prime : primes) {
				if (prime != 0) {

					if (prime > num / prime)
						break;

					if (num%prime==0) {
						isPrime = false;
						break;
					}
				}
			}

			if (isPrime) {
				primes[countOfPrimes++] = num;
			}

			num += 2;
		}
	}
}
