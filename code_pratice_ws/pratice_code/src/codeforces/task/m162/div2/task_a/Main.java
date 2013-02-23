package codeforces.task.m162.div2.task_a;
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
		String stones = in.nextLine();
		String ins = in.nextLine();

		char[] stonesArray = stones.toCharArray();

		char[] insArray = ins.toCharArray();
		int stonenumber = 0;

		int i,j;
		for (i=0,j=0;j<insArray.length && i<stonesArray.length;j++) {
			if (stonesArray[i]==insArray[j]) {
				i++;
			}
		}

		if (i==stonesArray.length)
			out.println(i);
		else
			out.println(i+1);
	}

}

