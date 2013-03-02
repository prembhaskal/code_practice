package codechef.march13.approx;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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

	String result;
	String intermediate;
	String repeat;

	int a = 103993;
	int b = 33102;//14;
	int b_minus_1 = 33101;

	int repeatnum;

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());

		formTheResultString();

//		FindFactors.printFactors(b);

		for(int i=0;i<tests;i++) {
			int num = Integer.parseInt(in.nextLine());

			if (num==0) {
				out.println("3");
			}
			else if(num==1) {
				out.println("3.1");
			} else {
				repeatnum = repeat.length();
				out.print("3.1");
				num--;
				int times = num/repeatnum;
				int remaining = num%repeatnum;

				for (int j=1;j<=times;j++) {
					out.print(repeat);
				}

				out.println(repeat.substring(0, remaining));
			}


		}

	}


	private void formTheResultString() {

		int[] rem = new int[b_minus_1];
		int[] quots = new int[b_minus_1];

		result = "3.";

		a = a%b;
		a = a*10;

		Map<String, Integer> divsr_div_map = new HashMap<String, Integer>();

		intermediate = "";
		int repeat_start = 0, repeat_end = 0;
		for (int i=0;i<rem.length;i++) {

			if (divsr_div_map.get(a+"_"+b)!=null) {
				repeat_end = i;
//				System.out.println("present value " + i);
				repeat_start = divsr_div_map.get(a+"_"+b);
//				System.out.println("repeating from " + divsr_div_map.get(a+"_"+b));
				break;
			}
			else {
				divsr_div_map.put(a+"_"+b,i);
			}
			quots[i]= a/b;
			rem[i] = a%b;
			a = rem[i] * 10;
			intermediate = intermediate + "" + quots[i];
		}

		repeat = intermediate.substring(repeat_start, repeat_end);

//		System.out.println("actual decimal is " + intermediate);

//		System.out.println("decimal is " + intermediate.substring(repeat_start, repeat_end));
	}


}

