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

    /*

abacdc
babcdd
abaeiw
qqqcdc
qqqfff
qqocdc

     */

}