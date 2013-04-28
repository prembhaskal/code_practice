package codechef.practice.product_divisors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	public static void main(String[] s) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter writer = new PrintWriter(System.out);

			TaskA solution = new TaskA();
			solution.solve(in,writer);

			writer.close();

			in.close();
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

}

// product of divisors..... here is the http://ww2.codechef.com/problems/D1/
class TaskA {

	int MOD = 10000;

	boolean isMoreThanMod;

	List<Integer> primeList = new ArrayList<Integer>();

	static Map<Integer, Integer> squareAndRootMap = new HashMap<Integer, Integer>();

	static Map<Integer, String> numVsDivisor = new HashMap<Integer, String>();

	static boolean isInitialized = false;

	static int[] primenos = new int[] {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,
			101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,
			211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,
			307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,
			401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,
			503,509,521,523,541,547,557,563,569,571,577,587,593,599,
			601,607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,701,709};

	public void solve(BufferedReader in, PrintWriter out) throws IOException {

		init();
		int tests = Integer.parseInt(in.readLine());

		int[] nos = new int[tests];

		for (int i=0;i<tests;i++) {
			nos[i] = Integer.parseInt(in.readLine());
		}

		for (int i=0;i<tests;i++) {
			String digits = getDivisorProd4Digits(nos[i]);
			out.println(digits);
		}

	}


	private String getDivisorProd4Digits(int num) {
		String fourDigits = "";
		isMoreThanMod = false;

		long divisorsProd = 1;

		int numberOfDivisors = getNumberOfDivisors(num);

		int root = (int) Math.sqrt(num);

		// find why the commented logic works??
//		if (numberOfDivisors%2!=0) {
//			divisorsProd = power(root, numberOfDivisors);
//		} else {
//			divisorsProd = power(num, numberOfDivisors/2);
//		}
//
		Integer squareRoot = squareAndRootMap.get(num);
		if (squareRoot!=null) {
			divisorsProd = (power(num, (numberOfDivisors-1)/2) * squareRoot);
			if (divisorsProd >= MOD)
				isMoreThanMod = true;
			divisorsProd = divisorsProd%MOD;
		} else {
			divisorsProd = power(num, numberOfDivisors/2);
		}

		fourDigits = "" + divisorsProd;

		if (!isMoreThanMod) {
			return fourDigits;
		}
		else {
			fourDigits = String.valueOf(divisorsProd + MOD);
			fourDigits = fourDigits.substring(1);
			return fourDigits;
		}

	}

	private int getNumberOfDivisors(int num) {
		int numberOfDivisors = 1;

		for (int i=0;i<primenos.length;i++) {
			int prime = primenos[i];

			if (prime > num/prime)
				break;

			if (num%prime==0) {
				int k=1;
				while (num%prime==0) {
					num /= prime;
					k++;
				}

				numberOfDivisors = numberOfDivisors * k;
			}
		}

		if (num > 1) {
			numberOfDivisors = numberOfDivisors * 2;
		}

		// 1 and the number itself should not be considered.
		numberOfDivisors = numberOfDivisors - 2;

		return numberOfDivisors;
	}


	private int power(long num, long pow) {
		long power = 1;

		while (pow > 0) {
			if (pow%2==1){
				power = (power * num);
				if (power >= MOD) {
					isMoreThanMod = true;
					power = power % MOD;
				}
			}

			num = (num * num);
			if (num >= MOD && pow > 1) {
				isMoreThanMod = true;
				num = num % MOD;
			}
			pow /= 2;
		}

		return (int) power;
	}


	private void init() {
		if (isInitialized)
			return;

		isInitialized = true;

		// get squares and roots
		int square = 1;
		for (int i=1;square<=500000;i++) {
			square = i*i;
			squareAndRootMap.put(square, i);
		}

	}



}

