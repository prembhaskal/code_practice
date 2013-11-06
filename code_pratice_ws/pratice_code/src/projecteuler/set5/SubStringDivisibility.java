package projecteuler.set5;

import algorithm.UtilitiesClass;

public class SubStringDivisibility {

	private long sum;

	int totalnums = 0;

	public long getSumOfThatNos() {
		sum = 0;
		int[] num = new int[]{1,2,3,4,5,6,7,8,9,0};
		findPermutations(num, 10);
		System.out.println("total such numbers are " + totalnums);
		return sum;
	}


	private void findPermutations(int[] num, int digitsToRotate) {
		if (digitsToRotate == 1) {
			doStuff(num);
			return;
		}

		for (int i = 0; i < digitsToRotate; i++) {
			leftRotate(num, digitsToRotate);
			findPermutations(num, digitsToRotate - 1);
		}
	}

	private void leftRotate(int[] num, int digitsToRotate) {
		int start = num.length - digitsToRotate;
		int temp = num[start];
		for (int i = start; i < num.length - 1; i++) {
			num[i] = num[i+1];
		}
		num[num.length - 1] = temp;
	}

	private void doStuff(int[] num) {

		if (num[0] == 0) // since only 10 digit number
			return;

		// check for each criteria
		int prod;

		// check for div by 2
		prod = getNum(num, 1);
		if (prod % 2 != 0)
			return;

		prod = getNum(num, 2);
		if (prod % 3 != 0)
			return;

		prod = getNum(num, 3);
		if (prod % 5 != 0)
			return;

		prod = getNum(num, 4);
		if (prod % 7 != 0)
			return;

		prod = getNum(num, 5);
		if (prod % 11 != 0)
			return;

		prod = getNum(num, 6);
		if (prod % 13 != 0)
			return;

		prod = getNum(num, 7);
		if (prod % 17 != 0)
			return;

		sum += getActualNum(num);
		totalnums++;
	}

	private long getActualNum(int[] num) {
		String str = "";
		for (int i = 0; i < num.length; i++) {
			str += num[i];
		}

		return Long.parseLong(str);
	}

	private int getNum(int[] num, int start) {
		int prod = 0;
		prod = num[start++];
		prod = num[start++] + prod*10;
		prod = num[start] + prod*10;
		return prod;
	}

	public long getSumFaster() {
		sum = 0;
		int[] num = new int[]{1,2,3,4,5,6,7,8,9,0};
		findPermutations(num, 10);

		return sum;
	}

	private void permutationWithCheck(int[] nums, int digitsToRotate) {
		if (digitsToRotate > 1) {
			if (!preCheck(nums, digitsToRotate))
				return;
		}

		if (digitsToRotate == 1) {
			doStuff(nums);
			return;
		}

		for (int i = 0; i < digitsToRotate; i++) {
			leftRotate(nums,  digitsToRotate);
			permutationWithCheck(nums, digitsToRotate - 1);
		}
	}

	private boolean preCheck(int[] num, int digitsToRotate) {
		if (digitsToRotate > 6)
			return true;
		int prod;

		if (digitsToRotate == 6) {
			// check for div by 2
			prod = getNum(num, 1);
			if (prod % 2 != 0)
				return false;
		}
		else if (digitsToRotate == 5) {
			prod = getNum(num, 2);
			if (prod % 3 != 0)
				return false;
		}
		else if (digitsToRotate == 4) {
			prod = getNum(num, 3);
			if (prod % 5 != 0)
				return false;
		}
		else if (digitsToRotate == 3) {
			prod = getNum(num, 4);
			if (prod % 7 != 0)
				return false;
		}
		else if (digitsToRotate == 2) {
			prod = getNum(num, 5);
			if (prod % 11 != 0)
				return false;
		}

		return true;
	}


}
