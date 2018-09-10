package hackerearth;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SubArray {

    public static void main(String[] args) {
        // generate random arrays with distinct nos..

//        genRandomNos();
        superArray(null, 1);
    }

    private static void genRandomNos() {
        Set<Integer> nos = new HashSet<>();

        while (nos.size() < 30) {
            nos.add(new Random().nextInt(100) + 1);
        }

        for (int no : nos) {
            System.out.print(no);
            System.out.print(" ");
        }

        System.out.println("");
    }

    static int superArray(int[] Arr, int N) {
        int[] tt = new int[]{0, 31, 29, 26, 22, 21, 20, 19, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        int key;
        key = 17;
        int maxlen = binSearch(tt, 1, 8, key);
        System.out.println("maxlen is " + maxlen + " for key: " + key);
        return 0;
    }

    static int binSearch(int[] tt, int st, int end, int key) {
        if (tt[end] < key) {
            return 0;
        }

        if (st == end) {
            return 1;
        }
        int m = 0;
        while (st < end) {
            m = (st + end) / 2;
            if (tt[m] > key && key > tt[m+1]) {
                return m;
            }
            else if (tt[m] > key) {
                st = m + 1;
            }
            else {
                end = m - 1;
            }
        }

        return st;
    }
}


/*
good tests
12
15 12 13 14 17 4 8 19 20 10 1 18
20
34 35 4 37 5 6 40 9 11 14 15 17 18 19 20 21 22 26 29 31
4
8 9 1 4
 */
