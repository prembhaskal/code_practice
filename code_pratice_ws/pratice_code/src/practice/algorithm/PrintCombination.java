package practice.algorithm;

public class PrintCombination {

	private int totalCallsMade;
	private int totalCombinations;
	private boolean print;

	private int totalLength;

	public void printCombination(int[] nums, int sizeRange, boolean print) {
		this.print = print;
		boolean[] mask = new boolean[nums.length];
		totalCombinations = 0;
		totalCallsMade = 0;

		getCombination(nums, mask, 0, 0, sizeRange);

		System.out.println("total Combinations are " + totalCombinations);
		System.out.println("total calls made are " + totalCallsMade);
	}

	private void getCombination(int[] nums, boolean[] mask, int idx, int size, int maxSize) {
		totalCallsMade++;

		// stop early, if we cannot meet the maxSize requirement.
		int remElements = nums.length - idx;
		if (size + remElements < maxSize)
			return;

		if (size == maxSize) {
			totalCombinations++;
			if (print)
				doStuff(nums, mask);
			return;
		}

		if (idx == nums.length)
			return; // no more elements to select from.


		// choose this number
		mask[idx] = true;
		getCombination(nums, mask, idx+1, size+1, maxSize);

		// don't choose this number
		mask[idx] = false;
		getCombination(nums, mask, idx+1, size, maxSize);
	}

	private void doStuff(int[] nums, boolean[] mask) {
		for (int i = 0; i < nums.length; i++) {
			if (mask[i])
				System.out.print(nums[i] + " ");
		}
		System.out.println("");
	}


	private int[] andBits;
	private int[] orBits;

	private void initAndBits() {
		andBits = new int[totalLength];
		orBits = new int[totalLength];

		int xorBit = 0b1;
		xorBit = xorBit << totalLength;
		xorBit = xorBit - 1;

		int start = 0b1;
		for (int i = 0; i < totalLength; i++) {
			andBits[i] = start ^ xorBit;
			orBits[i] = start;
			System.out.println("AND -->" + Integer.toBinaryString(andBits[i]) + "<-- ");
			System.out.println("OR -->" + Integer.toBinaryString(orBits[i]) + "<-- ");
			start = start << 1;
		}
	}

	public void printCombinationOfBits(int length, int setBits, boolean print) {
		this.print = print;
		this.totalLength = length;
		int bitMask = 0b0;
//		bitMask = bitMask << length;
//		bitMask = bitMask - 1;

		initAndBits();
		getCombinationUsingBits(bitMask, 0, 0, setBits);
	}

	// the initial bitMask should be 0000000000000, else it wont work.
	private void getCombinationUsingBits(int bitMask, int idx, int size, int maxSize) {
		if (size == maxSize) {
			doStuff(bitMask);
			return;
		}

		if (idx == totalLength)
			return;

		// choose the number.... set this bit.
		bitMask = bitMask | orBits[idx];
		getCombinationUsingBits(bitMask, idx + 1, size + 1, maxSize);

		// don't choose the number .... reset this bit.
		bitMask = bitMask & andBits[idx];
		getCombinationUsingBits(bitMask, idx + 1, size, maxSize);
	}

	private void doStuff(int bitMask) {
		System.out.println(bitMask + " " + Integer.toBinaryString(bitMask));
	}
}
