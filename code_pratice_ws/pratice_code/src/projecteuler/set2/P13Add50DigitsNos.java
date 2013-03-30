package projecteuler.set2;

import java.io.PrintWriter;
import java.util.Scanner;

public class P13Add50DigitsNos {

	int nums[][];

	public void addAllNumbers(Scanner in, PrintWriter out) {
		readNos(in);

		int[] sum = new int[100];

		for (int i=0;i<100;i++) {
			sum = add(nums[i], sum);
		}

		printSum(out,sum);
	}

	private void printSum(PrintWriter out, int[] sum) {
		for (int i=0;i<sum.length;i++)
			out.print(sum[i]);

		out.println("first ten digits are ");

		for (int i=sum.length-1;i>sum.length-15;i--)
			out.print(sum[i]);
	}


	private int[] add(int[] a, int[] b) {
		int[] sum = new int[55];

		int carry = 0;
		for (int i=0;i<a.length;i++) {
			sum[i] = a[i] + b[i] + carry;
			carry = sum[i]/10;
			sum[i] = sum[i]%10;
		}

		return sum;
	}

	// read numbers so that 0 index has the ones digit of the number
	private void readNos(Scanner in) {
		nums = new int[100][55];

		String number;
		char[] noAsChars;
		int index = 0;
		while (in.hasNext()) {
			number = in.nextLine();
			noAsChars = number.toCharArray();
			for (int i=0;i<50;i++) {
				nums[index][i] = Integer.parseInt(noAsChars[49-i] + "");
			}

			index++;
		}


	}
}
