package projecteuler.set6;

import java.util.Arrays;

public class P56PowerfulDigitSum {

	public int getMaxSum() {
		int maxSum = 0;

		for (int i = 2; i < 100; i++) {
			int[] num = numToArray(i);
			int[] power = new int[]{1};
			for (int j = 2; j < 100; j++) {
				power = multiply(power, num);
				int sum = getSum(power);
				maxSum = Math.max(sum, maxSum);
			}
		}

		return maxSum;
	}

	private int getSum(int[] a) {
		int sum = 0;
		for (int i = 0; i < a.length; i++)
			sum += a[i];

		return sum;
	}


	private int[] numToArray(int num) {
		int[] arr = new int[2];

		for (int i = 0; i < 2; i++) {
			arr[i] = num % 10;
			num /= 10;
		}

		return arr;
	}

	private int[] power(int[] a, int pow) {
		int[] power = new int[]{1};

		while (pow > 0) {
			if ((pow & 1) == 1) {
				power = multiply(power, a);
			}

			a = multiply(a, a);
			pow /= 2;
		}

		return power;
	}

	private int[] multiply(int[] a, int[] b) {
		int[] product = new int[a.length + b.length + 1];

		for (int i = 0; i < b.length; i++) {
			int digit = b[i];
			int[] intermediate = new int[product.length];
			int carry = 0;
			for (int j = 0; j < a.length; j++) {
				int prod = a[j] * digit + carry;
				carry = prod / 10;
				intermediate[i + j] = prod % 10;
			}
			intermediate[i + a.length] = carry;
			add(product, intermediate);
		}

		product = correctLength(product);

		return product;
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

	// does a[] = a[] + b[]; // a should be big enough
	private void add(int[] a, int[] b) {
		int carry = 0;
		for (int i = 0; i < b.length; i++) {
			a[i] = a[i] + b[i] + carry;
			carry = a[i] / 10;
			a[i] %= 10;
		}

		if (carry > 0)
			a[b.length] = carry;
	}
}
