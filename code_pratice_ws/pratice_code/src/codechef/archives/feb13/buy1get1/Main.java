package codechef.archives.feb13.buy1get1;
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
		int tests = Integer.parseInt(in.nextLine());

		for (int i=0;i<tests;i++) {
			String text = in.nextLine();
			char[] texta = text.toCharArray();

			int [] freq = new int[52];

			char ch;
			for (int j=0;j<texta.length;j++) {
				ch = texta[j];
				if (Character.isUpperCase(ch)) {
					freq[ch-65]++;
				} else {
					freq[26+ch-97]++;
				}
			}


			int count = 0;
			int num;
			for (int k=0;k<52;k++) {
				count += ((freq[k]/2) + (freq[k]%2));
			}

			out.println(count);
		}
	}


}

