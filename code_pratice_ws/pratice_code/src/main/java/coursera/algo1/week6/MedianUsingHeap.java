package coursera.algo1.week6;

import coursera.algo1.week5.HeapAsArray;
import java.io.PrintWriter;
import java.util.Scanner;

public class MedianUsingHeap {

	private int[] nums;

	private MaxHeap maxHeap;
	private HeapAsArray minHeap;

	private int MOD = 10000;

	public void solve(Scanner in, PrintWriter out, int nos) {
		nums = new int[nos];
		readFile(in);
		initHeap();

		int medianSum = 0;

		for (int i=0;i<nos;i++) {
			addElement(nums[i]);
			balanceHeap();
			int median = getMedian(i+1);
			medianSum = (medianSum + median)%MOD;
		}

		out.println("median sum is " + medianSum);

	}


	private void initHeap() {
		maxHeap = new MaxHeap(new int[]{});
		minHeap = new HeapAsArray(new int[]{});
	}

	private void addElement(int element) {
		if (maxHeap.getSize()==0) {
			maxHeap.insert(element);// insert first element into maxHeap (left side Heap)
		} else if (element > maxHeap.peekMax()) {
			minHeap.insert(element); // insert bigger element into minHeap (right side heap)
		} else {
			maxHeap.insert(element);
		}
	}


	// as per question, if k is odd. median = (k+1)/2th smallest element
	// if k is even, k/2th smallest element.
	private int getMedian(int totalElement) {
		int maxHeapSize = maxHeap.getSize();
		int minHeapSize = minHeap.getSize();

		int medianIndex;
		if ((totalElement&1)==0) {
			return maxHeap.peekMax(); // no need to calculate since if size is even, the heaps will be balanced.
		} else {
			medianIndex = (totalElement + 1) >> 1;
			if (maxHeapSize == medianIndex) {
				return maxHeap.peekMax();
			} else {
				return minHeap.peekMin();
			}

		}
	}


	private void balanceHeap() {
		int maxHeapSize = maxHeap.getSize();
		int minHeapSize = minHeap.getSize();

		if (Math.abs(maxHeapSize - minHeapSize) > 1) {
			if (maxHeapSize > minHeapSize) {
				int element = maxHeap.extractMax();
				minHeap.insert(element);
			} else {
				int element = minHeap.extractMin();
				maxHeap.insert(element);
			}
		}
	}

	private void readFile(Scanner in) {
		int i=0;
		while (in.hasNext()) {
			nums[i++] = Integer.parseInt(in.nextLine());
		}
	}
}
