package codechef.march13.palindrome;

import com.sun.media.sound.MidiOutDeviceProvider;
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

	int MOD = 1000000007;

	public void solve(Scanner in, PrintWriter out) {
		int tests = Integer.parseInt(in.next());

		for (int i=0;i<tests;i++) {
			int n = Integer.parseInt(in.next());

			out.println(findPermutation(n));
		}

	}

	// totat premutation = 2*26 * (26^(ceil[n/2] -1 )/25; when n is even.
	// for each even and odd combination, the result will be same, hence the 2
	// so if n is odd, reduce by 1 i.e 26^[ceil[n/2]]
	private long findPermutation(int n) {
		int k = (n+1)/2;

		long raise26ToK = pow(26, k);

		int modInv25 = pow(25, MOD-2);

		long permute = (52 * (raise26ToK - 1))%MOD;
		permute = (permute * modInv25)%MOD;

		if (n%2==1) {
			permute = (permute + MOD - raise26ToK)%MOD;
		}

		return permute;
	}



	private int pow(long num, long pow) {
		long result = 1;

		while (pow > 0) {
			if (pow %2==1) {
				result = (result * num)%MOD;
			}

			num = (num * num) %MOD;

			pow /= 2;
		}

		return (int) result;
	}

}

