package hackerearth;

import org.junit.Assert;
import org.junit.Test;

import java.io.PrintWriter;

public class GoldMineTest {

    @Test
    public void testSolve() {
        int[][] A = new int[3][3];
        A[0] = new int[]{3, 2, 1};
        A[1] = new int[]{9, 8, 7};
        A[2] = new int[]{4, 6, 5};

        GoldMine.calc(3, 3, A);
        PrintWriter writer = new PrintWriter(System.out);
        writer.println(GoldMine.solve(1, 1, 3, 3));
        writer.println(GoldMine.solve(1, 2, 2, 2));
        writer.println(GoldMine.solve(2, 2, 3, 3));

        writer.close();
    }

    @Test
    public void testSolvelarge() {
        int[][] A = new int[500][500];
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 500; j++) {
                A[i][j] = 1000;
            }
        }

        GoldMine.calc(500, 500, A);
        PrintWriter writer = new PrintWriter(System.out);
        writer.println(GoldMine.solve(1, 1, 500, 500));
        writer.println(GoldMine.solve(1, 1, 1, 2));
        writer.println(GoldMine.solve(1, 1, 499, 500));
        writer.println(GoldMine.solve(499, 500, 1, 1));

        writer.close();
    }


    @Test
    public void testSolveCompare() {
        int N = 5;
        int M = 3;
        int[][] A = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] = 10;
            }
        }

        GoldMine.calc(N, M, A);
        PrintWriter writer = new PrintWriter(System.out);
        for (int x1 = 1; x1 <= N ; x1++) {
            for (int x2 = x1; x2 <= N; x2++) {
                for (int y1 = 1; y1 <= M; y1++) {
                    for (int y2 = y1; y2 <= M; y2++) {
                        int exp = GoldMine.solveBruteforce(N, M, A, x1, y1, x2, y2);
                        int act = GoldMine.solve(x1, y1, x2, y2);
                        Assert.assertEquals(String.format("not equal for x1:%s, y1:%s, x2:%s, y2:%s", x1, y1, x2, y2), exp, act );
                    }
                }
            }
        }
        Assert.assertTrue(true);

        writer.close();
    }


    //62500000
    //250000000
}