package gfg.string;

// https://www.geeksforgeeks.org/problems/smallest-window-in-a-string-containing-all-the-characters-of-another-string-1587115621/1?page=1&sortBy=submissions
public class SmallestWindow {

    public static void main(String[] args) {
        String ans = smallestWindow("timetopractice", "toc");
        System.out.printf("answer is %s\n", ans);
    }

    //Function to find the smallest window in the string s consisting
    //of all the characters of string p.
    public static String smallestWindow(String s, String p) {
        // Your code here
        int[] expfreq = new int[26];
        int unique = 0;
        for (int i = 0; i < p.length(); i++) {
            int ch = p.charAt(i) - 'a';
            if (expfreq[ch] == 0) {
                unique++;
            }
            expfreq[ch]++;
        }

        int[] window = new int[26];
        int start = 0;
        int matching = 0;
        int minlen = 1000_000_000;
        int minstart = -1;
        for (int end = 0; end < s.length(); end++) {
            int ch = s.charAt(end) - 'a';
            window[ch]++;

            // Using this matching count logic to avoid matching map for each character.
            if (window[ch] == expfreq[ch]) {
                matching++;
            }
            if (matching == unique) {
                int len = end - start + 1;
                if (len < minlen) {
//                    System.out.printf("min at start: %d, end: %d\n", start, end);
                    minlen = len;
                    minstart = start;
                }
            }
            // try to reduce window
            while(matching == unique) {
                int sch = s.charAt(start) - 'a';
                if (window[sch] == expfreq[sch]) {
                    matching--;
                }
                start++;
                window[sch]--;

                if (matching == unique) {
                    int len = end - start + 1;
                    if (len < minlen) {
//                        System.out.printf("min at start: %d, end: %d\n", start, end);
                        minlen = len;
                        minstart = start;
                    }
                }
            }
        }
        if (minstart == -1) {
            return "-1";
        }

        return s.substring(minstart, minstart + minlen);
    }
}
