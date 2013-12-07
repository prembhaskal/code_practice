package fb.hackercup.cup2013_2;

import common.util.InputReader;
import java.io.PrintWriter;

public class LabelMaker {

	public void solve(InputReader in, PrintWriter out) {
		int tests = in.nextInt();

		for (int i = 1; i <= tests; i++) {
			String str = in.next();
			long labelCount = in.nextLong();
			String finalLabel = getLabel(str, labelCount);
			out.println("Case #" + i + ": " + finalLabel);
		}
	}

	private long[] totalCounts;
	private char[] letters;

	private String getLabel(String str, long labelCount) {
		letters = str.toCharArray();
		initTotalCount();

		// find the letters required in the label.
		long rem = labelCount;
		int lettersReqd;
		for (lettersReqd = 1; lettersReqd < totalCounts.length; lettersReqd++) {
			rem = rem - totalCounts[lettersReqd];
			if (rem <= 0)
				break;
		}

		// find the remaining count;
		long remainingSum = labelCount;
		for (int i = 1; i < lettersReqd; i++) {
			remainingSum -= totalCounts[i];
		}

		String finalLabel = "";

		// find all the letters required
		for (int i = lettersReqd; i > 0; i--) {
			long previous = totalCounts[i-1];
			int whichChar;

			if (remainingSum <= 0) {
				whichChar = letters.length - 1;
			} else {
				whichChar = (int) ((remainingSum - 1) / previous);
			}

			if (whichChar < 0) {
				System.out.println("break here");
				throw new RuntimeException("");
			}

			finalLabel = finalLabel + letters[whichChar];
			remainingSum = remainingSum % previous;
		}

		return finalLabel;
	}

	private void initTotalCount() {
		totalCounts = new long[51];
		totalCounts[0] = 1;

		for (int i = 1; i < totalCounts.length; i++) {
			totalCounts[i] = totalCounts[i-1] * letters.length;
		}
	}
}
