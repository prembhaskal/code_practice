package projecteuler.set3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class P21AmicableNos {

	public int getAmicableNosSum(int range) {
		int amicableSum = 0;

		Map<Integer, Integer> numVsSum = new HashMap<Integer, Integer>();
		for (int i=1;i<range;i++) {
//			int sum = getSumOfDivisors(i);
			int sum = getSumOfDivisorsUsingPrimeFactors(i);
			numVsSum.put(i, sum);
		}

		// find amicable pairs
		for (int i: numVsSum.keySet()) {
			int sum = numVsSum.get(i);
			Integer amicable = numVsSum.get(sum);
			if (amicable!=null && amicable==i && amicable!=sum) {// no perfect numbers are allowed.
				amicableSum = amicableSum + i;

//				System.out.println("amicable nos are " + amicable + " and " + sum);
			}
		}

		return amicableSum;
	}

	public int getSumOfDivisors(int num) {
		int sum = 0;

		for (int i=2;i<=num/i;i++) {
			if (num%i==0) {
				sum += i;
				if (i!=num/i) // dont use the same factor for square number
					sum += (num/i);
			}
		}

		sum += 1; // skipping 1 since we dont want to count number itself

		return sum;
	}


	// see this http://mathschallenge.net/library/number/sum_of_divisors
	public int getSumOfDivisorsUsingPrimeFactors(int num) {

		int copy = num;
		int prod = 1;

		for (int i=2;i<=num/i;i++) {
			int exp = 0;
			while (num%i==0) {
				exp++;
				num /= i;
			}

			if (exp > 0) {
				int factSum = pow(i,exp+1) - 1;
				factSum /= (i-1);
				prod *= factSum;
			}
		}

		if (num > 1) {
			// it will be a prime number, so the divisors will be 1 and the number itself.
			prod *= (1 + num);
		}

		prod = prod - copy;// we dont want the number itself to be counted as divisor.

		return prod;
	}

	private int pow(int num, int pow) {
		int power = 1;
		while (pow > 0) {
			if (pow%2==1) {
				power = power * num;
			}

			num = num * num;
			pow /= 2;
		}

		return power;
	}
}
