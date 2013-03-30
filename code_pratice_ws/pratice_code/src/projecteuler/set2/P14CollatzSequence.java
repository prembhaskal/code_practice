package projecteuler.set2;

import java.util.HashMap;
import java.util.Map;

public class P14CollatzSequence {

	Map<Integer, Integer> numVsSeqLength = new HashMap<Integer, Integer>();

	public void getLongestCollatzSequence() {
		int maxLength = 0;
		int maxNum = 1;

//		for (int i=100000-1;i>0;) {
		for (int i=1;i<1000000;) {
			int max = getSequenceLengthForNum(i);
			if (maxLength < max) {
				maxLength = max;
				maxNum = i;
			}

			i = i+1; // the number with the longest sequence can be even. see wikipedia
		}

		System.out.println("the longest sequence is of length " + maxLength + " for number " + maxNum);
	}


	public int getSequenceLengthForNum(long num) {

		int length = 1;

		while (num > 1) {
			if ((num&1)==0) {
				num /= 2;
			} else {
				num = 3*num + 1;
			}

			length++;
		}

		return length;
	}
}
