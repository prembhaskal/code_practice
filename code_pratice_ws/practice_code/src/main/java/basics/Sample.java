package basics;

import java.util.HashMap;
import java.util.Map;

public class Sample {


    public static void main(String[] args) {

        String key1 = "Pl";
        System.out.println("for " + key1 + " ans: " + getSeqNo(key1));

//        System.out.printf("total words : %d\n", getWordsInDictionary());
        System.out.printf("total words : %d\n", getWordsInDict());

    }

    static int getWordsInDict() {
        return getWordsInDictR(new StringBuilder());
    }

    static int getWordsInDictR(StringBuilder sb) {
        int total = 0;
        for (int i = 0; i < 26; i++) {
            sb.append((char) ('A' + i));

            int cnt = getSeqNo(sb.toString());
            if (cnt == -1) {
                sb.deleteCharAt(sb.length() - 1);
                continue;
            } else if (cnt > 0) {
                total++;
            }
            total = total + getWordsInDictR(sb);

            sb.deleteCharAt(sb.length() - 1);
        }
        return total;
    }

    static int getWordsInDictionary() {
        int total = 0;
        StringBuilder sb = new StringBuilder();

        // we want to generate 3 characters all words
        for (int a = 0; a < 26; a++) {
            // prefix check,
            // if it fails, we exit early
            sb.append((char) ('A' + a));
            int cnt = getSeqNo(sb.toString());
            if (cnt == -1) {
                sb.deleteCharAt(sb.length() - 1);
                continue;
            } else if (cnt > 0) {
                total++;
            }

            for (int b = 0; b < 26; b++) {
                sb.append((char) ('A' + b));
                cnt = getSeqNo(sb.toString());
                if (cnt == -1) {
                    sb.deleteCharAt(sb.length() - 1);
                    continue;
                } else if (cnt > 0) {
                    total++;
                }
                for (int c = 0; c < 26; c++) {
                    sb.append((char) ('A' + c));
                    cnt = getSeqNo(sb.toString());
                    if (cnt > 0) {
                        total++;
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.deleteCharAt(sb.length() - 1);
            }

            sb.deleteCharAt(sb.length() - 1);
        }
        return total;
    }

    static String[] dictionary = new String[]{
            "ABC",
            "PLA",
            "PLB",
            "XYZ",
            "AB",
            "ZY",
            "Z",
            "XY",
    };

    static int getSeqNo(String key) {
        System.out.println("key " + key);
        key = key.toLowerCase();
        Map<String, Integer> dictionaryMap = new HashMap<>();
        for (int i = 0; i < dictionary.length; i++) {
            String entry = dictionary[i].toLowerCase();
            dictionaryMap.put(entry, i + 1);
        }

        if (dictionaryMap.containsKey(key)) {
            return dictionaryMap.get(key);
        } else {
            // check for prefix
            for (String entry : dictionary) {
                entry = entry.toLowerCase();
                if (entry.startsWith(key)) {
                    return 0;
                }
            }
            return -1;
        }
    }

}
