package projecteuler.set3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P23NonAbundantSums {

	int max = 28123;
	Set<Integer> abundantNos = new HashSet<Integer>();

	List<Integer> abundantList = new ArrayList<Integer>();

	public void getSumOfNonAbundantSums() {

		updateAbundantNos();

		System.out.println("total abundant number are " + abundantList.size());

//		getNonAbundantNosUsingSet();

		getNonAbundantSumUsingList();
	}

	private void getNonAbundantNosUsingSet() {

		// find the number which are not sum of two abundant nos.

		int sum = 0;
		int maxAbSum = 0;
		int totalNoAb = 0;

		for (int i=1;i<=max;i++) {
			boolean canExpress = false;

			for (int abNum : abundantNos) {
				int rem = i - abNum;

				if (rem > 0 && abundantNos.contains(rem)) {
					canExpress = true;
					break;
				}
			}

			if (!canExpress) {
				sum += i;
				maxAbSum = Math.max(i, maxAbSum);
				totalNoAb++;
			}
		}

		System.out.println("max abundant number is " + maxAbSum);
		System.out.println("total non abundant sum is " + totalNoAb);
		System.out.println("sum of abundant nos is " + sum);
	}

	private void getNonAbundantSumUsingList() {

		boolean [] isAbundantSum = new boolean[max+1];
		isAbundantSum[0] = true;

		// get all possible sum less than max i.e 28123
		for (int i=0;i<abundantList.size();i++) {
			for (int j=i;j<abundantList.size();j++) {
				int sum = abundantList.get(i) + abundantList.get(j);
				if (sum <= max) {
					isAbundantSum[sum] = true; // mark which can be expressed as sum.
				} else {
					break; // we break since the list is sorted in ascending order.
				}
			}
		}

		// read all the nos which are not marked
		int sum = 0;
		for (int i=0;i<isAbundantSum.length;i++) {
			if (!isAbundantSum[i])
				sum += i;
		}

		System.out.println("sum of abundant nos is "+ sum);
	}

	private void updateAbundantNos() {
		for (int i=1;i<=max;i++) {
			int divSum = getSumOfDivisors(i);
//			int divSum = getSumOfDivisorsUsingPrimeFactors(i);
			if (divSum > i) {
//				abundantNos.add(i);
				abundantList.add(i);
			}
		}
	}

	private int getSumOfDivisors(int num) {
		int sum = 1; // considering 1 is always a factor.

		int i = 2;
		for (;i<=num/i;i++) {
			if (num%i==0) {
				sum += i;
				if (i!=num/i) // dont use the same factor for square number
					sum += (num/i);
			}
		}

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
