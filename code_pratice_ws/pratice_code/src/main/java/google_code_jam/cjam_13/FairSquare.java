package google_code_jam.cjam_13;

import java.io.PrintWriter;
import java.util.Scanner;

public class FairSquare {

	long[] numbersHCoded = new long[]{1L,4L,9L,121L,484L,676L,10201L,12321L,14641L,40804L,44944L,69696L,94249L,698896L,1002001L,
			1234321L,4008004L,5221225L,6948496L,100020001L,102030201L,104060401L,121242121L,123454321L,125686521L,400080004L,
			404090404L,522808225L,617323716L,942060249L,10000200001L,10221412201L,12102420121L,12345654321L,40000800004L,
			637832238736L,1000002000001L,1002003002001L,1004006004001L,1020304030201L,1022325232201L,1024348434201L,1086078706801L,
			1210024200121L,1212225222121L,1214428244121L,1230127210321L,1232346432321L,1234567654321L,1615108015161L,4000008000004L,
			4004009004004L,4051154511504L,5265533355625L,9420645460249L,100000020000001L,100220141022001L,102012040210201L,
			102234363432201L,121000242000121L,121242363242121L,123212464212321L,123456787654321L,123862676268321L,144678292876441L,
			165551171155561L,400000080000004L};

	long[] fairSquares = new long[]{1L,4L,9L,121L,484L,10201L,12321L,14641L,40804L,44944L,1002001L,1234321L,4008004L,100020001L,
			102030201L,104060401L,121242121L,123454321L,125686521L,400080004L,404090404L,10000200001L,10221412201L,12102420121L,
			12345654321L,40000800004L,1000002000001L,1002003002001L,1004006004001L,1020304030201L,1022325232201L,1024348434201L,
			1210024200121L,1212225222121L,1214428244121L,1232346432321L,1234567654321L,4000008000004L,4004009004004L,100000020000001L,
			100220141022001L,102012040210201L,102234363432201L,121000242000121L,121242363242121L,123212464212321L,123456787654321L,
			400000080000004L};

	public void solve(Scanner in, PrintWriter out) {
		int tests = in.nextInt();

		for (int testNo = 1; testNo<=tests;testNo++) {
			long lower = in.nextLong();
			long upper = in.nextLong();

//			int result = getNumberOfFairSquares(lower, upper);
			int result = getFairSquareBigRange(lower, upper);

			out.println("Case #" + testNo + ": " + result);
		}
	}

	private int getNumberOfFairSquares(int lower, int upper) {
		int count = 0;

		for (int i=0;i<numbersHCoded.length;i++) {
			if (numbersHCoded[i] >= lower && numbersHCoded[i] <= upper) {
				count++;
			}
		}

		return count;
	}

	private int getFairSquareBigRange(long lower, long upper) {
		int count = 0;

		for (int i=0;i<fairSquares.length;i++) {
			if (fairSquares[i]>=lower && fairSquares[i] <= upper) {
				count++;
			}
		}

		return count;
	}

	public void printFairSquare() {
		String str = "";

		for (int i=0;i<numbersHCoded.length;i++) {
			long num = numbersHCoded[i];

			if (isFairSquare(num))
				str = str + num + "L,";
		}

		System.out.println(str);
	}

	private boolean isFairSquare(long num) {
		long sqrt = getSqrt(num);
		return isPalindrome(sqrt);
	}

	private long getSqrt(long num) {
		long squareRoot = 1;

		for (long i=2;i<=num/i;i++) {
			int exp = 0;

			while (num%i==0) {
				num /= i;
				exp++;
			}

			for (int j=1;j<=exp/2;j++) {
				squareRoot = squareRoot * i;
			}
		}

		// no need to get the last factor as it will not be present;
		return squareRoot;
	}

	public boolean isPalindrome(long num) {
		long rev = 0;
		long copy = num;
		while (num > 0) {
			long digit = num % 10;
			num /= 10;
			rev = rev*10 + digit;
		}

		return rev == copy;
	}
}
