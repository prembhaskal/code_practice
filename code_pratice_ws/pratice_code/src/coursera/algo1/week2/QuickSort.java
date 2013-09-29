package coursera.algo1.week2;

import java.io.PrintWriter;
import java.util.Scanner;

public class QuickSort {

	public void solve(Scanner in, PrintWriter out) {
		int[] nums = new int[10000];
		for (int i=0;i<10000;i++) {
			nums[i] = Integer.parseInt(in.nextLine());
		}

		out.println(quickSort(nums));
	}


	public long quickSort(int[] nums) {
		return quickSort(nums, 0,nums.length-1);
	}


	private long quickSort(int[] nums, int start, int end) {
		long count = 0;

		if (start >= end)
			return 0;

		int partitionIndex = partitionPivot1stElement(nums, start, end);
//		int partitionIndex = partitionPivotAsLastElement(nums, start, end);
//		int partitionIndex = partitionPivotAsMedianOfThree(nums, start, end);

		count += (end - start);

		// count length -1 comparisons for recursive call on each array.
		// NOTE: if array has start and end, then length = (end - start)+1;
//		count += (partitionIndex-1-start < 0 ? 0 : partitionIndex-1-start);
		count += quickSort(nums, start, partitionIndex-1);

//		count += (end-partitionIndex-1 < 0 ? 0: end-partitionIndex-1);
		count += quickSort(nums, partitionIndex+1,end);

		return count;
	}


	// return the final index of the pivot element, after partitioning
	// choose first element as the pivot
	private int partitionPivot1stElement(int[] nums, int start, int end) {
		if (start >= end) {
			return start;
		}

		// i is the boundary of elements less than pivot.....till nums[i-1] elements are less than pivot.
		// j is the boundary of elements not yet observed.
		// start .....< P .....i .... > P ..... j ..... elements yet to be observed.

		int i = start+1;
		int temp;
		for (int j=start+1;j<=end;j++) {
			if (nums[j] < nums[start]) {
				temp = nums[j];// swap with left most element > P.
				nums[j] = nums[i];
				nums[i] = temp;
				i++; // move the boundary less than the pivot.
			}
		}

		// swap the pivot element to bring it to proper position.
		temp = nums[i-1];
		nums[i-1] = nums[start];
		nums[start] = temp;

		return i-1;
	}

	// choose the last element as the pivot
	private int partitionPivotAsLastElement(int[] nums, int start, int end) {
		if (start >= end)
			return start;

		int temp;
		// last element as pivot, swap last and first element.
		temp = nums[end];
		nums[end] = nums[start];
		nums[start] = temp;

		// remaining else will be same as first element.
		return partitionPivot1stElement(nums, start, end);
	}

	// pivot is median of first, median and last element,
	// if we got only 2 elements in the array, it does not matter since no. of comparisons in either case will be 1
	private int partitionPivotAsMedianOfThree(int[] nums, int start, int end) {
		if (start >= end)
			return start;

		int median = nums[(start+end)/2];
		int medianIndex;

		int min = Math.min(nums[start],Math.min(median, nums[end]));
		int max = Math.max(nums[start],Math.max(median, nums[end]));

		if (median>min && median<max)
			medianIndex = (start+end)/2;
		else if (nums[start]>min && nums[start]<max)
			medianIndex = start;
		else
			medianIndex = end;

		// swap median element with first element
		int temp = nums[medianIndex];
		nums[medianIndex] = nums[start];
		nums[start] = temp;

		return partitionPivot1stElement(nums, start, end);

	}
}
