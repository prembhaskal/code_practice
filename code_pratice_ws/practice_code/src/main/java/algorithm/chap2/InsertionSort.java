package algorithm.chap2;

import algorithm.UtilitiesClass;

public class InsertionSort {

	public static void main(String[] s) {

		testRecursiveSort();
	}

	private static void testNormalSort() {
		InsertionSort insertionSort = new InsertionSort();
		int [] array;

		array = new int[] {1,3,4,5,6,2};

		insertionSort.sort1(array);
		printArray(array);

		array = new int[] {6,5,4,3,2,1};

		insertionSort.sort1(array);
		printArray(array);

		array = new int[] {1,1,1,1,1,1};

		insertionSort.sort1(array);
		printArray(array);

		array = new int[100000];
		fillArray(array);
		long startTime = UtilitiesClass.startTime();
		insertionSort.sort1(array);
		UtilitiesClass.printTimeElapsed(startTime);


		array = new int[100000];
		UtilitiesClass.fillArrayWithHalfSorted(array);
		startTime = UtilitiesClass.startTime();
		insertionSort.sort1(array);
		UtilitiesClass.printTimeElapsed(startTime);
	}

	private static void testRecursiveSort() {
		InsertionSort sort = new InsertionSort();
		int[] array;

		array = new int[]{6,5,4,3,2,1};
		sort.sortRecursive(array,array.length-1);
		printArray(array);

		array = new int[]{1,4,2,5,3,3};
		sort.sortRecursive(array,array.length-1);
		printArray(array);
	}

	// insertion sort1.....
	// choose an element, insert into a proper place by comparing.
	// choose the next element.
	//[1,3,4,5,6,2] ...assume chosen element is 2
	// INCREASING ORDER
	public void sort1(int[] nums) {
		long noOfIterations = 0;
		int length = nums.length;

		for (int i=1;i<length;i++) {
			int chosenElement = nums[i];
			// compare and replace
			int j;
			for (j=i-1;j>=0;j--) {
				noOfIterations++;
				// do until we find smaller element
				if (nums[j] <= chosenElement)
					break;
				// else keep shifting down, making place for chosenElement to keep
				nums[j+1] = nums[j];
			}

			nums[j+1] = chosenElement;
		}

		System.out.println("No of Iterations " + noOfIterations);
	}


	public void sortRecursive(int[] nums, int elementIndex) {
		if (elementIndex < 1)
			return;
		// sort the A[n-1] elements
		sortRecursive(nums,elementIndex-1);
		int chosenElement = nums[elementIndex];
		int j;
		// insert A[n] into the sorted A[n-1] elements.
		for (j=elementIndex-1;j>=0;j--) {
			if (nums[j] <= chosenElement)
				break;
			nums[j+1] = nums[j];
		}
		nums[j+1] = chosenElement;
	}

	// shift array from --start-- to --end--, 1 block below to accomodate a new
	// value are --start--
	public void shiftArrays(int[] nums, int start, int end) {

		if (end > nums.length-2)
			return;// there should exist a place to shift down.

		while (end >= start) {
			nums[end+1] = nums[end];
			end--;
		}
	}


	public static void printArray(int[] nums) {
		for(int i : nums) {
			System.out.print(i);
		}
		System.out.println();
	}


	public static void fillArray(int[] nums) {
		int start = nums.length;
		for (int i=0;i<nums.length;i++) {
			nums[i] = start--;
		}
	}
}
