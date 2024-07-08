package leetcode.slidewin;

import java.util.HashMap;
import java.util.Map;

public class P3LongSubstrNoDuplicate {
    public int lengthOfLongestSubstring(String s) {
        // 2 pointers
        // freq map
        // fwd ptr, moves ahead, if able to add new entry to map, mark curr length
        // if unable to add, moves prev. pointer to index next to duplicate
        // store index in map instead of boolean.
        Map<Integer, Integer> idxMap = new HashMap<>();
        // int f = 0; // fwd ptr
        int p = 0; // prev ptr
        int maxlen = 0;
        for (int f = 0; f < s.length(); f++) {
            // map check
            int curr = s.charAt(f);
            if (idxMap.containsKey(curr)) {
                int prevIdx = idxMap.get(curr);
                if (prevIdx >= p) {
                    p = prevIdx + 1;
                }
            }
            idxMap.put(curr, f);

            // mark curr lens
            int currlen = f - p + 1;
            maxlen = Math.max(maxlen, currlen);
        }

        return maxlen;
    }
}
