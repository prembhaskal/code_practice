package topcoder.srm.archive.s579.div2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// this is getting too involved and boring :(
public class UndoHistory {

	private Set<String>  undoHistory = new HashSet<String>();

	public int minPresses(String[] lines) {
		int minPresses = 0;

		addToUndoHistory("");

		boolean isFirstWord = true;

		for (String line : lines) {

			List<String> wordsTyped = new ArrayList<String>();
			int pressesForWord = 0;
			String str = "";
			String wordFormed = "";
			char[] lineArr  = line.toCharArray();

			for (int i = 0; i < lineArr.length; i++) {
				char ch = lineArr[i];
				str = str + ch;
				wordFormed = wordFormed + ch;

				wordsTyped.add(str);

				if (undoHistory.contains(str)) {
					// do nothing.
				} else {
					// get the previous word from undo history
					if (!isFirstWord) {
						pressesForWord += 2;
					}

					// if it is first letter and not present in history
					if (i==0) {
						pressesForWord++;
					} else {
						// check if the key pressed now is present in history
						// if YES, continue to find longest match
						if (undoHistory.contains(ch)) {
							str = ch + "";
						}// if NO, type this word and search for next.
						else {
							pressesForWord++;
							str = "";
						}
					}
				}

				// add current word to history
				addToUndoHistory(wordFormed);

			}

			isFirstWord = false;

			// after each word press enter.
			pressesForWord++;

			minPresses += pressesForWord;
		}

		return minPresses;
	}

	private void addToUndoHistory(String str) {
		undoHistory.add(str);
	}
}
