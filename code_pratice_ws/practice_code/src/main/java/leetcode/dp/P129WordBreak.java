package leetcode.dp;

import java.util.*;

public class P129WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return frec(s, dict, new HashMap<>());
    }

    boolean frec(String s, Set<String> dict, Map<String, Boolean> mem) {
        if (s.length() == 0) {
            return true;
        }
        if (mem.containsKey(s)) {
            return mem.get(s);
        }
        if (dict.contains(s)) {
            mem.put(s, true);
            return mem.get(s);
        }
        // break s and see if the words are part of dict.
        boolean present = false;
        for (int i = 1; i < s.length(); i++) {
            String word = s.substring(0, i);
            present = dict.contains(word) && frec(s.substring(i), dict, mem);
            if (present) {
                break;
            }
        }
        mem.put(s, present);
        return mem.get(s);
    }
}
