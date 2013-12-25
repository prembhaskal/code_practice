package topcoder.srm.archive.s574.div2;

import java.util.HashMap;
import java.util.Map;

public class CityMap {

	public String getLegend(String[] cityMap, int[] POIs) {
		Map<Character, Integer> poiVsNumber = new HashMap<Character, Integer>();

		for (int i=0;i<cityMap.length;i++) {
			char[] str = cityMap[i].toCharArray();

			for (int j=0;j<str.length;j++) {
				Character ch = str[j];
				if (!ch.equals('.')) {

					Integer count = poiVsNumber.get(ch);
					if (count == null) {
						count = 0;
					}
					count += 1;
					poiVsNumber.put(ch, count);
				}
			}
		}


		Map<Integer, Character> numberVsPOI = new HashMap<Integer, Character>();
		for (Map.Entry<Character, Integer> entry : poiVsNumber.entrySet()) {
			numberVsPOI.put(entry.getValue(), entry.getKey());
		}

		String result = "";

		for (int i=0;i<POIs.length;i++) {
			result = result + "" + numberVsPOI.get(POIs[i]);
		}

		return result;
	}
}
