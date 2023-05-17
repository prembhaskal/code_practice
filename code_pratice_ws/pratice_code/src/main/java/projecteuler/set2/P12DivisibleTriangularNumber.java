package projecteuler.set2;

public class P12DivisibleTriangularNumber {


	public void geHighlyDivisibleTriangularNumber() {
		int triangularNo = 0;

		int i=1;
		while (true) {
			triangularNo += i;
			i++;

//			int divisors = getDivisors(triangularNo);
			int divisors = getFastDivisors(triangularNo);

			if (divisors > 500) {
				System.out.println("highly divisible trian number is " + triangularNo);
				break;
			}
		}
	}


	private int getDivisors(int num) {
		int divisors = 0;
		int i=1;
		for (;i<num/i;i++) {
			if (num %i==0)
				divisors += 2;
		}

		if (i*i == num)
			divisors += 1;

		return divisors;
	}

	private int getFastDivisors(int num) {
		int divisors = 1;

		for (int i=2;i<=num/i;i++) {
			int exp = 0;

			while (num%i==0) {
				exp++;
				num /= i;
			}

			divisors *= (exp+1);
		}

		if (num > 1) {
			divisors *= 2;
		}

		return divisors;
	}
}
