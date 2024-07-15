package gfg.string;

import java.util.HashMap;
import java.util.Map;

// GFG https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1?page=1&sortBy=submissions
public class LongestKUniqueStr {
    public int longestkSubstr(String s, int k) {
        // code here
        Map<Integer, Integer> countMap = new HashMap<>();
        int start = 0;
        int end = 0;
        int unique = 0;

        int maxDist = -1;

        while (end < s.length()) {
            int ch = s.charAt(end) - 'a';

            int exist = countMap.getOrDefault(ch, 0);
            if (exist == 0) {
                // increase unique only when we see a new element
                unique++;
            }
            countMap.put(ch, exist + 1);
            if (unique == k) {
                maxDist = Math.max(maxDist, end - start + 1);
            }
            while (unique > k) {
                // remove character at start.
                int sch = s.charAt(start) - 'a';
                exist = countMap.get(sch);
                if (exist == 1) {
                    // reduce unique when all occurrence of elements are gone.
                    unique--;
                }
                countMap.put(sch, exist - 1);

                start++;
            }
            end++;
        }

        return maxDist;
    }
}
