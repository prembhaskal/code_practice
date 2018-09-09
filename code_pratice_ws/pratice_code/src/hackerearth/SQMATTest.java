package hackerearth;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by prem on 9/9/18.
 */
public class SQMATTest {


    @Test
    public void solve() {
        int N;
        int M;
        String[] A;
        int[] coord;

        N = 6;
        M = 6;
        A = new String[]
                {
                "abacdc",
                "babcdd",
                "abaeiw",
                "qqqcdc",
                "qqqfff",
                "qqocdc",
        };

        coord = SQMAT.solve(A, N, M);
        System.out.println("result is " + Arrays.toString(coord));
    }

    @Test
    public void solveLarge() {
        int N;
        int M;
        String[] A;
        int[] coord;

        N = 500;
        M = 500;
        A = new String[N];

        for (int i = 0; i < N; i++) {
            char[] chr = new char[M];
            for (int j = 0; j < M; j++) {
                chr[j] = 'a';
            }

            A[i] = new String(chr);
        }

        coord = SQMAT.solve(A,N,M);
        System.out.println("result is " + Arrays.toString(coord));
    }

    /*

abacdc
babcdd
abaeiw
qqqcdc
qqqfff
qqocdc

     */

}