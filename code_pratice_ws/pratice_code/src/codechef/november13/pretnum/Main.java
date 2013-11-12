package codechef.november13.pretnum;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String[] s) {
		try {
			InputStream inputStream = System.in;
			InputReader in = new InputReader(inputStream);
			PrintWriter writer = new PrintWriter(System.out);

			TaskA solution = new TaskA();
			solution.solve(in,writer);

			writer.close();
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class TaskA {

	private Set<Integer> primeSet = new HashSet<Integer>();
	Set<Long> powerOfPrimes = new HashSet<Long>();
	Set<Long> primesInRange;

	int primeCertainty = 5;
	List<Integer> primes;

	private int[] smallPrimes = new int[]{3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41};// 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173};

	public void solve(InputReader in, PrintWriter out) throws IOException {

		int tests = in.nextInt();
		preGeneratePrimes(1000000);
		storePowerOfPrimes();

		for (int i=0;i<tests;i++) {
			long lower = in.nextLong();
			long upper = in.nextLong();

			int kisses = getNumberOfKisses(lower, upper);
			out.println(kisses);
		}

	}

	private int getNumberOfKisses(long lower, long upper) {
		generatePrimesInRange(lower, upper);

		int totalKisses = 0;
//		System.out.println("upper - lower " + (upper-lower));

		for (long i=lower;i<=upper;i++) {
			if (isKissableNumber(i))
				totalKisses++;
		}

		return totalKisses;
	}

	// next prime is nothing but checking isPrime for each of the number. :( :(
	private int getNumberOfKissesNextProbPrime(long lower, long upper) {
		int totalkisses = 0;

		for (long i = lower; i <= upper; i++) {
			if (i < 1000000) {
				if (primeSet.contains((int)i) || powerOfPrimes.contains(i))
					totalkisses++;
			} else {
				if (millerCheckIfPrime(i))
					totalkisses++;
				long nextPrime = getNextPrime(i);
				i = nextPrime;
				i--; // it will be incremented by the loop
			}
		}

		return totalkisses;
	}

	// check is number is form --> num = (prime)^(k)...where k is also prime
	//TODO even simple miller's test for all 10^6 nos. wont work......need to find something else.
	private boolean isNumberPrimePower(long num) {
		if (num == 1)
			return false;

		if (num < 1000000) {
			if (primeSet.contains((int)num))
				return true;
		}
		else {
			if (millerCheckIfPrime(num))
				return true;
		}

		// means it is not prime, check if it is prime power.

		int primepower = primePrimePower(num);

		if (primepower==0)
			return false;
		else
			return true;
	}

	// 0 - if not a prime prime power,
	// else returns the power. > 1
	private int primePrimePower(long num) {

		// assume num = (prime)^k
		for (int k : smallPrimes) {
			k--;

			int kthRoot = (int) Math.pow(num, 1.0/k);

			if (kthRoot==1)
				break;

			long kthPower = pow(kthRoot, k);
			if (kthPower == num && primeSet.contains(kthRoot))
				return k;
		}

		return 0;
	}

	private long pow(long num, int k) {
		long power = 1;
		while (k > 0) {
			if ((k&1)==1) {
				power = power * num;
			}

			num = num * num;
			k /= 2;
		}

		return power;
	}

	private boolean millerCheckIfPrime(long num) {
		BigInteger bigInteger = BigInteger.valueOf(num);
		boolean isProbPrime = bigInteger.isProbablePrime(primeCertainty);
		return isProbPrime;
	}

	private long getNextPrime(long num) {
		BigInteger bigInteger = BigInteger.valueOf(num);
		return bigInteger.nextProbablePrime().longValue();
	}

	private boolean isKissableNumber(long num) {
		if (num < 1000000) {
			if (primeSet.contains((int)num))
				return true;
		}
		else {
			if (primesInRange.contains(num))
				return true;
		}

		if (powerOfPrimes.contains(num))
			return true;

		return false;
	}

	private void preGeneratePrimes(int range) {
		primes = new ArrayList<>();

		boolean [] primeChecks = new boolean[range+1];
		Arrays.fill(primeChecks, true);

		for (int i = 2; i * i <= range; i++) {
			for (int j = i * i; j <= range ; j += i) {
				primeChecks[j] = false;
			}
		}

		primes.add(2);

		for (int i = 3; i < primeChecks.length; i += 2) {
			if (primeChecks[i])
				primes.add(i);
		}

		primeSet.addAll(primes);
	}

	// segmented sieve of eratosthenes. 
	private void generatePrimesInRange(long lower, long upper) {
		List<Long> newPrimes = new ArrayList<>();
		
		int limit = (int) Math.sqrt(upper) + 1;
		
		boolean[] primeChecks = new boolean[(int)(upper - lower + 1)];
		Arrays.fill(primeChecks, true);
		
		for (int prime : primes) {
			if (prime > limit)
				break;
			long start = lower;
			int rem = (int) (start % prime);
			if (rem != 0) {
				start = start + prime - rem;
			}
			
			for ( ; start <= upper; start += prime) {
				primeChecks[((int) (start - lower))] = false;
			}
		}

		for (int i = 0; i < primeChecks.length; i++) {
			if (primeChecks[i])
				newPrimes.add(lower + i);
		}
		
		
		primesInRange = new HashSet<>(newPrimes);

//		System.out.println("primes in the range are " + newPrimes.size());

	}

	//TODO negative nos. are getting added ...fix this.
	private void storePowerOfPrimes() {
		long range = 1000000000000L;
		for (int prime : primeSet) {

			for (int power : smallPrimes) {
				long powerOfPrime = pow(prime, power-1);
				if (powerOfPrime > range || powerOfPrime < 0)
					break;

				powerOfPrimes.add(powerOfPrime);
			}
		}

//		System.out.println("total power of primes are " + powerOfPrimes.size());
//		System.out.println("max power is " + maxPower + " for " + maxPowerFor);
	}


}

class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
		tokenizer = null;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

}
