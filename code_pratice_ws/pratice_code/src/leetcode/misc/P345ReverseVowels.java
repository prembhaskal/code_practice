package leetcode.misc;

import java.util.HashMap;
import java.util.Map;

public class P345ReverseVowels {
    public static void main(String[] args) {
        P345ReverseVowels sol = new P345ReverseVowels();
        String input = "leetcode";
        String out = sol.reverseVowels(input);
        System.out.printf("input: %s, output: %s\n", input, out);
    }

    public String reverseVowels(String s) {
        var vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        var vmap = new HashMap<>();
        for (var ch : vowels) {
            vmap.put(ch, 1);
        }

        var chars = s.toCharArray();

        int sptr = 0;
        int eptr = chars.length - 1;

        for (; sptr < chars.length && eptr >= 0 && sptr < eptr; ) {
            char left = chars[sptr];
            if (!vmap.containsKey(left)) {
                sptr++;
                continue;
            }

            char right = chars[eptr];
            if (!vmap.containsKey(right)) {
                eptr--;
                continue;
            }

            // exchange
            chars[sptr] = right;
            chars[eptr] = left;
            sptr++;
            eptr--;
        }

        return new String(chars);
    }


}
