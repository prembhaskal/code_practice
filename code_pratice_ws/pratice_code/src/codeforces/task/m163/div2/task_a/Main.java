package codeforces.task.m163.div2.task_a;
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
		int nums = Integer.parseInt(in.nextLine());
		String stn = in.nextLine();

		char[] stones = stn.toCharArray();

		int count = 0;

		for (int i=1;i<nums;i++) {
			if (stones[i]==stones[i-1])
				count++;
		}

		out.println(count);
	}


}

