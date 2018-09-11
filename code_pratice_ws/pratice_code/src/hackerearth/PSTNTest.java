package hackerearth;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by prem on 8/9/18.
 */
public class PSTNTest {


    @Test
    public void test() {
        int N;
        long P;
        int[] A;
        int exp;

        N = 4;
        P = 7000;
        A = new int[]{1,2,3,3};
        long brkPower;

        brkPower = PSTN.solve1(N, P, A);
        System.out.println("brkPower is " + brkPower);

        N = 8;
        P = 8000;
        A = new int[]{1, 2, 1, 2, 3, 2, 2, 1};
        exp = 7156;

        brkPower = PSTN.solve1(N, P, A);
        System.out.println("brkPower exp " + exp + " actual: " + brkPower);

        N = 5;
        P = 8000;
        A = new int[]{1, 2, 1, 2, 1};
        exp = 7998;

        brkPower = PSTN.solve1(N, P, A);
        System.out.println("brkPower exp " + exp + " actual: " + brkPower);

        N = 8;
        P = 8000;
        A = new int[]{3, 3, 2, 2, 2, 2, 3, 3};

        brkPower = PSTN.solve1(N, P, A);
        System.out.println("brkPower actual: " + brkPower);

        N =11;
        P = 7625597484987l;
        A = new int[]{3,3,2,3,1,1,1,1,1,1,3};
        exp = 5;

        brkPower = PSTN.solve1(N, P, A);
        System.out.println("brkPower exp " + exp + " actual: " + brkPower);
    }

    @Test
    public void testPow() {
        Set<Long> st = new TreeSet<>();
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    long num = PSTN.power(j, k);
                    long pow = PSTN.power(i, num);
                    st.add(pow);
                    System.out.println(String.format("values i: %s, j: %s, k: %s, num: %s, pow: %s", i, j, k, num, pow));
                }
            }
        }

        for (long num: st) {
            System.out.println(num);
        }
    }

}