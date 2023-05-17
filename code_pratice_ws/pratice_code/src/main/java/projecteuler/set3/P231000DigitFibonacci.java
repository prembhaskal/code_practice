package projecteuler.set3;

public class P231000DigitFibonacci {

	public int findFibonacciOfDigit(int digits) {
		int[] a = new int[digits];
		int[] b = new int[digits];
		int[] fib = new int[digits];

		// initialize
		a[0] = 1;
		b[0] = 1;

		int term = 3;
		boolean termFound = false;

		while (!termFound) {
			fib = addBigNumbers(a,b);
			a = b;
			b = fib;
			if(fib[digits-1] > 0)
				termFound = true;

			term++;
		}

		return term-1;
	}

	// last digit is lost,if sum exceeds range
	public int[] addBigNumbers(int[] a, int[] b) {
		int[] result = new int[a.length];

		int rem=0;
		for (int i=0;i<a.length;i++) {
			int res = a[i] + b[i] + rem;
			rem = res/10;
			res = res%10;
			result[i] = res;
		}

		return result;
	}
}
