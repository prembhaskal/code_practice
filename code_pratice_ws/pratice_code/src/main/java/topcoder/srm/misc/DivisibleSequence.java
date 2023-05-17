package topcoder.srm.misc;

import java.math.BigInteger;
import java.util.Arrays;

public class DivisibleSequence {
	long MOD = 1000000009;
	public int count(int N, int H) {

		return countUsingExponentReduction(N,H);
//		return countUsingBigInt(N,H);
//		return countUsingExpReductionStoringExpValues(N,H);
	}

	// divide the number into its prime factors.
	// N = p^r * q^t
	// see the editorial of 565 to understand the solution.

	// Can be made faster by remembering the value for an exponent reduction.
	// done, but somehow this remembering stuff is slower than normal one.

	// eg. 3^5 and 7^5,  both have exponent as 5, hence same number of combinations. this can be remembered and kept and used.

	// DONE try to solve without using BigInteger, find out why and how multiplicative inverse is useful in MOD division
	private int countUsingExponentReduction(int N, int H) {

		long result = 1;

		// basic integer factorization problem-- > trial division problem
		// for fast factorization
		for(int i=2;i*i<=N;i++) {
			int exp = 0;

			while(N%i==0) {
				exp++;
				N /= i;
			}
			if( exp > 0) {
//				result = result * combinationWithMod(exp + H - 1, exp) % MOD;
				result = (result * fastCombination(exp + H - 1, exp)) % MOD;
			}
		}

		if (N >1)
			result = (result * H) % MOD;
		return (int)result;
	}

	private int countUsingExpReductionStoringExpValues(int N, int H) {
		long result = 1;
		// max exponent possible is 29. (you can find out using for MAX N and smallest prime factor)
		int [] expValues = new int[29];
		Arrays.fill(expValues, -1);

		for(int i=2; i <= N/i;i++) {
			int exp = 0;

			while (N%i==0) {
				N /= i;
				exp++;
			}
			if(expValues[exp] < 0)
				expValues[exp] = (int)fastCombination(exp+H-1,exp);
			result = (result * expValues[exp]) % MOD;
		}

		if (N>1)
			result = (result * H) % MOD;

		return (int)result;
	}

	private long combinationWithMod(long n, long r) {
		if ( r > n/2)
			r = n-r;
		if(r==0)
			return 1;
		else {
			long res = n * combinationWithMod(n-1,r-1);
			res = res /r;
			if(res < 0)
				System.out.println("Negative result for n = " + n + " and r = " + r);
			return res;
		}
	}

	// return value will be less than MOD
	public long fastCombination(long n, long r) {
		if(r > n-r)
			r = n-r;
		if(r==0)
			return 1;
		long numerator = 1, denominator = 1;
		for (int i=1;i<=r;i++) {
			numerator = (numerator * (n - i + 1)) % MOD;
			denominator = (denominator * (r-i+1)) % MOD;
		}
		return (numerator * multiplicativeInverse(denominator)) % MOD;
	}

	// fermat's little theorem, divide by a^2 both sides, since MOD is a prime number
	private int multiplicativeInverse(long n) {
		return powMod(n, MOD-2);
	}

	// exponentiation by squaring
	public int powMod(long n, long p) {
		long res = 1;
		while(p > 0) {
			if ( (p&1)==1) {
				res = (res * n) % MOD;
			}
			n  = (n * n) % MOD;
			p /= 2;
		}

		return (int)res;
	}

	private int countUsingBigInt(int N, int H) {
		BigInteger result = BigInteger.ONE;
		for(int i=2;i*i<=N;i++) {
			int exp = 0;

			while(N%i==0) {
				exp++;
				N /= i;
			}
			if( exp > 0)
				result = result.multiply(
						combinationWithBIGint(
								BigInteger.valueOf(exp).add(BigInteger.valueOf(H)).subtract(BigInteger.ONE),
								BigInteger.valueOf(exp))).mod(BigInteger.valueOf(MOD));
		}

		if ( N > 1)
			result = result.multiply(BigInteger.valueOf(H)).mod(BigInteger.valueOf(MOD));

		return result.intValue();
	}

	public BigInteger combinationWithBIGint(BigInteger n, BigInteger r) {
		if (r.equals(BigInteger.ZERO))
			return BigInteger.ONE;
		else {
			BigInteger res = n.multiply(combinationWithBIGint(n.subtract(BigInteger.ONE), r.subtract(BigInteger.ONE)));
//			res = res.mod(BigInteger.valueOf(MOD));
			res = res.divide(r);
			return res;
		}
	}
}
