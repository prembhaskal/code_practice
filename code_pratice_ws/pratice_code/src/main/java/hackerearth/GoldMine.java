package hackerearth;

import java.io.PrintWriter;

public class GoldMine {

    private static int[][] dynsum;
    public static void calc(int N, int M, int[][] A) {
        // find diagonal sum first
        dynsum = new int[N+1][M+1];

        for (int r = N - 1; r >= 0; --r) {
            for (int c = M - 1; c >= 0; --c) {
                dynsum[r][c] = A[r][c] + dynsum[r+1][c] + dynsum[r][c+1] - dynsum[r+1][c+1];
            }
        }
    }

    public static int solve(int x1, int y1, int x2, int y2) {
        x1--;
        y1--;
        x2--;
        y2--;
        int r1,r2,c1,c2;
        r1 = x1;
        r2 = x2;
        c1 = y1;
        c2 = y2;
        if (x1 > x2) {
            r1 = x2;
            r2 = x1;
        }

        if (y1 > y2) {
            c1 = y2;
            c2 = y1;
        }

        int sum = dynsum[r1][c1] - dynsum[r1][c2+1] - dynsum[r2+1][c1] + dynsum[r2+1][c2+1];
        return sum;
    }

    public static int solveBruteforce(int N, int M, int[][] A, int x1, int y1, int x2, int y2) {
        x1--;y1--;x2--;y2--;
        long sum = 0;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                sum += A[i][j];
            }
        }
        return (int)sum;
    }
}
