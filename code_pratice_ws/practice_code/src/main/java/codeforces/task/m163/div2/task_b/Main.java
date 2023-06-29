package codeforces.task.m163.div2.task_b;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskB solution = new TaskB();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}
}

class TaskB {

	public void solve(Scanner in, PrintWriter out) {
		int n = in.nextInt();
		int k =in.nextInt();

		String queueStr = in.next();
		char[] queue = queueStr.toCharArray();
		int temp;
		for (int i=0;i<k;i++) {
			for(int j=0;j<n-1;j++) {
				if (queue[j]=='B' && queue[j+1]=='G') {
					queue[j]='G';
					queue[j+1]='B';
					j++;
				}
			}
		}

		for (int i=0;i<n;i++)
			out.print(queue[i]);
	}

}
