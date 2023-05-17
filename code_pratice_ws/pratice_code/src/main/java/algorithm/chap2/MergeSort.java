package algorithm.chap2;

import algorithm.UtilitiesClass;

public class MergeSort {

	public static void main(String[] s) {
		testMergeSort();
//		testMerge();
	}

	private static void testMergeSort() {
		int[] array;
		MergeSort mergeSort = new MergeSort();


		array = new int[] {9,8,7,6,5,4,3,2,1};
		mergeSort.sort1(array,0,array.length-1);
		UtilitiesClass.printArray(array);


		array = new int[] {9,8,7,6,5,4,3,2,1};
		mergeSort.sort2(array,0,array.length-1);
		UtilitiesClass.printArray(array);


		array = new int[] {1,2,3,4,5,6,7,8,9};
		mergeSort.sort1(array,0,array.length-1);
		UtilitiesClass.printArray(array);


		array = new int[50000000];
		UtilitiesClass.fillArray(array);
		long startTime = UtilitiesClass.startTime();
		mergeSort.sort1(array,0,array.length-1);
		UtilitiesClass.printTimeElapsed(startTime);


		array = new int[50000000];
		UtilitiesClass.fillArray(array);
		startTime = UtilitiesClass.startTime();
		mergeSort.sort2(array,0,array.length-1);
		UtilitiesClass.printTimeElapsed(startTime);


		array = new int[50000000];
		UtilitiesClass.fillArrayWithHalfSorted(array);
		startTime = UtilitiesClass.startTime();
		mergeSort.sort1(array,0,array.length-1);
		UtilitiesClass.printTimeElapsed(startTime);

		array = new int[50000000];
		UtilitiesClass.fillArrayWithHalfSorted(array);
		startTime = UtilitiesClass.startTime();
		mergeSort.sort2(array,0,array.length-1);
		UtilitiesClass.printTimeElapsed(startTime);


		array = new int[50000000];
		UtilitiesClass.fillArrayWithHalfSorted(array);
		startTime = UtilitiesClass.startTime();
		mergeSort.sort3(array,0,array.length-1);
		UtilitiesClass.printTimeElapsed(startTime);

		array = new int[50000000];
		UtilitiesClass.fillArrayWithHalfSorted(array);
		startTime = UtilitiesClass.startTime();
		mergeSort.sort4(array,0,array.length-1);
		UtilitiesClass.printTimeElapsed(startTime);

	}


	private static void testMerge() {
		MergeSort mergeSort = new MergeSort();

		int[] array;

		array = new int[] {1,1,1,1,2,4,5,7,2,3,5,6};
		mergeSort.merge(array,4,7,11);
		UtilitiesClass.printArray(array);

		array = new int[] {2,4,6,8,1,3,5};
		mergeSort.merge(array,0,3,6);
		UtilitiesClass.printArray(array);

		array = new int[] {1,1,1,1,2,4,5,7,2,3,5,6};
		mergeSort.merge3(array,4,7,11);
		UtilitiesClass.printArray(array);

		array = new int[] {2,4,6,8,1,3,5};
		mergeSort.merge4(array,0,3,6);
		UtilitiesClass.printArray(array);
	}


	public void sort1(int[] nums, int start, int end) {
		if (start==end)
			return;
		int firstEnd = (start + end)/2;
		sort1(nums,start,firstEnd);
		sort1(nums,firstEnd+1,end);
		merge(nums,start,firstEnd,end);

	}

	// merges two adjacent part of array.
	// firstStart is the start of 1st part,
	public void merge(int[] nums, int firstStart, int firstEnd, int secondEnd) {
		int lenOfFirst = firstEnd-firstStart+1;
		int lenOfSecond = secondEnd-firstEnd;
		//copy the two part into two separate array.
		// we store the extra element to know the end of the array.
		int[] left = new int[lenOfFirst+1];
		int[] right = new int[lenOfSecond+1];

		for (int i=0;i<lenOfFirst;i++)
			left[i] = nums[firstStart+i];
		left[lenOfFirst] = Integer.MAX_VALUE;

		for (int i=0;i<lenOfSecond;i++)
			right[i] = nums[firstEnd+1+i];
		right[lenOfSecond] = Integer.MAX_VALUE;

		// start merging the left and right into the original array.
		for(int k=firstStart,i=0,j=0;k<=secondEnd;k++) {
			if (left[i] <= right[j]) {
				nums[k] = left[i];
				i++;
			} else {
				nums[k] = right[j];
				j++;
			}
		}
	}

