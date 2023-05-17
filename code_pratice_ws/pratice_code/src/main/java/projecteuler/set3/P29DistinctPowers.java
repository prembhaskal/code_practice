package projecteuler.set3;

import java.util.*;

public class P29DistinctPowers {

	Map<Integer, Map<Integer, Integer>> numVsFactorAndPower = new HashMap<Integer, Map<Integer, Integer>>();

	Set<String> numFactorPower = new HashSet<String>();

	// assuming range starts from 2
	public int getDistinctPowers(int range) {
		for (int i=2;i<=range;i++) {
			createFactorsMapForNumber(i);
		}

		for (int i=2;i<=range;i++) {
			for (int j=2;j<=range;j++) {
				createString(i,j);
			}
		}

		int distinctPowers = numFactorPower.size();

		return distinctPowers;
	}

	// for each number say 6^2, we store it as 2-2,3-2
	private void createString(int num, int pow) {
		String factorPower = "";

		Map<Integer, Integer> factorVsPower = numVsFactorAndPower.get(num);

		List<Integer> factors = new ArrayList<Integer>(factorVsPower.keySet());
		Collections.sort(factors);

		for (int factor : factors) {
			int power = factorVsPower.get(factor);
			factorPower = factorPower + factor + "-" + (power*pow) + ",";
		}

		numFactorPower.add(factorPower);
	}

	private void createFactorsMapForNumber(int num) {

		Map<Integer, Integer> factorVsPower = new HashMap<Integer, Integer>();
		numVsFactorAndPower.put(num,factorVsPower);
		
		for (int i=2;i<=num/i;i++) {
			if (num%i==0) {
				int pow = 0;

				while (num%i==0) {
					num /= i;
					pow++;
				}

				factorVsPower.put(i,pow);
			}
		}

		if (num > 1) {
			factorVsPower.put(num, 1);
		}

	}
}
