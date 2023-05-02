package leetcode.array;

public class P48Rotateimage {
    public void rotate(int[][] matrix) {
        // printMatrix(matrix);
        transpose(matrix);
        reverseRows(matrix);
        // printMatrix(matrix);
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
