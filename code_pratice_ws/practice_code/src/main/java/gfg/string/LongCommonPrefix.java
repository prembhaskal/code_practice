package gfg.string;

// GFG https://www.geeksforgeeks.org/problems/longest-common-prefix-in-an-array5129/1?page=1&sortBy=submissions
public class LongCommonPrefix {
    String longestCommonPrefix(int n, String arr[]) {
        // code here

        int smallest = 1000_000;
        int smallestIdx = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i].length() < smallest) {
                smallest = arr[i].length();
                smallestIdx = i;
            }
        }

        int c = 0;
        for (; c < smallest; c++) {
            char ch = arr[smallestIdx].charAt(c);
            boolean allMatched = true;
            for (int i = 0; i < n; i++) {
                if (ch != arr[i].charAt(c)) {
                    allMatched = false;
                    break;
                }
            }
            if (!allMatched) {
                break;
            }
        }
        // System.out.printf("int c = %d\n", c);
        if (c == 0) {
            return "-1";
        }

        return arr[smallestIdx].substring(0, c);

    }
}
