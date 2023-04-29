package leetcode.array;

public class P73SetMatrixZero {
    public void setZeroes(int[][] matrix) {
        setZeroesMap(matrix);
    }

    private void setZeroesMap(int[][] mat) {
            int[] rows = new int[mat.length];
            int[] cols = new int[mat[0].length];

            for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat[0].length; j++) {
                            if (mat[i][j] == 0) {
                                    rows[i] = 1;
                                    cols[j] = 1;
                            }
                    }
            }

            for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat[0].length; j++) {
                            if (rows[i] == 1 || cols[j] == 1) {
                                    mat[i][j] = 0;
                            }
                    }
            }
    }
}
