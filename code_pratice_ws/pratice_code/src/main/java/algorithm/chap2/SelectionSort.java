package algorithm.chap2;

import algorithm.UtilitiesClass;

public class SelectionSort {

	public static void main(String[] s) {
		SelectionSort selectionSort = new SelectionSort();

		int[] array;

		array = new int[]{6,5,4,3,2,1};
		selectionSort.sort1(array);
		UtilitiesClass.printArray(array);


		array = new int[]{1,3,2,5,4,7,6};
		selectionSort.sort1(array);
		UtilitiesClass.printArray(array);


		array = new int[]{1,2,3,4,5,6};
		selectionSort.sort1(array);
		UtilitiesClass.printArray(array);

		array = new int[100000];
		UtilitiesClass.fillArray(array);
		long startTime = UtilitiesClass.startTime();
		selectionSort.sort1(array);
		UtilitiesClass.printTimeElapsed(startTime);


		array = new int[100000];
		UtilitiesClass.fillArrayWithHalfSorted(array);
		startTime = UtilitiesClass.startTime();
		selectionSort.sort1(array);
		UtilitiesClass.printTimeElapsed(startTime);
	}



	// selection sort...........INCREASING ORDER
	// find the smallest element, exchange with the a[1]
	// find the 2nd smallest element, exchange with a[2]....so on.
	// [6,5,4,3,2,1]
	public void sort1(int[] nums) {
		long iterations = 0;
		int temp;
		for (int i=0;i<nums.length-1;i++) {
			for (int j=i+1;j<nums.length;j++) {
				iterations++;
				// swap if smaller
				if (nums[i] > nums[j]) {
					temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}

		System.out.println("no of iterations " + iterations);
	}
}
