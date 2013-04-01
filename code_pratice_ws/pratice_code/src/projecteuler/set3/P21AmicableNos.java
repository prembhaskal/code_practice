package projecteuler.set3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class P21AmicableNos {

	public int getAmicableNosSum(int range) {
		int amicableSum = 0;

		Map<Integer, Integer> numVsSum = new HashMap<Integer, Integer>();
		for (int i=1;i<range;i++) {
			int sum = getSumOfDivisors(i);
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
				sum += (num/i);
			}
		}

		sum += 1; // skipping 1 since we dont want to count number itself

		return sum;
	}
}
