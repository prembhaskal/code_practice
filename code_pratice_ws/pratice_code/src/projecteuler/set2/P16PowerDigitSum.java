package projecteuler.set2;

public class P16PowerDigitSum {

	int[] sum;

	public void getSumOfDigits() {
		sum = new int[303];
		sum[0] = 1;

		for (int i=1;i<=1000;i++) {
			sum = doubleTheNumber(sum);
//			System.out.println("2 raise to " + i + " is ");
//			printNumber(sum);
//			System.out.println("");
		}

		// add digits
		int digitsum = 0;

		for (int i=0;i<sum.length;i++) {
			digitsum += sum[i];
		}

		System.out.println("sum of digits is " + digitsum);
	}


	private int[] doubleTheNumber(int[] a) {
		int[] sum = new int[a.length];
		int carry = 0;
		for (int i=0;i<a.length;i++) {
			int rem = a[i] + a[i] + carry;
			sum[i] = rem % 10;
			carry = rem/10;
		}

		return sum;
	}

	// ones digits at 0 index
	private void printNumber(int[] num) {
		for (int i=num.length-1;i>=0;i--) {
			System.out.print(num[i]);
		}
	}
}
