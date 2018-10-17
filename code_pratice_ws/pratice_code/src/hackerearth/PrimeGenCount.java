package hackerearth;

import java.io.PrintWriter;
import java.util.*;

/**
 * Created by prem on 1/9/18.
 *
 * // simpler solution with DP
 * DP[i+x] = Math.min(DP[i+x], DP[i] + 1)
 */
public class PrimeGenCount {

    public void solve(int r1, int r2, int N, String cave, PrintWriter wr) {
        int[] primes = generatePrimes(N);
        int[] rangeCnt = getRangeCount(primes, N);

        char[] cells = cave.toCharArray();

        boolean[] visited = new boolean[N + 1];
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0));
        visited[0] = true;

        int time;
        done:
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int idx = node.idx;
            time = node.time;

            if (cells[idx] == '*') {
                wr.println("No way!");
                return;
            }

            if (idx == N - 1) {
                wr.println(time);
                return;
            }


            // get neighbours
            int idxp1 = idx + 1;
            if (idxp1 < N && !visited[idxp1] && cells[idxp1] == '#') {
                visited[idxp1] = true;
                queue.add(new Node(idxp1, time + 1));
            }

            int idxp2 = idx + 2;
            if (idxp2 < N && !visited[idxp2] && cells[idxp2] == '#') {
                visited[idxp2] = true;
                queue.add(new Node(idxp2, time + 1));
            }

            int A = 0;
            int val = (r2 * rangeCnt[idx + 1])/ r1;
            if (idx + 1 <= val) {
                A = rangeCnt[idx+1];
            }

            if (A > 0) {
                int idxsp = idx + A;
                if (idxsp < N && !visited[idxsp] && cells[idxsp] == '#') {
                    visited[idxsp] = true;
                    queue.add(new Node(idxsp, time + 1));
                }
            }
        }

        wr.println("No way!");
    }

    public int[] getRangeCount(int[] primes, int N) {
        int[] rangecnt = new int[N + 1];
        rangecnt[0] = rangecnt[1] = 0;

        int tillNow = 0;
        int j = 0;
        for (int i = 2; i <= N; i++) {
            if (j < primes.length && primes[j] == i) {
                tillNow++;
                j++;
            }
            rangecnt[i] = tillNow;
        }

        return rangecnt;
    }

    private static class Node {
        int time;
        int idx;
        Node(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public int[] generatePrimes(int range) {
        boolean[] primes = new boolean[range + 1];

        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;

        for (int i = 2; i * i <= range; ++i) {
            for (int j = i * i; j <= range; j += i) {
                primes[j] = false;
            }
        }

        List<Integer> primeArr = new ArrayList<>();

        primeArr.add(2);
        int j = 0;
        for (int i = 3; i < primes.length; i+=2) {
            if (primes[i]) {
                primeArr.add(i);
            }
        }

        return listToArray(primeArr);
    }

    public int getPrimeCountTillNum(int primes[], int num) {

        if (num > primes[primes.length - 1]) {
            return primes.length;
        }
        // binary search
        // search for idx such that primes[idx] <= num and primes[idx+1] > num
        int low = 0;
        int high = primes.length - 1;
        int mid;
        int found = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (primes[mid] == num) {
                found = mid;
                break;
            }

            if (primes[mid] < num) {
                if (mid + 1 < primes.length && primes[mid + 1] > num) {
                    found = mid;
                    break;
                }
                else {
                    low = mid + 1;
                }
            }
            else {
                high = mid - 1;
            }
        }

        return found + 1 ;

    }

    private int[] listToArray(List<Integer> list) {
        int[] num = new int[list.size()];
        int i = 0;
        for (int no : list)
            num[i++] = no;

        return num;
    }
}
