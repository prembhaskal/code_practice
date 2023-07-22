package leetcode.string;

public class P14LongCommPrefix {
    public String longestCommonPrefix(String[] strs) {
        String str = strs[0];

        StringBuilder sb = new StringBuilder();
        // compare with rest chars
        out:
        for (int c = 0; c < str.length(); c++) {
            for (int i = 1; i < strs.length; i++) {
                String ostr = strs[i];
                if (ostr.length() <= c) {
                    break out;
                }
                if (str.charAt(c) != ostr.charAt(c)) {
                    break out;
                }
            }
            sb.append(str.charAt(c));
        }

        return sb.toString();
    }
}
