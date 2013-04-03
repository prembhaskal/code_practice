package projecteuler.set3;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class P22NamesScores {

	String [] names;

	public void printNamesScore(Scanner in, PrintWriter out, int totalNames) {
		readFile(in, totalNames);

		Arrays.sort(names);

		int nameScore = 0;

		for (int i=1;i<=names.length;i++) {
			nameScore += (i*getScore(names[i-1]));
		}

		out.println("names score is " + nameScore);
	}

	private int getScore(String name) {
		char[] nameArray = name.toCharArray();

		int score = 0;

		for (int i=0;i<nameArray.length;i++) {
			score += ((int)nameArray[i] - 64);
		}

		return score;
	}

	private void readFile(Scanner in, int totalNames) {
		names = new String[totalNames];
		int i=0;
		while (in.hasNext()) {
			names[i++] = in.nextLine();
		}
	}
}
