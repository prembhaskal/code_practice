package hackerearth;

import java.io.*;
import java.util.*;

/**
 * Created by prem on 8/9/18.
 */
public class PSTN {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        String[] inp = br.readLine().split(" ");

        int N = Integer.parseInt(inp[0]);
        long P = Long.parseLong(inp[1]);

        int[] A = new int[N];
        String[] inp1 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(inp1[i]);
        }

        long out_ = solve1(N,P,A);
        wr.println(out_);

        wr.close();
        br.close();
    }

    static class Node {
        int idx;
        int strn;
        Node prev;
        Node next;
        long val;

        public Node(int idx, int strn, Node prev, long val) {
            this.idx = idx;
            this.strn = strn;
            this.prev = prev;
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return idx == node.idx;

        }

        public void calcPower() {
            int prevVal = (prev != null) ? prev.strn : 0;
            int nextVal = (next != null) ? next.strn : 0;
            if (prevVal > 0 && nextVal > 0) {
                val = power(strn, power(prevVal,nextVal));
            }
            else {
                val = 0;
            }
        }

        @Override
        public int hashCode() {
            return idx;
        }

        @Override
        public String toString() {
            return String.format("idx: %s, strn: %s, val %s, prev: %s, next: %s",
                    idx, strn, val, (prev != null) ? prev.idx: -1, (next != null) ? next.idx: "last");
        }
    }

    static long solve1(int N, long P, int[] A) {

        long minBreakPower = Long.MAX_VALUE;

        long brkPower = 0;

        for (int size = N; size > 2; size--) {

            // see if there is a 1
            int oneIdx = -1;
            for (int j = 1; j < size - 1; j++) {
                if (A[j] == 1) {
                    oneIdx = j;
                }
            }

            // get max val
            int maxValIdx = 1;
            long maxVal = -1;
            for (int j = 1; j < size-1; j++) {
                long val = power(A[j], power(A[j-1], A[j+1]));
                if (maxVal < val) {
                    maxVal = val;
                    maxValIdx = j;
                }
            }

            int brkIdx;

            if (P == 1) {
                P = 0;
                brkPower++;
                minBreakPower = Math.min(brkPower, minBreakPower);
                break;
            }
            else if (maxVal > P) { // can we break with current val.
                brkPower = brkPower + A[maxValIdx];
                P = 0;
                minBreakPower = Math.min(P + brkPower, minBreakPower);
                break;
            }
            else if (oneIdx != -1) { // break 1
                P = P - 1;
                brkPower = brkPower + 1;
                brkIdx = oneIdx;
            }
            else {
                brkPower = brkPower + A[maxValIdx];
                P = P - maxVal;
                brkIdx = maxValIdx;
            }


            minBreakPower = Math.min(P + brkPower, minBreakPower);

            // shift left
            for (int i = brkIdx; i < size-1; i++) {
                A[i] = A[i+1];
            }
        }

        return minBreakPower;
    }


    static long solve(int N, long P, int[] A) {
        boolean debug = true;
        if (debug){
            System.out.println(Arrays.toString(A));
        }

        // fill queue
        Node prev = null;
        Node next =null;
        for (int i = 0; i < N; i++) {
            int strn = A[i];
            int prevVal = (i > 0) ? A[i-1] : 0;
            int nextVal = (i < N-1) ? A[i+1] : 0;
            long val = 0;
            if (prevVal > 0 && nextVal > 0)
            {
                val = power(A[i], power(prevVal, nextVal));
            }
            else {
                val = 0;
            }

            Node curr = new Node(i, strn, prev, val);
            if (prev != null) {
                prev.next = curr;
            }
            prev = curr;
        }

        PriorityQueue<Node> heap = new PriorityQueue<>((Comparator<Node>) (o1, o2) -> {

            if (o1.strn == 1) {
                return -1;
            }
            else if (o2.strn == 1) {
                return 1;
            }
            else {
                if (o1.val > o2.val){
                    return -1;
                }
                else {
                    return 1;
                }
            }
        });

        while (prev != null) {
//            System.out.println(prev);
            if (prev.next != null && prev.prev != null) {
                heap.add(prev);
            }
            prev = prev.prev;
        }

//        System.out.println(heap.size());

        long minBrkPower = Long.MAX_VALUE;
        long brkPower = 0;
        while (!heap.isEmpty()) {
            Node curr = heap.poll();

            // reorder
            prev = curr.prev;
            next = curr.next;

            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }

            // reorder in heap
            if (prev != null) {
                heap.remove(prev);
                prev.calcPower();
                heap.add(prev);
            }

            if (next != null) {
                heap.remove(next);
                next.calcPower();
                heap.add(next);
            }

            // break curr stone.
            brkPower = brkPower + curr.strn;
            P = P - curr.val;
            if (P < 0) {
                P = 0;
            }
            minBrkPower = Math.min(brkPower + P, minBrkPower);

            if (P == 0) {
                break;
            }
        }



        return minBrkPower;
    }


    static long power(long num, long pow) {
        long prod = 1;

        while (pow > 0) {
            if ((pow&1)==1) {
                prod = (prod * num);
            }

            num = (num * num);
            pow /= 2;
        }

        return prod;
    }
}
