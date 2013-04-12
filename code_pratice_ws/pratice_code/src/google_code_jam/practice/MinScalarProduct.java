package google_code_jam.practice;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * User: premkumar.bhaskal
 * Date: 4/12/13
 * Time: 12:17 PM
 */
public class MinScalarProduct {

	int N;
	int[] arr1;
	int[] arr2;

	public void solve(Scanner in, PrintWriter out) {

		int tests = in.nextInt();

		for (int testNo = 1;testNo<=tests;testNo++) {
			N = in.nextInt();
			arr1 = new int[N];
			arr2 = new int[N];

			for (int i=0;i<N;i++) {
				arr1[i] = in.nextInt();
			}

			for (int i=0;i<N;i++) {
				arr2[i] = in.nextInt();
			}

			long minProd = getMinScalarProduct();
			out.println("Case #" + testNo + ": " + minProd);
		}
	}

	private long getMinScalarProduct() {
		Arrays.sort(arr1);
		Arrays.sort(arr2);

		long minScalarProd = 0;

		for (int i=0;i<N;i++) {
			minScalarProd += ((long)arr1[i] * arr2[N-i-1]);
		}

		return minScalarProd;
	}

}
