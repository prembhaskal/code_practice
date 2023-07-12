package leetcode.array;

import java.util.Map;
import java.util.HashMap;

public class P76MinWinSubstr {
    public static void main(String[] args) throws Exception {
        try {
            var testclass = new P76MinWinSubstr();
            var ans = testclass.minWindow("ADOBECODEBANC", "ABC");
            System.out.printf("ans is %s\n", ans);
            System.out.println("hello world");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> chMap = new HashMap<>();
        char[] tc = t.toCharArray();

        for (char c : tc) {
            int freq = chMap.getOrDefault(c, 0);
            chMap.put(c, freq + 1);
        }

        // System.out.printf("exp map is %s\n", chMap.toString());

        int left = 0;
        int right = 0;

        Map<Character, Integer> currMap = new HashMap<>();
        int minlen = 1000_000;

        int minleft = -1;
        int minright = -1;
        while (right < s.length()) {
            // compute
            char ch = s.charAt(right);
            currMap.put(ch, currMap.getOrDefault(ch, 0) + 1);

            while (compareMap(chMap, currMap)) {
//                minlen = Math.max(minlen, right - left + 1);
                if (minlen > right - left + 1) {
                    minlen = right - left + 1;
                    minleft = left;
                    minright = right;
                }

                char leftch = s.charAt(left);
                currMap.put(leftch, currMap.get(leftch) - 1);
                left++;
            }
            right++;
        }

        if (minlen == 1000_000) {
            return "";
        }
        return s.substring(minleft, minright + 1);
    }

    boolean compareMap(Map<Character, Integer> exp, Map<Character, Integer> act) {
        // System.out.printf("exp map is %s, act map is %s\n", exp.toString(), act.toString());
        for (Map.Entry<Character, Integer> entry : exp.entrySet()) {
            if (act.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }

        return true;
    }
}