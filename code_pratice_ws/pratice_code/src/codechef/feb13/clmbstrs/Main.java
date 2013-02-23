package codechef.feb13.clmbstrs;
import java.io.PrintWriter;
import java.util.Arrays;
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

	int MOD = 1000000007;
	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.nextLine());


		// calculating for max here it fills up the array and we dont need to calculate anymore.
		int[] waysDP = new int[1000000+1];
		waysDP[1]=1;
		waysDP[2]=2;
		fibonacci(waysDP,1000000);

		for (int i=0;i<tests;i++) {
			int num = in.nextInt();
			int onesGuess = in.nextInt();

			if (onesGuess > 31) {
				out.println("INCORRECT");
				return;
			}

			int ways = waysDP[num];

			int onesActual = getNoOfOnes(ways);

			if (onesGuess==onesActual) {
				out.println("CORRECT");
			} else {
				out.println("INCORRECT");
			}
		}

	}

	private int getNoOfOnes(int n) {
		return Integer.bitCount(n);
	}

	// refer to http://www.careercup.com/question?id=3590768
	// or search google for staircase problem one or two
	// f(n) = f(n-1) + f(n-2) , reach step n from n-1th step or n-2th step.
	private int getWays(int[] waysDp, int steps) {
		int ways = 0;
		ways = fibonacci(waysDp,steps);
		return ways;
	}

	private int fibonacci(int[] waysDp, int n) {
		if (n==1)
			return 1;
		if (n==2)
			return 2;

		long num_1 = 2;//denotes n-1
		long num_2 = 1;//denotes n-2
		long num = 0;
		for (int i=2;i<n;i++) {
			num = (num_1 + num_2)%MOD;
			num_2 = num_1;
			num_1 = num;

			waysDp[i+1] = (int)num;
		}

		return (int)num;
	}



}