	public void sortOptimised(int[] nums) {
		sort2(nums, 0, nums.length-1);
	}

	// sort using optimised merge
	public void sort2(int[] nums, int start, int end) {
		if (start==end)
			return;
		int firstEnd = (start + end)/2;
		sort2(nums,start,firstEnd);
		sort2(nums,firstEnd+1,end);
		merge2(nums,start,firstEnd,end);

	}

	// optimised...if last element of left is smaller than first element of right, we append right to left.
	public void merge2(int[] nums, int firstStart, int firstEnd, int secondEnd) {
		if(firstEnd < secondEnd)
			if(nums[firstEnd] <= nums[firstEnd+1])
				return;

		merge(nums,firstStart, firstEnd, secondEnd);
	}


	// sort using optimised merge
	public void sort3(int[] nums, int start, int end) {
		if (start==end)
			return;
		int firstEnd = (start + end)/2;
		sort3(nums,start,firstEnd);
		sort3(nums,firstEnd+1,end);
		merge3(nums,start,firstEnd,end);

	}

	// merge3 variant...
	public void merge3(int[] nums, int firstStart, int firstEnd, int secondEnd) {
		// sanity check if merging is needed or not.
		if(firstEnd < secondEnd)
			if(nums[firstEnd] <= nums[firstEnd+1])
				return;

		int lenOfFirst = firstEnd - firstStart + 1;
		int lenOfSecond = secondEnd - firstEnd;

		// create two arrays left.
		int[] left = new int[lenOfFirst];
		int[] right = new int[lenOfSecond];

		for (int i=0;i<lenOfFirst;i++) {
			left[i] = nums[firstStart + i];
		}

		for (int i=0;i<lenOfSecond;i++) {
			right[i] = nums[firstEnd+1+i];
		}

		for (int k=firstStart,i=0,j=0;k<=secondEnd;k++) {
			// if elements are present in both the arrays
			if (i<lenOfFirst && j<lenOfSecond) {
				// copy the smaller element to actual array
				if (left[i] <= right[j]) {
					nums[k] = left[i];
					i++;
				} else {
					nums[k] = right[j];
					j++;
				}
			}
			// if elements in first are over
			else if (i>=lenOfFirst) {
				while (k<=secondEnd) {
					nums[k++] = right[j++];
				}
				break; // stop copying since we are done.
			} // last case will be when elements in second are over
			else {
				while (k<=secondEnd) {
					nums[k++] = left[i++];
				}
				break;
			}
		}
	}


	// sort using optimised merge
	public void sort4(int[] nums, int start, int end) {
		if (start==end)
			return;
		int firstEnd = (start + end)/2;
		sort4(nums,start,firstEnd);
		sort4(nums,firstEnd+1,end);
		merge4(nums,start,firstEnd,end);

	}

	// merge3 variant... different logic to find end of left or right array.
	public void merge4(int[] nums, int firstStart, int firstEnd, int secondEnd) {
		// sanity check if merging is needed or not.
		if(firstEnd < secondEnd)
			if(nums[firstEnd] <= nums[firstEnd+1])
				return;

		int lenOfFirst = firstEnd - firstStart + 1;
		int lenOfSecond = secondEnd - firstEnd;

		// create two arrays left.
		int[] left = new int[lenOfFirst];
		int[] right = new int[lenOfSecond];

		for (int i=0;i<lenOfFirst;i++) {
			left[i] = nums[firstStart + i];
		}

		for (int i=0;i<lenOfSecond;i++) {
			right[i] = nums[firstEnd+1+i];
		}

		for (int k=firstStart,i=0,j=0;k<=secondEnd;k++) {
			if (j>=lenOfSecond || i<lenOfFirst && left[i] <= right[j])
				nums[k] = left[i++];
			else
				nums[k] = right[j++];
		}
	}
}
