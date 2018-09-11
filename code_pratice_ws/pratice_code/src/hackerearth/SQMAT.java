package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by prem on 9/9/18.
 */
public class SQMAT {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int M = Integer.parseInt(temp[1]);

        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        int[] out_ = solve(arr, N , M);
        wr.print(out_[0]);

        for (int i = 1; i < out_.length; i++) {
            wr.print(" " + out_[i]);
        }

        wr.close();
        br.close();
    }

    static boolean[][] cpalinprev;
    static boolean[][] rpalinprev;

    static boolean[][] cpalin;
    static boolean[][] rpalin;

    static boolean[][] rtemp;
    static boolean[][] ctemp;

    static int[] solve(String[] A, int N, int M) {
        boolean debug = false;

        char[][] mat = new char[N][M];
        for (int i = 0; i < N; i++) {
            mat[i] = A[i].toCharArray();
        }

        int P = Math.min(N, M);


        cpalinprev = new boolean[N][M];
        rpalinprev = new boolean[N][M];

        cpalin = new boolean[N][M];
        rpalin = new boolean[N][M];

        int[][] rowcnt = new int[N][M];
        int[][] colcnt = new int[N][M];

        int maxK = -1;
        int maxStI = -1;
        int maxStJ = -1;
        int maxEnI = -1;
        int maxEnJ = -1;

        //init all k = 0
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                rpalinprev[i][j] = true;
                cpalinprev[i][j] = true;
            }
        }

        for (int k = 2; k <= P; k += 2) {
            int[] res = solveForK(k, mat, N, M, cpalinprev, rpalinprev, cpalin, rpalin, rowcnt, colcnt);

            if (res[4] > 0 && maxK < k) {
                maxK = k;
                maxStI = res[0];
                maxStJ = res[1];
                maxEnI = res[2];
                maxEnJ = res[3];
            }

            ctemp = cpalinprev;
            rtemp = rpalinprev;
            cpalinprev = cpalin;
            rpalinprev = rpalin;

            initMat(ctemp, N);
            initMat(rtemp, N);

            cpalin = ctemp;
            rpalin = rtemp;

            for (int i = 0; i < N; i++) {
                Arrays.fill(rowcnt[i], 0);
                Arrays.fill(colcnt[i], 0);
            }
        }

        initMat(cpalinprev, N);
        initMat(rpalinprev, N);
        initMat(cpalin, N);
        initMat(rpalin, N);

        //init all k = 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                rpalinprev[i][j] = true;
                cpalinprev[i][j] = true;
            }
        }

        for (int k = 3; k <= P; k += 2) {
            int[] res = solveForK(k, mat, N, M, cpalinprev, rpalinprev, cpalin, rpalin, rowcnt, colcnt);

            if (res[4] > 0 && maxK < k) {
                maxK = k;
                maxStI = res[0];
                maxStJ = res[1];
                maxEnI = res[2];
                maxEnJ = res[3];
            }

            ctemp = cpalinprev;
            rtemp = rpalinprev;
            cpalinprev = cpalin;
            rpalinprev = rpalin;

            initMat(ctemp, N);
            initMat(rtemp, N);

            cpalin = ctemp;
            rpalin = rtemp;

            for (int i = 0; i < N; i++) {
                Arrays.fill(rowcnt[i], 0);
                Arrays.fill(colcnt[i], 0);
            }
        }

        return new int[]{maxStI, maxStJ, maxEnI, maxEnJ};
    }

    static void initMat(boolean[][] mtx, int N) {
        for (int i = 0; i < N; i++) {
            Arrays.fill(mtx[i], false);
        }
    }


    static int[] solveForK(int k, char[][] mat, int N, int M, boolean[][] cpalinprev, boolean[][] rpalinprev, boolean[][] cpalin, boolean[][] rpalin, int[][] rowcnt, int[][] colcnt) {
        rpalin[0][0] = true;
        cpalin[0][0] = true;

        // first column, j == 0
        for (int i = k-1; i < N; i++) {
            if (mat[i][0] == mat[i-k+1][0] && cpalinprev[i-1][0]) {
                cpalin[i][0] = true;
            }
        }

        //first row, i == 0
        for (int j = k-1; j < M; j++) {
            if (mat[0][j] == mat[0][j-k+1] && rpalinprev[0][j-1]) {
                rpalin[0][j] = true;
            }
        }

        // remaining rows
        for (int j = k-1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                if (mat[i][j - k + 1] == mat[i][j] && rpalinprev[i][j-1]) {
                    rpalin[i][j] = true;
                }
            }
        }

        // remaining columns
        for (int i = k-1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mat[i - k + 1][j] == mat[i][j] && cpalinprev[i-1][j]) {
                    cpalin[i][j] = true;
                }
            }
        }

        rowcnt[0][0] = 1;
        colcnt[0][0] = 1;

        // first row
        for (int j = 0; j < M; j++) {
            if (rpalin[0][j]) {
                rowcnt[0][j] = 1;
            }
        }

        // first column
        for (int i = 0; i < N; i++) {
            if (cpalin[i][0]) {
                colcnt[i][0] = 1;
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (rpalin[i][j]) {
                    rowcnt[i][j] = 1 + rowcnt[i-1][j];
                }
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (cpalin[i][j]) {
                    colcnt[i][j] = 1 + colcnt[i][j-1];
                }
            }
        }

        int[] maxSquare = new int[5];

        int actK = -1;
        int maxI = -1;
        int maxJ = -1;

        outer:
        for (int i = k-1; i < N; i++) {
            for (int j = k-1; j < M; j++) {
                if (rowcnt[i][j] >= k && colcnt[i][j] >= k) {
                    actK = k;
                    maxI = i;
                    maxJ = j;
                    break outer;
                }
            }
        }

        int maxStI = maxI - k + 1;
        int maxStJ = maxJ - k + 1;

        maxSquare[0] = maxStI + 1;
        maxSquare[1] = maxStJ + 1;
        maxSquare[2] = maxI + 1;
        maxSquare[3] = maxJ + 1;
        maxSquare[4] = actK;

        return maxSquare;
    }

}
