package coursera.algo.week5;

import java.util.Arrays;

/**
 * Heap stores data using 0 based index.
 */
public class HeapAsArray {
	// arrays of numbers since vertices of graph are stored as numbers, else we should make array of whatever
	// object we want to store in the heap.
	private int[] nums;
	private int size;

	public HeapAsArray(int[] a) {
		this.nums = a;
		size = a.length;
		heapify();
	}

	public int getSize() {
		return size;
	}

	public int extractMin() {
		if (size < 1)
			return -1; // this normally should not happen, programming calling should take care of it.
		int min = nums[0];
		nums[0] = nums[size-1]; // put last element at top
		size--; // reduce size;
		bubbleDown(0);// bubble down to the last element;

		return min;
	}

	public void insert(int element) {
		ensureCapacity();
		nums[size] = element; // put element in last position
		size++; // increase size, since we got a new element
		bubbleUp(size-1);// bubble up the element to maintain balanced heap
	}

	private void ensureCapacity() {
		if (nums.length > size)
			return;
		int newCapacity = (size*3)/2 + 1; // 1.5 times the size

		nums = Arrays.copyOf(nums, newCapacity);
	}

	// bubble down from last parent, then previous parent, then...so on till root.
	private void heapify() {
		int lastParentIdx = getParent(size-1); // get the last parent
		if (lastParentIdx < 0)
			lastParentIdx = 0;

		for (int i=lastParentIdx;i>=0;i--) {
			bubbleDown(i);
		}
	}

	private void bubbleUp(int nodeIndex) {
		if (nodeIndex==0) // if root element, stop
			return;

		int parentIdx = getParent(nodeIndex);
		if (nums[parentIdx] > nums[nodeIndex]) {
			swap(parentIdx, nodeIndex);
			bubbleUp(parentIdx);
		}
	}


	// node is actually node index.
	private void bubbleDown(int nodeIndex) {
		int leftIndex = getLeftChildIndex(nodeIndex);
		int rightIndex = getRightChildIndex(nodeIndex);

		if (leftIndex > 0 && (nums[nodeIndex] > nums[leftIndex])) {
			swap(leftIndex, nodeIndex);
			bubbleDown(leftIndex);
		}
		// after swapping with left, do check for right element.
		if (rightIndex>0 && (nums[nodeIndex] > nums[rightIndex])) {
			swap(rightIndex, nodeIndex);
			bubbleDown(rightIndex);
		}

		return; // if we reach here means we have either reached leaf or heap is proper at this node.
	}

	private void swap(int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	// 0 based index
	// -1 if no index is found
	private int getLeftChildIndex(int nodeIndex) {
		int leftIndex = 2*nodeIndex + 1;

		if (leftIndex < size)
			return leftIndex;

		return -1;
	}

	private int getRightChildIndex(int nodeIndex) {
		int rightIndex = 2*nodeIndex + 2;
		if (rightIndex < size)
			return rightIndex;
		return -1;
	}

	private int getParent(int nodeIndex) {
		if (nodeIndex > 0) {
			if ((nodeIndex&1)==0) { // if even
				return nodeIndex/2 - 1;
			} else {
				return nodeIndex/2; // integer division will floor automatically
			}
		}

		return -1;
	}
}
