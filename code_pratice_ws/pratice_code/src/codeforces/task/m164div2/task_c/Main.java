package codeforces.task.m164div2.task_c;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class Main {

	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskC solution = new TaskC();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}
}

class TaskC {

	public void solve(Scanner in, PrintWriter out) {

		int n = in.nextInt();
		int k = in.nextInt();


		n = Math.min(n,k);

		int[] val = new int[n+1];

		out.println(n+1);

		for (int i=0;i<n+1;i++) {
			out.println(i + " " + (n-i));
		}
	}


}
