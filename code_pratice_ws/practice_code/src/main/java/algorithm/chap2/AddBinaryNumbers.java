package algorithm.chap2;

public class AddBinaryNumbers {


	public static void main(String[] s) {
		AddBinaryNumbers test = new AddBinaryNumbers();

		int[] a;
		int[] b;
		// add 2 + 3 = 5
		a = new int[]{0,1,0};
		b = new int[]{0,1,1};
		printBinaryNumbers(test.addBinaryNumbers(a, b));

		// add 7+5=12
		a = new int[]{1,1,1};
		b = new int[]{1,0,1};
		printBinaryNumbers(test.addBinaryNumbers(a,b));

		// add 2 + 5 = 7
		a = new int[]{0,1,0};
		b = new int[]{1,0,1};
		printBinaryNumbers(test.addBinaryNumbers(a,b));
	}


	public int[] addBinaryNumbers(int [] a, int [] b) {
		int length = a.length;
		int[] result = new int[length + 1];
		int rem = 0;
		int val;
		for (int i=length-1;i>=0;i--) {
			val = a[i] + b[i] + rem;
			rem = (val & 10)>>1;
			result[i+1] = val & 01;
		}

		result[0] = rem;
		return result;
	}


	public static void printBinaryNumbers(int [] nums) {
		for (int i=0;i<nums.length;i++) {
			System.out.print(nums[i]);
		}
		System.out.println();
	}
}
