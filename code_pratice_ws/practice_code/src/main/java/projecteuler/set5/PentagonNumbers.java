package projecteuler.set5;

import java.util.HashSet;
import java.util.Set;

public class PentagonNumbers {

	public int getMinDValue(int range) {
		generatePentagonNos(range);

		// Pj + Pk = Pm   where m > j
		// Pj - Pk = Pn   where n < j

		int smallestD = -1;

		for (int n = 1; n < pentagonNos.length; n++) {
			for (int k = 1; k < pentagonNos.length; k++) {
				int pentaJ = pentagonNos[n] + pentagonNos[k];
				if (pentaSet.contains(pentaJ)) { // Pj = Pn + Pk
					int pentaM = pentaJ + pentagonNos[k];
					if (pentaSet.contains(pentaM)) {
						smallestD = pentagonNos[n];
						break;
					}
				}
			}

			if (smallestD > 0)
				break;
		}

		return smallestD;
	}
	
	private int[] pentagonNos;

	private Set<Integer> pentaSet;
	
	private void generatePentagonNos(int total) {
		pentagonNos = new int[total + 1];
		pentaSet = new HashSet<>();

		for (int i = 1; i < pentagonNos.length; i++) {
			int penta = i * (3*i - 1);
			penta /= 2;

			pentagonNos[i] = penta;
			pentaSet.add(penta);
		}
	}
	
}
