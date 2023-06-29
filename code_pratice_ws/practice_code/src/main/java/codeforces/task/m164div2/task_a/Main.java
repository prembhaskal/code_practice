package codeforces.task.m164div2.task_a;
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
		int teams = Integer.parseInt(in.nextLine());

		int[] homeDress = new int[teams];
		int[] guestDress = new int[teams];

		for (int i=0;i<teams;i++) {
			homeDress[i] = in.nextInt();
			guestDress[i] = in.nextInt();
		}

		int match = 0;

		for(int i=0;i<teams;i++) {
			for (int j=0;j<teams;j++) {
				if (homeDress[i]==guestDress[j])
					match++;
			}
		}

		out.println(match);
	}


}

