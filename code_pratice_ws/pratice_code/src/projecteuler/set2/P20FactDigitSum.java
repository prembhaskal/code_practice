package projecteuler.set2;

import algorithm.UtilitiesClass;

public class P20FactDigitSum {

	public void findFactDigitSum(int num) {
		int fact = 1;
		int[] factArray = numberToArray(fact);

		for (int i=1;i<=num;i++) {
			int[]numberArray = numberToArray(i);
			factArray = multiplyTwoArrays(numberArray, factArray);
		}

		UtilitiesClass.printArrayNoSpaces(factArray);

		int digitSum = 0;

		for (int i=0;i<factArray.length;i++) {
			digitSum += factArray[i];
		}

		System.out.println("sum of digits is " + digitSum);
	}

	public int[] numberToArray(int num) {

		int size = 0;
		int copy = num;

		while (copy > 0) {
			copy /= 10;
			size++;
		}

		int[] array = new int[size];

		for (int i=array.length-1;i>=0;i--) {
			array[i] = num%10;
			num /= 10;
		}

		return array;
	}


	/// index 0 has the MSB.
	public int[] multiplyTwoArrays(int[] a, int[] b) {
		int [] result = new int[a.length + b.length];

		for (int i=a.length-1, k1 = result.length-1;i>=0;i--,k1--) {
			int k = k1;
			int carry = 0;
			for (int j=b.length-1;j>=0;j--, k--) {
				int prod = a[i] * b[j] + result[k] + carry;
				carry = prod/10;
				result[k] = prod%10;
			}
			result[k] = carry;
		}

		return result;
	}
}
