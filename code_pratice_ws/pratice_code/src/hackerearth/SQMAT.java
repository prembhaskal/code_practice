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
        wr.println(out_[0]);

        for (int i = 1; i < out_.length; i++) {
            wr.print(" " + out_[i]);
        }

        wr.close();
        br.close();
    }
    
    static int[] solve(String[] A, int N, int M) {
        boolean debug = false;

        char[][] mat = new char[N][M];
        for (int i = 0; i < N; i++) {
            mat[i] = A[i].toCharArray();
        }

        int P = Math.max(N, M);

        // matrix to store true, if there is palindrome (row,column) ending at (i,j) with length k. palin[i][j][k]
        boolean[][][] rpalin = new boolean[N][M][P+1];
        boolean[][][] cpalin = new boolean[N][M][P+1];


        //init all k = 0 and k = 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                rpalin[i][j][0] = true;
                rpalin[i][j][1] = true;

                cpalin[i][j][0] = true;
                cpalin[i][j][1] = true;
            }
        }

        // first column, j == 0
        for (int i = 1; i < N; i++) {
            for (int k = 2; k <= P; k++) {
                if (i > k-1 && mat[i][0] == mat[i-k+1][0] && cpalin[i-1][0][k-2]) {
                    cpalin[i][0][k] = true;
                }
            }
        }

        //first row, i == 0
        for (int j = 1; j < M; j++) {
            for (int k = 2; k <= P; k++) {
                if (j > k-1 && mat[0][j] == mat[0][j-k+1] && rpalin[0][j-1][k-2]) {
                    rpalin[0][j][k] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                rpalin[i][j][0] = true;
                rpalin[i][j][1] = true;

                for (int k = 2; k <= P; k++) {
                    if (j > k-1 && mat[i][j - k + 1] == mat[i][j] && rpalin[i][j-1][k-2]) {
                        rpalin[i][j][k] = true;
                    }
                }

                cpalin[i][j][0] = true;
                cpalin[i][j][1] = true;

                for (int k = 2; k <= P; k++) {
                    if (i > k-1 && mat[i - k + 1][j] == mat[i][j] && cpalin[i-1][j][k-2]) {
                        cpalin[i][j][k] = true;
                    }
                }
            }
        }

        if (debug) {
            for (int i = 0; i < N; i++) {
                System.out.println("printing for i = " + i);

                for (int j = 0; j < N; j++) {
                    System.out.println("rpalin j = " + j + "--> " + Arrays.toString(rpalin[i][j]));
                }

                System.out.println("");

                for (int j = 0; j < N; j++) {
                    System.out.println("cpalin j = " + j + "--> " + Arrays.toString(cpalin[i][j]));
                }

                System.out.println("");
            }
        }

        //
        int [][][] rowcnt = new int[N][M][P+1];
        int [][][] colcnt = new int[N][M][P+1];

        rowcnt[0][0][1] = 1;
        colcnt[0][0][1] = 1;
        for (int i = 0; i < N; i++) {
            colcnt[i][0][1] = 1;
        }

        for (int j = 0; j < M; j++) {
            rowcnt[0][j][1] = 1;
        }


        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                for (int k = 1; k <= P; k++) {
                    if (rpalin[i][j][k]) {
                        rowcnt[i][j][k] = 1 + rowcnt[i-1][j][k];
                    }
                    if (cpalin[i][j][k]) {
                        colcnt[i][j][k] = 1 + colcnt[i][j-1][k];
                    }
                }
            }
        }


        int[] maxSquare = new int[4];

        int maxK = -1;
        int maxI = -1;
        int maxJ = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 2; k <= P ; k++) {
                    if (rowcnt[i][j][k] >= k && colcnt[i][j][k] >= k) {
                        if (maxK < k) {
                            maxK = k;
                            maxI = i;
                            maxJ = j;
                        }
                    }
                }
            }
        }

        int maxStI = maxI - maxK + 1;
        int maxStJ = maxJ - maxK + 1;

        maxSquare[0] = maxStI + 1;
        maxSquare[1] = maxStJ + 1;
        maxSquare[2] = maxI + 1;
        maxSquare[3] = maxJ + 1;

        return maxSquare;
    }

}
