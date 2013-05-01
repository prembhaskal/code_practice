package projecteuler.set3;

import java.util.*;
import org.omg.CORBA.SetOverrideType;

public class P29DistinctPowers {

	Map<Integer, Map<Integer, Integer>> numVsFactorAndPower = new HashMap<Integer, Map<Integer, Integer>>();

	Set<String> numFactorPower = new HashSet<String>();

	public int getDistinctPowers(int a, int b) {

	}

//	private void create

	private void createFactorsMapForNumber(int num) {

		Map<Integer, Integer> factorVsPower = new HashMap<Integer, Integer>();
		
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

		numVsFactorAndPower.put(num,factorVsPower);
	}
}
