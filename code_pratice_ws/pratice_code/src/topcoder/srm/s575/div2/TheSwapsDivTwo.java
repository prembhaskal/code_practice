package topcoder.srm.s575.div2;

import java.util.HashSet;
import java.util.Set;

public class TheSwapsDivTwo {


	public int find(int[] sequence) {
		Set<String> strSet = new HashSet<String>();

		// counting strings after swap so dont add original string.
		for (int i=0;i<sequence.length;i++) {
			for (int j=i+1;j<sequence.length;j++) {
				swap(sequence, i, j);
				String str = convertStringToArray(sequence);
//				String str = Arrays.toString(sequence);
				strSet.add(str);
				swap(sequence, j, i);
			}
		}


		return strSet.size();
	}

	private void swap(int[] sequence, int i, int j) {
		int temp = sequence[i];
		sequence[i] = sequence[j];
		sequence[j] = temp;
	}

	private String convertStringToArray(int[] array) {
		String str = "";

		for (int i=0;i<array.length;i++) {
			str = str + array[i] + ",";//add comma so that a unique string is formed.
		}

		return str;
	}
}
