package codechef.archives.cieldist;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] s) {
		Scanner in = new Scanner(System.in);
		PrintWriter writer = new PrintWriter(System.out);
		new Main().solve(in, writer);
		writer.close();
		in.close();
	}


	public void solve(Scanner in, PrintWriter out) {
		int testCases = Integer.parseInt(in.nextLine());

		for (int i=0;i<testCases;i++) {
			int Ds = in.nextInt();
			int Dt = in.nextInt();
			int D = in.nextInt();

			if (Ds >=(D+Dt))
				out.println(Ds-D-Dt);
			else if (Dt >=(D+Ds))
				out.println(Dt-D-Ds);
			else if(Ds+Dt<D)
				out.println(D-Dt-Ds);
			else
				out.println(0.00);
		}
	}
}
