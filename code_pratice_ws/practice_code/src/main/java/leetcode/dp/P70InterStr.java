package leetcode.dp;

public class P70InterStr {
    public boolean isInterleave(String s1, String s2, String s3) {
        int i = 0;
        var A = s1.toCharArray();
        int j = 0;
        var B = s2.toCharArray();
        var C = s3.toCharArray();
        int k = 0;

        // return f(C, C.length-1, A, A.length-1, B, B.length-1);
//        int[][][] dp = new int[C.length][A.length][B.length];
//        for (k = 0; k < C.length; k++) {
//            for (i = 0; i < A.length; i++) {
//                for (j = 0; j < B.length; j++) {
//                    dp[k][i][j] = -1;
//                }
//            }
//        }
//
//        return fdp(C, C.length - 1, A, A.length - 1, B, B.length - 1, dp);
//
//        return fbottomup(A, B, C);
        return fbottomuplessmem(A, B, C);
    }

    // recursive
    // A[0...i]  B[0...j]  C[0...i+j]
    //  C[i+j] = f(C[i+j-1], A[i-1], B[j])  // choose from A
    //           or f(C[i+j-1], A[i], B[j-1]) // choose from B
    boolean f(char[] C, int ci, char[] A, int ai, char[] B, int bi) {
        if (ci < 0) {
            if (ai < 0 && bi < 0) {
                return true;
            }
            return false;
        }

        boolean first = false;
        if (ai >= 0 && C[ci] == A[ai]) {
            first = f(C, ci - 1, A, ai - 1, B, bi);
        }
        boolean second = false;
        if (bi >= 0 && C[ci] == B[bi]) {
            second = f(C, ci - 1, A, ai, B, bi - 1);
        }

        return first | second;
    }

    boolean fdp(char[] C, int ci, char[] A, int ai, char[] B, int bi,
                int[][][] dp) {
        if (ci < 0) {
            if (ai < 0 && bi < 0) {
                return true;
            }
            return false;
        }

        if (ai > -1 && bi > -1) {
            if (dp[ci][ai][bi] != -1) {
                return dp[ci][ai][bi] == 1;
            }
        }

        boolean first = false;
        if (ai >= 0 && C[ci] == A[ai]) {
            first = fdp(C, ci - 1, A, ai - 1, B, bi, dp);
        }
        boolean second = false;
        if (bi >= 0 && C[ci] == B[bi]) {
            second = fdp(C, ci - 1, A, ai, B, bi - 1, dp);
        }

        var ans = first | second;

        if (ai > -1 && bi > -1) {
            if (ans) {
                dp[ci][ai][bi] = 1;
            } else {
                dp[ci][ai][bi] = 0;
            }
        }

        return ans;
    }

    boolean fbottomup(char[] A, char[] B, char[] C) {
        int an = A.length;
        int bn = B.length;
        int cn = C.length;

        var dp = new boolean[cn + 1][an + 1][bn + 1];
        dp[0][0][0] = true;
        // A all done, compare B and C
        for (int k = 1; k < cn + 1; k++) {
            for (int i = 0; i < an + 1; i++) {
                for (int j = 0; j < bn + 1; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    if (i == 0) {
                        if (C[k - 1] == B[j - 1]) {
                            dp[k][0][j] = dp[k - 1][0][j - 1];
                        }
                    } else if (j == 0) {
                        if (C[k - 1] == A[i - 1]) {
                            dp[k][i][0] = dp[k - 1][i - 1][0];
                        }
                    } else {
                        if (C[k - 1] == A[i - 1]) {
                            dp[k][i][j] |= dp[k - 1][i - 1][j];
                        }
                        if (C[k - 1] == B[j - 1]) {
                            dp[k][i][j] |= dp[k - 1][i][j - 1];
                        }
                    }
                }
            }
        }

        return dp[cn][an][bn];
    }


    boolean fbottomuplessmem(char[] A, char[] B, char[] C) {
        int an = A.length;
        int bn = B.length;
        int cn = C.length;
        if (cn != an + bn) {
            return false;
        }

        // 2 dimension only, third can be derived.
        var dp = new boolean[cn + 1][an + 1];
        dp[0][0] = true;
        // A all done, compare B and C
        for (int k = 1; k < cn + 1; k++) {
            for (int i = 0; i < an + 1; i++) {
                int j = k - i;
                if (j < 0 || j > bn) {
                    continue;
                }
                if (i == 0) {
                    if (C[k - 1] == B[j - 1]) {
                        dp[k][0] |= dp[k - 1][0];
                    }
                } else if (j == 0) {
                    if (C[k - 1] == A[i - 1]) {
                        dp[k][i] |= dp[k - 1][i - 1];
                    }
                } else {
                    if (C[k - 1] == A[i - 1]) {
                        dp[k][i] |= dp[k - 1][i - 1];
                    }
                    if (C[k - 1] == B[j - 1]) {
                        dp[k][i] |= dp[k - 1][i];
                    }
                }
            }
        }

        return dp[cn][an];
    }
}
