package com.prem.srm;

/**
 * User: premkumar.bhaskal
 * Date: 12/31/12
 * Time: 2:16 PM
 */
public class DivisibleSequence {

	int MOD = 1000000009;
	public int count(int N, int H) {

		// factorise and exponent for each of the prime factors.
		// trial division
		long result = 1;
		for (int i=2;i<=N/i;i++) {
			int exp = 0; // exp is power of the prime factor
			while(N%i==0) {
				exp++;
				N /=i;
			}
			result = result * combination(exp+H-1,exp)%MOD;
		}

		if (N > 1)
			result = result * H % MOD; // one last prime factor remains in the trial division method,
									   // accounting for its power (exp = 1), combination(H,1) = H;

		return (int)result;
	}


	public long combination(int c, int r) {

		int res = 1;
		int numerator = 1;
		int denominator = 1;
		for(int i=1;i<=r;i++) {
			numerator = numerator * (c-i+1)%MOD;
			denominator = denominator * (r-i+1) %MOD;
		}
		res = numerator * multiplicativeInverse(denominator) % MOD;
		return res;
	}


	public int multiplicativeInverse(int num) {
		// fermat little theorem. a^p == a mod p; divide by a^2 on both sides.
		return powMod(num, MOD-2);
	}

	// exponentiation by squaring
	// x^n = if n is even, (x^n/2)^2
	//       if n is odd,  (x)(x^n-1/2)^2
	public int powMod(int n, int p) {
		int res = 1;
		int a = n;
		while (p > 0) {
			if ((p&1)==1) { // in case of odd power, we multiply with number at that iteration.
				res = res * a % MOD;
//				p -= 1;     // reducing once from the power. not needed since we divide by 2 anyways
			}

			a = a * a % MOD;
			p /= 2;
		}

		return res;
	}

	public int powModRec(int n, int p) {
		if (p==1) return n;

		if((p&1)==1)
			return n * powModRec(n*n,(p)/2); // reducing by one is redundant, since we divide by 0
		else
			return powModRec(n*n,(p/2));
	}
}
