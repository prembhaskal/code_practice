package topcoder.srm.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// SRM 565 DIV 2.... 250 point problem
public class ValueHistogram {

	public String[] build(int[] values) {

		int [] valuesFreq = new int[10];

		Arrays.fill(valuesFreq, 0);

		for (int i = 0; i<values.length;i++) {
			valuesFreq[values[i]]++;
		}

		List<String> strList = new ArrayList<String>();

		String endStr = "..........";

		while(true) {
			String str = "";

			for (int i=0;i<10;i++) {
				if(valuesFreq[i]==0) {
					str = str + ".";
				}
				else {
					str = str + "X";
					valuesFreq[i]--;
				}

			}

			strList.add(str);
			if (str.equals(endStr))
				break;
		}


		//		// reverse the list
		String [] strArray = new String[strList.size()];

		for (int i=0;i<strArray.length;i++) {
			strArray[i] = strList.get(strArray.length - 1 -i);
		}

		return strArray;
	}

}
