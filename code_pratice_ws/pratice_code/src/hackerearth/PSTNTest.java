package hackerearth;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
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
        long brkPower = 0;

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
        P = 7625597484987L;
        A = new int[]{3,3,2,3,1,1,1,1,1,1,3};
        exp = 5;

        brkPower = PSTN.solve1(N, P, A);
        System.out.println("brkPower exp " + exp + " actual: " + brkPower);
    }

    @Test
    public void testBrkPwrVsStrength() {
//        System.out.println(Long.MAX_VALUE);
//        PSTN.getPwrVsStr("1233", 4);
//        PSTN.getPwrVsStr("12123221", 8);
//        PSTN.getPwrVsStr("33222233", 8);
//        long[] pwrVsStr = PSTN.getPwrVsStr("313231323113231313323113231313313231323113231313323113231313313231323113231313323113231313313231323113231313323113231313", 120);
//        long[] pwrVsStr = PSTN.getPwrVsStr("313231323113231313323121323131", 30, 0, );
//        long[] pwrVsStr = PSTN.getPwrVsStr("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111", 120);
//        System.out.println(" break power vs strength for string " + "333333333333" + " is  ");
//
//        for (int i = 1; i < pwrVsStr.length; i++) {
//            System.out.println("brk:  " + i + " strength: " + pwrVsStr[i]);
//        }



        int N;
        long P;
        int[] A;
        int exp;

//        P = 38128255860392L;
        P = 200000000000000000L;
//        A = new int[]{3,1,3,2,3,1,3,2,3,1,1,3,2,3,1,3,1,3,3,2,3,1,1,3,2,3,1,3,1,3,3,1,3,2,3,1,3,2,3,3,1,3,2,3,1,3,2,3,1,1,3,2,3,1,3,1,3,3,2,3,1,1,3,2,3,1,3,1,3,3,1,3,2,3,1,3,2,3,3,1,3,2,3,1,3,2,3,1,1,3,2,3,1,3,1,3,3,2,3,1,1,3,2,3,1,3,1,3,3,1,3,2,3,1,3,2,3};
        A = new int[]{1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3};
        N = A.length;
        long brkPower = 0;

        brkPower = PSTN.solve1(N, P, A);
        System.out.println("brkPower is " + brkPower);

    }

    @Test
    public void testMaxVal() {
//        int[] A = new int[]{1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3};
        int[] A = new int[]{1, 2, 1, 2, 3, 2, 2, 1};
        int N = A.length;

        long maxVal = PSTN.maxValueFromStr(A, N);
        System.out.println(maxVal);
    }

    @Test
    public void testWithQueue() {
        int N;
        long P;
        int[] A;
        int exp;
        StringBuilder sb;
        long[] minBrk;
//
//        N = 4;
//        P = 7000;
//        A = new int[]{1,2,3,3};
//
//        sb = new StringBuilder();
//        for (int i = 0; i < N; i++) {
//            sb.append(A[i]);
//        }
//
//        minBrk = PSTN.getMinBrk(sb.toString(), N, 0, P);
//        System.out.println("debug -- " + Arrays.toString(minBrk));
//
//        N =11;
//        P = 7625597484987L;
//        A = new int[]{3,3,2,3,1,1,1,1,1,1,3};
//
//        sb = new StringBuilder();
//        for (int i = 0; i < N; i++) {
//            sb.append(A[i]);
//        }
//
//        minBrk = PSTN.getMinBrk(sb.toString(), N, 0, P);
//        System.out.println("debug -- " + Arrays.toString(minBrk));

        P = 200000L;
        A = new int[]{3,1,3,2,3,1,3,2,3,1,1,3,2,3,1,3,1,3,3,2,3,1,1,3,2,3,1,3,1,3,3,1,3,2,3,1,3,2,3,3,1,3,2,3,1,3,2,3,1,1,3,2,3,1,3,1,3,3,2,3,1,1,3,2,3,1,3,1,3,3,1,3,2,3,1,3,2,3,3,1,3,2,3,1,3,2,3,1,1,3,2,3,1,3,1,3,3,2,3,1,1,3,2,3,1,3,1,3,3,1,3,2,3,1,3,2,3};
        N = A.length;

        sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(A[i]);
        }

        minBrk = PSTN.getMinBrk(sb.toString(), N, 0, P);
        System.out.println("debug -- " + Arrays.toString(minBrk));


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