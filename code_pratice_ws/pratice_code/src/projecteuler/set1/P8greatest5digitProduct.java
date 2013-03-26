package projecteuler.set1;

import java.io.PrintWriter;
import java.util.Scanner;

public class P8greatest5digitProduct {

	int[] number;

	public int findGreatestProductof5ConsDigit(Scanner in, int digits) {
		readNumber(in, digits);

		int maxProd = -1;

		for (int i = 4; i<number.length;i++) {
			maxProd = Math.max(getProduct(i-4,i), maxProd);
		}

		return maxProd;
	}

	private int getProduct(int startIndex, int endIndex) {
		int prod = 1;

		for (int i=startIndex;i<=endIndex;i++) {
			prod *= number[i];
		}

		return prod;
	}

	private void readNumber(Scanner in, int digits) {
		number = new int[digits];

		int i = 0;
		while(in.hasNext()) {
			String str = in.nextLine();
			char[] chars = str.toCharArray();

			for (char c : chars) {
				number[i++] = Integer.parseInt(c + "");
			}
		}
	}
}
