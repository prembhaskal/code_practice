package projecteuler.set5;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PanDigitalPrime {

	public boolean print = false;
	public int getLargestPanDigitPrime() {
		int start = 1234567;
		printPermutation(start);
		return maxPrime;
	}

	private int maxPrime = 2;

	private void registerPrime(int num) {
		if (isPrime(num)) {
			maxPrime = Math.max(num, maxPrime);
		}
	}

	private void registerPrime(int[] arr) {
		int actualNum = getNumFromArray(arr);
		registerPrime(actualNum);
	}

	private int getNumFromArray(int[] arr) {
		int num = 0;

		for (int i : arr) {
			num = num*10 + i;
		}

		return num;
	}

	public void printPermutation(int startNumber) {
		int[] nums = getArrayRepForNumber(startNumber);

		printPermutation(nums, 0, nums.length-1);
	}

	private int[] getArrayRepForNumber(int num) {
		String str = num + "";
		char[] chars = str.toCharArray();

		int[] nums = new int[chars.length];
		int i = 0;
		for (char ch : chars) {
			nums[i++] = Integer.parseInt(ch + "");
		}

		return nums;
	}

	private void printPermutation(int[] num, int start, int end) {
		int digitsToRotate = end - start + 1;

		if (digitsToRotate == 1) {
			printArray(num);
			registerPrime(num);
		}


		for (int i=0;i<digitsToRotate;i++) {
			// first rotate, then print, so that original array is back at completion of loop.
			leftRotate(num, digitsToRotate);
			printPermutation(num, start+1, end);
		}
	}

	// leftRotate only the given last digits.
	public void leftRotate(int[] num, int digits) {
		int temp;
		int start = num.length - digits;
		int end = num.length - 1;

		temp = num[start];

		for (int i=start;i<end;i++) {
			num[i] = num[i+1];
		}

		num[end] = temp;
	}

	private void printArray(int[] num) {

		if (!print)
			return;

		for(int i : num) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public boolean isPrime(int num) {
		if (!isPrimeSanity(num))
			return false;
		BigInteger bigInteger = BigInteger.valueOf(num);

		boolean isPrime = bigInteger.isProbablePrime(25);

		return isPrime;
	}

	private Set<Integer> evenDigits = new HashSet<>(Arrays.asList(2,4,6,8));

	private boolean isPrimeSanity(int num) {

		if (num==2)
			return true;

		int oneDigit = num%10;

		if (evenDigits.contains(oneDigit))
			return false;

		return true;
	}
}

