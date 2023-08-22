package leetcode.string;

public class P459RepeatSubStr {
    public boolean repeatedSubstringPattern(String s) {
        return checkWithSmallerStr(s);
        // return checkWithAppend(s);
    }

    // slower than other method
    boolean checkWithAppend(String s) {
        var sb = new StringBuilder(s);
        var sbsb = sb.append(s);
        var rem = sbsb.substring(1, sbsb.length() - 1);
        return rem.contains(s); // plan contains is O(n^2), KMP would be faster.
    }

    boolean checkWithSmallerStr(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();
        for (int i = n / 2; i > 0; i--) {
            if (n % i == 0) {
                int m = n / i;
                boolean allmatch = true;
                for (int j = 1; j < m; j++) {
                    // match from j*i to j*i + i - 1
                    if (!substrmatch(chars, 0, i - 1, chars, i * j, i * j + i - 1)) {
                        // System.out.printf("match failed, i:%d, sf: %d, st: %d, df: %d, dt: %d\n", i, 0, i-1, i*j, i * j + i - 1);
                        allmatch = false;
                        break;
                    }
                }

                if (allmatch) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean substrmatch(char[] src, int sfrom, int sto, char[] dst, int dfrom, int dto) {
        if (sto - sfrom != dto - dfrom) {
            return false;
        }
        for (int i = sfrom, j = dfrom; i <= sto && j <= dto; i++, j++) {
            if (src[i] != dst[j]) {
                return false;
            }
        }

        return true;
    }
}
