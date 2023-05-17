package projecteuler.set1;

public class P5SmallestMultipleLCM {


	public long getSmallestMultiple1to20() {
		int num = 0;
		long gcd;
		long lcm = 1;

//		gcd = getGCD(2,3);
//		lcm = getLCM(2,3,gcd);

		for (int i=1;i<=20;i++) {
			gcd = getGCD(i, lcm);
			lcm = getLCM(i,lcm, gcd);
		}

		return lcm;
	}

	public long getSmallestMultipleUsingExponentFactors() {
		int[] factors = new int[20];

		for (int i=2;i<=20;i++) {
			int num = i;
			for (int j=2;j <= num/j;j++) {
				int exp = 0;
				while (num%j==0) {
					num /= j;
					exp++;
				}

				factors[j] = Math.max(exp, factors[j]);
			}

			if (num > 1) {
				factors[num] = Math.max(1, factors[num]);
			}
		}

		// get the number by multiplying the numbers ^ exponent
		long lcm = 1;
		for (int i=2;i<factors.length;i++) {
//			for (int exp = factors[i]; exp > 0; exp--) {
//				lcm = lcm * i;
//			}

//			lcm = lcm * pow(i,factors[i]);

			lcm = lcm * powByRecursion(i, factors[i]);
		}

		return lcm;
	}

	private long pow(int num, int pow) {
		long  power = 1;
		while (pow > 0) {
			if (pow%2==1) {
				power = power * num;
			}

			num = num * num;
			pow /=2;
		}

		return power;
	}

	private long powByRecursion(int num, int pow) {
		if (pow==0) {
			return 1;
		}
		if (pow == 1) {
			return num;
		}
		if (pow%2==0) {
			return powByRecursion(num*num, pow/2);
		} else {
			return num * powByRecursion(num*num, pow/2);
		}
	}

	private long getLCM(long a, long b, long gcd) {
		return (a*b)/gcd;
	}

	public long getGCD(long a, long b) {
		long num = Math.max(a,b);
		long div = Math.min(a,b);

		while (num > div) {
			long rem = num%div;
			if (rem==0)
				break;
			num = div;
			div = rem;
		}

		return div;
	}
}
