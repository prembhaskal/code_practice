package com.prem.test;

import java.util.HashSet;
import java.util.Set;

public class BracktesPrint {


	private Set<String> strings = new HashSet<>();
	public static void main(String[] args) {
		int total = 2;
		BracktesPrint bracktesPrint = new BracktesPrint();
		bracktesPrint.findParenthesis(total - 1, "()");
		bracktesPrint.print();

	}

	private void print() {
		for (String str : strings)
			System.out.println(str);
	}

	private void findParenthesis(int rem, String str) {
		if (rem  == 0) {
			strings.add(str);
			return;
		}

		// () str
		findParenthesis(rem - 1, "()" + str);
		// str ()
		findParenthesis(rem - 1, str + "()");

		// (str)
		findParenthesis(rem - 1, "(" + str + ")");

	}
}
