package fb.hackercup.cup2013_2;

import common.util.InputReader;
import java.io.PrintWriter;
import java.math.BigInteger;

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


	// A Simple way to do it.
	// find the last letter in each of the iteration.
	// ie. 34 means the last letter is E, 33 means T, 32 means H and so on.
	// also 1-E, 2-H, 3-T, 4-EE, 5-EH, 6-ET,
	// 7-HE, 8-HH, 9-HT, 10-TE, 11-TH, 12-TT...this shows a pattern.
	// last digit = (no-1)%3;
	// no = (no-1)/3; for the next letter.
	// it has got no overflow problems
	private String getLabelSimple(String str, long labelCount) {
		char[] chars = str.toCharArray();
		String label = "";
		while (labelCount > 0) {
			labelCount--;
			char ch = chars[((int) (labelCount % chars.length))];
			label = label + ch;
			labelCount /= chars.length;
		}

		return label;
	}

	private BigInteger[] totalCounts;
	private char[] letters;

	private String getLabel(String str, long labelCount) {
		letters = str.toCharArray();
		initTotalCount();

		// find the letters required in the label.
		BigInteger rem = BigInteger.valueOf(labelCount);
		int lettersReqd;
		for (lettersReqd = 1; lettersReqd < totalCounts.length; lettersReqd++) {
			rem = rem.subtract(totalCounts[lettersReqd]);
			if (rem.compareTo(BigInteger.ZERO) <= 0)
				break;
		}

		// find the remaining count;
		BigInteger remainingSum = BigInteger.valueOf(labelCount);
		for (int i = 1; i < lettersReqd; i++) {
			remainingSum = remainingSum.subtract(totalCounts[i]);
		}

		String finalLabel = "";

		// find all the letters required
		for (int i = lettersReqd; i > 0; i--) {
			BigInteger previous = totalCounts[i-1];
			int whichChar;

			if (remainingSum.compareTo(BigInteger.ZERO) <= 0) {
				whichChar = letters.length - 1;
			} else {
				whichChar = ((remainingSum.subtract(BigInteger.ONE)).divide(previous)).intValue();
			}

			if (whichChar < 0) {
				System.out.println("break here");
				throw new RuntimeException("");
			}

			finalLabel = finalLabel + letters[whichChar];
			remainingSum = remainingSum.mod(previous);
		}

		return finalLabel;
	}

	private void initTotalCount() {
		totalCounts = new BigInteger[51];
		totalCounts[0] = BigInteger.ONE;

		BigInteger mul = BigInteger.valueOf(letters.length);

		for (int i = 1; i < totalCounts.length; i++) {
			totalCounts[i] = totalCounts[i-1].multiply(mul);
		}
	}
}
