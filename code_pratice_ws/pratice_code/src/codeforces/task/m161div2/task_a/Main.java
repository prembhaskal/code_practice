package codeforces.task.m161div2.task_a;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);

		TaskA solution = new TaskA();
		solution.solve(in,writer);

		writer.close();
		in.close();


	}

}

class TaskA {

	public void solve(Scanner in, PrintWriter out) {

		int[][] matrix = new int[5][5];
		int val_i = 0;
		int val_j = 0;

		for (int i=0;i<5;i++) {
			for (int j=0;j<5;j++) {
				matrix[i][j] = in.nextInt();
				if (matrix[i][j] == 1) {
					val_i = i;
					val_j = j;
				}
			}
		}

		int movement = 0;
		movement+= Math.abs(val_i-2);
		movement+= Math.abs(val_j-2);

		out.println(movement);

	}

}

