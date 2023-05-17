package codeforces.task.m162.div2.task_d;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskD solution = new TaskD();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}
}

class TaskD {

	public void solve(Scanner in, PrintWriter out) {
		int total = Integer.parseInt(in.nextLine());

		int[] goodNos = new int[total];

		for (int i=0;i<total;i++)
			goodNos[i] = in.nextInt();
	}


	private int lengthOfSequence(int[] array, int length, int index) {
		int max = 0;

		int present = array[index];
		int next = array[index+1];

		int choose = -1;
		int notChoose = -1;
		if (isPartOfGoodSeq(present, next)) {
			choose = lengthOfSequence(array, length+1, index++);
		}

		             return 0;

	}


	private boolean isPartOfGoodSeq(int present, int next) {
		if (present >= next)
			return false;
		if (gcd(present,next) == 1)
			return false;

		return true;

	}

	private int gcd(int m, int n) {
		int divd, div, rem;
		if (m > n) {
			divd = m;
			div = n;
		} else {
			divd = n;
			div = m;
		}

		while (true) {
			rem = divd%div;
			if (rem==0)
				return div;
			divd = div;
			div = rem;
		}

	}

}
