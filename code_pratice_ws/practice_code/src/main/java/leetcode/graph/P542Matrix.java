package leetcode.graph;

import java.util.ArrayDeque;

public class P542Matrix {

    public int[][] updateMatrix(int[][] mat) {
        // return solveWithBFS(mat);
        return solveWithDFS(mat);
    }

    int[][] solveWithDFS(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // int[][] upd = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 0) {
                    mat[i][j] = 1000_000;
                }
            }
        }


        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 0) {
                    visited[i][j] = true;
                    dfs(mat, visited, i, j);
                    // upd[i][j] = dist;
                }
            }
        }

        return mat;
    }

    int dfs(int[][] mat, boolean[][] visited, int i, int j) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        int mindist = 1000_000;
        int m = mat.length;
        int n = mat[0].length;

        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                if (mat[ni][nj] == 0) {
                    mindist = 1;
                    break;
                }
                // if (mat[ni][nj] == 1000_000) {
                int newdist;
                if (!visited[ni][nj]) {
                    visited[ni][nj] = true;
                    newdist = dfs(mat, visited, ni, nj);
                } else {
                    newdist = mat[ni][nj];
                }
                // System.out.printf("i:%d,j:%d, ni:%d,nj:%d, newdist:%d\n",i,j,ni,nj,newdist);
                mindist = Math.min(mindist, newdist + 1);
                // }
            }
        }

        mat[i][j] = mindist;
        return mindist;
    }

    // from each zero bfs to all sides and update accordingly.
    int[][] solveWithBFS(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        var queue = new ArrayDeque<Cell>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    mat[i][j] = 1000_000;
                } else {
                    queue.add(new Cell(i, j));
                }
            }
        }


        while (!queue.isEmpty()) {
            var pcell = queue.removeLast();
            var currdist = mat[pcell.i][pcell.j];
            int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
            for (int[] dir : dirs) {
                var ni = pcell.i + dir[0];
                var nj = pcell.j + dir[1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n) {
                    int newdist = currdist + 1;
                    if (mat[ni][nj] == 1000_000) {
                        mat[ni][nj] = newdist;
                        queue.addFirst(new Cell(ni, nj));
                    }
                }
            }
        }

        return mat;
    }


}

class Cell {
    int i;
    int j;

    Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
