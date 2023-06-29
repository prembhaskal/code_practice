package hackerearth;

import java.io.*;
import java.util.*;


public class SubArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        for (int t_i = 0; t_i < T; t_i++) {
            int N = Integer.parseInt(br.readLine().trim());
            String[] arr_Arr = br.readLine().split(" ");
            int[] Arr = new int[N];
            for (int i_Arr = 0; i_Arr < arr_Arr.length; i_Arr++) {
                Arr[i_Arr] = Integer.parseInt(arr_Arr[i_Arr]);
            }

            int out_ = SubAndSuperArray(Arr, N);
            wr.println(out_);
        }

        wr.close();
        br.close();
    }

    static int SubAndSuperArray(int[] Arr, int N) {
        // Write your code here
        if (N == 1 ) {
            return 1;
        }

        // end to start
        int mx = 1;
        int ln = 1;

        int[] mlen = new int[N];
        mlen[N-1] = 1;


        int[] rlen = new int[N];
        rlen[N-1] = 1;

        for (int i = N-2; i>= 0; --i) {
            if (Arr[i] < Arr[i+1]) {
                ln++;
                if (mx < ln) {
                    mx = ln;
                    mlen[i] = mx;
                }
                else {
                    mlen[i] = mlen[i+1]; // same as old
                }
            }
            else {
                mlen[i] = mlen[i+1];
                ln = 1;
            }

            rlen[i] = ln;

        }

        // start to end
        int max = -1;
        int len = 1;

        int[] slen = new int[N];
        slen[0] = 1;

        for (int i = 1; i < N; ++i) {
            if (Arr[i] > Arr[i-1]) {
                len++;
                if (max < len) {
                    max = len;
                }
            }
            else {
                len = 1;
            }

            slen[i] = len;
        }

        //mxEnd, mlens, mxStart, mlen
        int mxt = 0;
        mxt = 0;

        // tail table as done in longest increasing sub sequences
        int[] tt = new int[N+1];

        tt[0] = 0;
        tt[1] = Arr[N-1];
        len = 0;

        for (int i = N - 2 ; i >= 0 ; --i) {
            int x = Arr[i];
            int mlenhere;

            mlenhere = getIdx(tt, 1, mlen[i+1], x);

            if (mlenhere > 0 && mxt < mlenhere + slen[i]) {
                mxt = mlenhere + slen[i];
            }

            if (Arr[i] > tt[rlen[i]]) {
                tt[rlen[i]] = Arr[i];
            }
        }

        return mxt;
    }

    // tt is in desceding order
    static int getIdx(int[] tt, int st, int end, int key) {

        if (tt[st] < key) {
            return 0;
        }
        int m = 0;
        if (st == end) {
            return 1;
        }

        while (st < end) {
            m = (st + end)/2;
            if (tt[m] > key && key > tt[m+1]) {

                return m;
            }
            else if (tt[m] > key) {
                st = m+1;
            }
            else {
                end = m-1;
            }

        }

        return st;
    }
}
