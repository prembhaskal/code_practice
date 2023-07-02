package gfg;

public class AllocateMinPages {
    //Function to find minimum number of pages.
    public static int findPages(int[] A, int N, int M) {
        if (N < M) {
            return -1;
        }
        // binary search for answer
        int maxPages = -1;
        int total = 0;
        for (int pages : A) {
            total += pages;
            if (pages > maxPages) {
                maxPages = pages;
            }
        }

        int low = maxPages;
        int high = total;

        int answer = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean canAllocate = allocateAtMax(A, N, M, mid);
            if (canAllocate) {
                answer = mid;
                // if we can allocate mid, try for lower number, not current as answer in case this is the minimum.
                high = mid - 1;
            } else {
                // if we cannot allocate mid, try to higher number.
                low = mid + 1;
            }

        }

        return answer;

    }


    // if we can divide books in M groups.
    static boolean allocateAtMax(int[] A, int N, int M, int maxAlloc) {
        int curr = 0;
        int std = 0;
        for (int pages : A) {
            if (curr + pages > maxAlloc) {
                std++;
                curr = 0;
                if (pages > maxAlloc) {
                    return false;
                }
            }
            curr = curr + pages;
        }


        return std < M;
    }
}
