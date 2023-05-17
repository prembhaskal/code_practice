package projecteuler.set1;

public class P2EvenFibonacci {

	public int findEvenSum(int range) {
		int a = 1;
		int b = 2;
		int sum = 2;
		int fib = 0;

		while (fib < range) {
			fib = a + b;
			a = b;
			b = fib;
			if ((fib&1)==0) {
				sum += fib;
			}
		}

		return sum;
	}
}
