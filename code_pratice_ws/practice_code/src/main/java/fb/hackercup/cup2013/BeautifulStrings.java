package fb.hackercup.cup2013;

import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BeautifulStrings {

	public String solve(String text, int testCase) {

		int beauty = 0;
		int base = 97;
		char[] textArray = text.toCharArray();

		int[] freq = new int[26];
		int value;
		for (int chr : textArray) {
			if (Character.isLetter(chr)) {
				value = (int)Character.toLowerCase(chr) - base;
				freq[value]++;
			}
		}

		Arrays.sort(freq);

		for (int i=25, val = 26;i>0;i--, val--) {
			beauty = beauty + freq[i]*val;
		}

		System.out.println("Case #" + testCase + ": " + beauty);

		return "Case #" + testCase + ": " + beauty;

	}
}
