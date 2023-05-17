package algorithm.chap2;

public class BinarySearch {

	public static void main(String[] s) {
		testSimpleBinarySearch();
	}

	private static void testSimpleBinarySearch() {
		BinarySearch search = new BinarySearch();
		int[] array;

		array = new int[] {1,2,3,4,6,9,12};
		System.out.println(search.search1(array,1));

		System.out.println(search.search1(array,7));

		System.out.println(search.search1(array,9));

		System.out.println(search.search1(array,12));

		array = new int[] {1,2,3,4,6,7,8,9};
		System.out.println(search.search1(array,1));
		System.out.println(search.search1(array,9));
		System.out.println(search.search1(array,5));
	}


	// assumes that the array nums is search in ascending order
	public boolean search1(int[] nums, int key) {
		int low = 0;
		int high = nums.length-1;
		int mid;
		boolean found = false;

		while (low <= high) {
			mid = (low + high)/2;
			if (key == nums[mid]) {
				found = true;
				break;
			} else if (key < nums[mid]) {
				high = mid-1;//previous element since mid is already considered
			} else {
				low = mid+1;//next element since mid is already considered
			}
		}

		return found;
	}

	// implemented recursive search for it.
	public boolean search2(int[] nums, int key) {
		int low = 0;
		int high = nums.length-1;
		int mid;
		return searchRecursive(nums, low, high, key);
	}

	private boolean searchRecursive(int[] nums, int low, int high, int key) {
		if (low > high)
			return false;

		int mid = (low + high)/2;

		if (key==nums[mid])
			return true;
		else if (key < nums[mid]) {
			high = mid-1;
			return searchRecursive(nums, low, high, key);
		} else {
			low = mid+1;
			return searchRecursive(nums, low, high, key);
		}

	}
}
