package topcoder.srm.s579.div2;

import java.util.HashSet;
import java.util.Set;

public class UndoHistory {

	private Set<String>  undoHistory = new HashSet<String>();

	public int minPresses(String[] lines) {
		int minPresses = 0;

		addToUndoHistory("");

		for (String line : lines) {

			int pressesForWord = 0;
			String str = "";
			char[] lineArr  = line.toCharArray();

			for (int i = 0; i < lineArr.length; i++) {
				str = str + lineArr[i];


			}
		}

		return minPresses;
	}


	public void addToUndoHistory(String str) {
		undoHistory.add(str);
	}
}
