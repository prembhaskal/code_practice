package projecteuler.set6;

public class P53CombinatSelections {

	public int NCRGreaterThanMillion() {

		long[][] ncrValues = new long[101][101];

		// fill the first no. with 1
		for (int i = 0; i < ncrValues.length; i++) {
			ncrValues[i][0] = 1;
		}

		int count = 0;

		// start filling the pascal's triangle
		for (int i = 1; i < ncrValues.length; i++) {
			for (int j = 1; j <= i; j++) {
				ncrValues[i][j] = ncrValues[i-1][j-1] + ncrValues[i-1][j];
				if (ncrValues[i][j] > 1000_000) {
					ncrValues[i][j] = 1000_000; // set it to million so that it does not increase again.
					count++;
				}
			}
		}

		return count;
	}

}
