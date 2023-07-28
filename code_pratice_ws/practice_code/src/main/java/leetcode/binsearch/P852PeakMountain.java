package leetcode.binsearch;

public class P852PeakMountain {
    public int peakIndexInMountainArray(int[] arr) {
        // binary search
        int low = 0;
        int high = arr.length - 1;
        // System.out.printf("low: %d, high: %d, mid: %d\n", low, high, mid);
        while (low < high) { // loop when more than 3 elements.
            int mid = (high + low) / 2;
            // System.out.printf("before low: %d, high: %d, mid: %d\n", low, high, mid);
            // check mid+1 part of peak
            if (arr[mid] < arr[mid + 1]) {
                low = mid + 1;
            } else { // arr[mid] >= arr[mid+1]
                high = mid;
            }
            // System.out.printf("after low: %d, high: %d, mid: %d\n", low, high, mid);
        }

        return low;

    }

}
