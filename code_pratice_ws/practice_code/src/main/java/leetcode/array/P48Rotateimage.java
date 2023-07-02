package leetcode.array;

public class P48Rotateimage {
    public void rotate(int[][] matrix) {
        // printMatrix(matrix);
        transpose(matrix);
        reverseRows(matrix);
        // printMatrix(matrix);
    }


    private void rotateNormally(int[][] mat) {
        int off = 0;
        // size reduces by 2 everytime
        for (int size = mat.length; size > 0; size = size - 2, off++) {
            int first = off;
            int last = off+(size-1);
            for (int c = 0; c < size - 1; c++) { // size-1 becoz we rotate 1 less time
                // save the top row
                int tmp = mat[first][first + c];

                // top row <= left column
                mat[first][first+c] = mat[last-c][first];

                // left column <= bottom row
                mat[last-c][first] = mat[last][last-c];

                // bottom row <= right column
                mat[last][last-c] = mat[first+c][last];

                // right column <= top row
                mat[first+c][last] = tmp;
            }

        }

        return;
    }

    private void transpose(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = i + 1; j < mat.length; j++) {
                swap(mat, i, j, j, i);
            }
        }
    }

    private void reverseRows(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            reverseArray(mat[i]);
        }
    }

    private void reverseArray(int[] ar) {
        for (int i = 0, j = ar.length - 1; i < j; i++, j--) {
            int tmp = ar[i];
            ar[i] = ar[j];
            ar[j] = tmp;
        }
    }

    private void swap(int[][] mat, int r1, int c1, int r2, int c2) {
        int tmp = mat[r1][c1];
        mat[r1][c1] = mat[r2][c2];
        mat[r2][c2] = tmp;
    }

    private void printMatrix(int[][] mat) {
        printf("----- matrix ----- \n");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                printf(" %d", mat[i][j]);
            }
            printf("\n");
        }
        printf("----- matrix ----- \n");
    }

    private void printf(String str, Object... args) {
        System.out.printf(str, args);
    }
}
