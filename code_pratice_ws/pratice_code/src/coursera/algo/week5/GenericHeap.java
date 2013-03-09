package coursera.algo.week5;

import java.util.Arrays;

public class  GenericHeap<T extends HeapEntry> {

	T[] nums;
	int size;


	public GenericHeap(T[] a) {
		this.nums = a;
		size = a.length;
		heapify();
	}


	public int getSize() {
		return size;
	}

	public T extractMin() {
		if (size < 1)
			return null; // this normally should not happen, programming calling should take care of it.
		T min = nums[0];
		nums[0] = nums[size-1]; // put last element at top
		size--; // reduce size;
		bubbleDown(0);// bubble down to the last element;

		return min;
	}

	public void insert(T element) {
		ensureCapacity();
		nums[size] = element; // put element in last position
		size++; // increase size, since we got a new element
		bubbleUp(size-1);// bubble up the element to maintain balanced heap
	}

	public void delete(int nodeIndex) {
		swap(nodeIndex, size-1);// swap element to delete with last element in heap
		size--; // since we removed one element

		int parentIdx = getParent(nodeIndex);
		if (parentIdx >=0 && nums[nodeIndex].key <= nums[parentIdx].key) {
			bubbleUp(nodeIndex); // bubble up if element is smaller than parent
		} else {
			bubbleDown(nodeIndex);// else bubble down
		}
	}

	public T[] getAllElements() {
		T[] a = Arrays.copyOf(nums, size);
		return a;
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
		if (nums[parentIdx].key > nums[nodeIndex].key) {
			swap(parentIdx, nodeIndex);
			bubbleUp(parentIdx);
		}
	}


	// node is actually node index.
	private void bubbleDown(int nodeIndex) {
		int leftIndex = getLeftChildIndex(nodeIndex);
		int rightIndex = getRightChildIndex(nodeIndex);

		if (leftIndex > 0 && (nums[nodeIndex].key > nums[leftIndex].key)) {
			swap(leftIndex, nodeIndex);
			bubbleDown(leftIndex);
		}
		// after swapping with left, do check for right element.
		if (rightIndex>0 && (nums[nodeIndex].key > nums[rightIndex].key)) {
			swap(rightIndex, nodeIndex);
			bubbleDown(rightIndex);
		}

		return; // if we reach here means we have either reached leaf or heap is proper at this node.
	}

	private void swap(int i, int j) {
		T temp = nums[i];
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


