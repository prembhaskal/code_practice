package gfg.string;

import java.util.*;

public class AnagramsTog {
    public List<List<String>> Anagrams(String[] string_list) {
        // Code here
        // keep original order while making answer
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> groups = new HashMap<>();
        for (int i = 0; i < string_list.length; i++) {
            String str = string_list[i];
            String key = getKey(str);
            List<String> list = groups.get(key);
            if (list == null) {
                list = new ArrayList<>();
                ans.add(list); // keep list reference in answer.
                groups.put(key, list);
            }
            list.add(str);
        }
        return ans;
    }

    String getKey(String s) {
        char [] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
