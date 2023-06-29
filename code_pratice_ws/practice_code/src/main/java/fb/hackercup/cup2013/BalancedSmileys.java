package fb.hackercup.cup2013;

import java.io.PrintWriter;
import java.util.Scanner;

public class BalancedSmileys {

	public void solve(Scanner in, PrintWriter out) {
		int testCases = Integer.parseInt(in.nextLine());
		int i = 1;
		while (testCases > 0) {
			balancedSmileys(in.nextLine(), i++, out);
			testCases--;
		}
	}

	private void balancedSmileys(String text, int testNo, PrintWriter out) {
		boolean isBalanced = true;

		char[] textArray = text.toCharArray();

		if (textArray.length==0)
			isBalanced = true;

		char ch;
		for (int i=0;i<textArray.length;i++) {
			ch = textArray[i];
			if (!(Character.isLetter(ch) || ch==' ' || ch==':' || ch=='(' || ch==')')) {
				isBalanced = false;
				break;
			}
		}

		isBalanced = isBalanced && checkIfBalanced(textArray, 0, 0);

		if (isBalanced) {
			System.out.println("Case #" + testNo + ": YES");
			out.println("Case #" + testNo + ": YES");
		} else {
			System.out.println("Case #" + testNo + ": NO");
			out.println("Case #" + testNo + ": NO");
		}

	}

	private boolean checkIfBalanced(char[] text, int begin, int openBraces) {
		char ch;

		for (int i=begin;i<text.length;i++) {
			ch = text[i];

			if (ch=='(') {
				openBraces++;
			}

			if (ch==')')
				openBraces--;

			if (openBraces < 0)
				return false;

			if (ch==':') {
				if (i<text.length-1) {
					if (text[i+1]=='(' || text[i+1] ==')') {
						// either consider the smiley or don't consider
						boolean consider = checkIfBalanced(text, i+2, openBraces)
											|| checkIfBalanced(text,i+1,openBraces);
						return consider; // return since we will be essentially done
					}
				}
			}
		}

		if (openBraces!=0)
			return false;

		return true;
	}
}
