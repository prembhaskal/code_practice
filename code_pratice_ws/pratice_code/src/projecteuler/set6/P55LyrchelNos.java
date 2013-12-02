package projecteuler.set6;

import java.util.Arrays;

public class P55LyrchelNos {

	int maxIterations = 50;

	public int totalLyrchelNosBelow10k() {

		int totalLychrelNos = 0;
		for (int i = 1; i < 10_000; i++) {
			int[] num = noToArray(i);
			boolean isPalin = false;
			for (int j = 0; j < maxIterations; j++) {
				num = reverseAndAdd(num);
				if (isPalindrome(num)) {
					isPalin = true;
					break;
				}
			}

			if (!isPalin)
				totalLychrelNos++;
		}

		return totalLychrelNos;
	}

	private int[] reverseAndAdd(int[] num) {
		int[] sum = new int[num.length + 1];

		int carry = 0;
		for (int i = 0; i < num.length; i++) {
			sum[i] = num[i] + num[num.length - 1 - i] + carry;
			carry = sum[i] / 10;
			sum[i] %= 10;
		}

		sum[sum.length - 1] = carry;

		return correctLength(sum);
	}

	private int[] correctLength(int[] a) {
		int length = a.length;
		int zeroes = 0;
		for (int i = length - 1; i >= 0; i--) {
			if (a[i] > 0)
				break;
			zeroes++;
		}

		return Arrays.copyOfRange(a, 0, length - zeroes);
	}

	private boolean isPalindrome(int[] num) {
		for (int i = 0; i < num.length / 2; i++) {
			if (num[i] != num[num.length - 1 -i])
				return false;
		}

		return true;
	}

	// at max we have for 4 digits. i.e. < 10_000
	private int[] noToArray(int num) {
		int[] array = new int[4];

		for (int i = 0; i < 4; i++) {
			array[i] = num % 10;
			num /= 10;
		}

		array  = correctLength(array);

		return array;
	}
}
