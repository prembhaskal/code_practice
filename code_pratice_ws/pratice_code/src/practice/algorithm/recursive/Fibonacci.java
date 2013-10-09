package practice.algorithm.recursive;

public class Fibonacci {


	public static void main(String[] s){
		Fibonacci fibonacci = new Fibonacci();
		int n = 46;// CAUTION DO NOT GIVE VALUE ABOVE 50, the program may not come out at all.
		fibonacci.profileFibonacciUsingDynamicProgramming(n);
		fibonacci.profileFibonacciNormalRecursive(n);

	}

	private void profileFibonacciUsingDynamicProgramming(int n) {
		long oldTime = System.nanoTime();

		long fibonNumer = getNthfibonacciNumberUsingDynamicProgramming(n);

		System.out.println("The " + n + " th fibonacci number is " + fibonNumer);

		long newTime = System.nanoTime();

		System.out.println("Dynamic Programming --> time taken to calculate is " + (newTime-oldTime) + " nano secs");
	}

	private void profileFibonacciNormalRecursive(int n) {
		long oldTime = System.nanoTime();

		long fibonNumer = getFibonacciNormalRecursive(n);

		System.out.println("The " + n + " th fibonacci number is " + fibonNumer);

		long newTime = System.nanoTime();

		System.out.println("Normal Programming --> time taken to calculate is " + (newTime-oldTime) + " nano secs\n" +
				"OR --> " + (newTime-oldTime)/1000000 + " milli secs");
	}


	// this method makes use of already calculated values.
	private long getNthfibonacciNumberUsingDynamicProgramming(int n) {
		if (n==0)
			return 0;
		if (n==1)
			return 1;

		// array to store the found values
		long  [] d = new long[n];
		// we are sure of not hitting array index out of bound exception
		d[0] = 0;
		d[1] = 1;
		for(int i=2;i<n;i++)
			d[i] = -1;

		return fibon(n,d);
	}


	private long fibon(int n, long [] d) {
		if (d[n-1] < 0)
			d[n-1] = fibon(n-1, d);
		return d[n-2] + d[n-1];
	}

	private long getFibonacciNormalRecursive(int n) {
		if (n==0)
			return 0;
		if (n==1)
			return 1;
		return getFibonacciNormalRecursive(n-1) + getFibonacciNormalRecursive(n-2);
	}

}
