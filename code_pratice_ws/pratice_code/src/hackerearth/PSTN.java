package hackerearth;

import java.io.*;
import java.util.*;

/**
 * Created by prem on 8/9/18.
 * Correct solution is to implement below DP  :( this is problem setters solution.
 *
 * #include <bits/stdc++.h>
 #define lli long long

 using namespace std;

 lli A[502];
 bool vis[502][502];
 lli dp[502][502];

 lli fast_pow(lli a, lli b)
 {
 lli res = 1;
 while ( b > 0 ) {
 if ( b&1 ) res = (res*a);
 a = (a*a);
 b >>= 1;
 }
 return res;
 }

 lli f(int l, int r)
 {
 if ( vis[l][r] ) return dp[l][r];
 vis[l][r] = true;
 lli ans = 0;
 for ( int i = l+1; i <= r-1; i++ ) ans = max(ans, -A[i] + fast_pow(A[i], fast_pow(A[l],A[r])) + f(l,i) + f(i,r));
 dp[l][r] = ans;
 return ans;
 }

 int main()
 {
 int n;
 lli P;
 cin >> n >> P;
 assert(n >= 1 && n <= 500);
 assert(P >= 1 && P <= 1000000000000000000LL);
 for ( int i = 0; i < n; i++ ) cin >> A[i], assert(A[i] >= 1 && A[i] <= 3);
 lli ans = P - f(0,n-1);
 assert(ans >= 0);
 cout << ans << endl;
 return 0;
 }

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

    private static Map<String, Map<Long, Long>> strVsPowerVsBrkPower;
    private static PrintWriter wr = new PrintWriter(System.out);

    private static long[] brkVsmaxStr;

    static long solve1(int N, long P, int[] A) {

//        if (N > 50) {
//            return Math.min(
//                    deleteMaxStrategy(N, P, Arrays.copyOf(A, A.length)),
//                    deleteOneThenMaxStrategy(N, P, Arrays.copyOf(A, A.length))
//            );
//        }
//
//

        strVsVal = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(A[i]);
        }

        int totalSum = 0;
        for (int i = 1; i < N - 1; i++) {
            totalSum += A[i];
        }

        long maxValPossible = maxValueFromStr(A, N);
        if (P > maxValPossible) {
            return P - maxValPossible + totalSum;
        }

        brkVsmaxStr = new long[totalSum + 1];

        long[] pwrVsStr = getPwrVsStr(sb.toString(), N, 0, P);

        for (int i = 1; i < pwrVsStr.length; i++) {
            if (pwrVsStr[i] >= P)
                return i;
        }

        return P - pwrVsStr[pwrVsStr.length-1] + pwrVsStr.length - 1;
    }

    private static Map<String, long[]> strVsVal = new HashMap<>();


    static long maxValueFromStr(int[] A, int len) {

        // remove all 1s
        long pwr = 0;
        int N = len;
        for (int i = 1; i < len-1; i++) {
            if (A[i] == 1) {
                A[i] = A[i+1];
                pwr++;
                N--;
            }
        }

//        remove 2s with max value
        for (int size = len; size > 2; size--) {
            int twoIdx = -1;
            long maxVal = -1;
            for (int i = 1; i < size - 1; i++) {
                if (A[i] == 2) {
                    long val = power(A[i], power(A[i-1], A[i+1]));
                    if (val > maxVal) {
                        twoIdx = i;
                        maxVal = val;
                    }
                }


                if (twoIdx != -1) {
                    pwr = pwr + maxVal;
                    // shift left
                    for (int k = twoIdx; k < size-1; k++) {
                        A[k] = A[k+1];
                    }
                }
                else {
                    break;
                }
            }

        }

//        remove 3s with max value
        for (int size = len; size > 2; size--) {
            int maxValIdx = -1;
            long maxVal = -1;
            for (int i = 1; i < size - 1; i++) {
                long val = power(A[i], power(A[i-1], A[i+1]));
                if (val > maxVal) {
                    maxValIdx = i;
                    maxVal = val;
                }


                if (maxValIdx != -1) {
                    pwr = pwr + maxVal;
                    // shift left
                    for (int k = maxValIdx; k < size-1; k++) {
                        A[k] = A[k+1];
                    }
                }
                else {
                    break;
                }
            }
        }

        return pwr;
    }



    /// TLE :( :(
    static long[] getPwrVsStr(String str, int len, int brkSpent, long P) {
        if (strVsVal.containsKey(str)) {
            return strVsVal.get(str);
        }

        if (P <= 0) {
            return new long[]{};
        }

        if (len <= 2) {
            return new long[]{};
        }

        int totalSum = 0;
        for (int i = 1; i < len - 1; i++) {
            totalSum += str.charAt(i) - '0';
        }

        long[] pwrVsStr = new long[totalSum + 1];

        for (int i = 1; i < len - 1; i++) {
            int pwr = str.charAt(i) - '0';
            long stn = power(str.charAt(i) - '0', power(str.charAt(i-1) - '0', str.charAt(i+1) - '0'));
            String newStr = new StringBuilder(str).deleteCharAt(i).toString();

            long[] belowMax = getPwrVsStr(newStr, len - 1, brkSpent + pwr, P - stn);
            pwrVsStr[pwr] = Math.max(pwrVsStr[pwr], stn); // if breaking current stone is enough.

            for (int j = 1; j < belowMax.length; j++) {
                long newStn = stn + belowMax[j];
                if (newStn < 0) {
                    throw new RuntimeException("should not happen");
                }
                pwrVsStr[j + pwr] = Math.max(pwrVsStr[j + pwr], newStn);
//                brkVsmaxStr[j + pwr] = Math.max(pwrVsStr[j + pwr], brkVsmaxStr[j + pwr]); // global max
            }
        }

//        System.out.println(" break power vs strength for string " + str + " is  ");
//
//        for (int i = 1; i < pwrVsStr.length; i++) {
//            System.out.println("brk:  " + i + " strength: " + pwrVsStr[i]);
//        }

        strVsVal.put(str, pwrVsStr);
        return pwrVsStr;
    }

    static class Node {
        String str;
        int brkSpent;
        long remStn;

        public Node(String str, int brkSpent, long remStn) {
            this.str = str;
            this.brkSpent = brkSpent;
            this.remStn = remStn;
        }
    }

    // brkVsRemP[i] will have min remP achieved with breaking stone of i strength.
    private static long[] brkVsRemP;

    static long[] getMinBrk(String str, int N, int brkSpent, long P) {

        int totalSum = 0;
        for (int i = 1; i < N - 1; i++) {
            totalSum += str.charAt(i) - '0';
        }

        brkVsRemP = new long[totalSum+1];
        Arrays.fill(brkVsRemP, P);

        Queue<Node> qu = new ArrayDeque<>();
        qu.add(new Node(str, brkSpent, P));
        HashSet<String> set = new HashSet<>();

        while (!qu.isEmpty()) {
            Node node = qu.poll();
            String currStr = node.str;
            set.add(currStr);

            long remStn = node.remStn;
            int currBrk = node.brkSpent;

            for (int i = 1; i < currStr.length() - 1; i++) {
                int pwr = currStr.charAt(i) - '0';
                long stn = power(currStr.charAt(i) - '0', power(currStr.charAt(i-1) - '0', currStr.charAt(i+1) - '0'));
                String newStr = new StringBuilder(currStr).deleteCharAt(i).toString();
                if (remStn - stn > 0 && !set.contains(newStr)) {
                    qu.add(new Node(newStr, currBrk + pwr, remStn - stn));
                }

                if (remStn - stn <= 0) {
                    brkVsRemP[currBrk+pwr] = 0;
                }
                else {
                    brkVsRemP[currBrk+pwr] = Math.min(brkVsRemP[currBrk+pwr], remStn - stn);
                }
            }
        }

        return brkVsRemP;

    }

    static boolean alreadyAchievedWithLessBrk(long[] brkVsStr, int newBrk, long P) {
        for (int i = 0; i < brkVsStr.length; i++) {
            if (i < newBrk && brkVsStr[i] >= P) {
                return true;
            }
        }
        return false;
    }



//    // brkPower,
//    static long getMinBrkPwr(String str, int len, long P) {
//        if (P <= 0) {
//            return 0;
//        }
//
//        if (strVsPowerVsBrkPower.containsKey(str)) {
//            Map<Long, Long> map = strVsPowerVsBrkPower.get(str);
//            wr.println(String.format("searching for P:%s and str::%s -- current map: %s", P, str, map.toString()));
//            for (Map.Entry<Long, Long> e : map.entrySet()) {
//                long Px = e.getKey();
//                if (Px >= P) {
//                    wr.println(String.format("got value for P:%s and str::%s and Px:%s, value:%s", P, str, Px, e.getValue()));
////                    return e.getValue();
//                }
//            }
//        }
//
//
//        if ( len <= 2) {
//            return P;
//        }
//
//        long minBrkPower = Long.MAX_VALUE;
//        for (int i = 1; i < len - 1; i++) {
//            // delete i
//            long brkPwr = str.charAt(i) - '0';
//            long redStrn = power(str.charAt(i) - '0', power(str.charAt(i-1) - '0', str.charAt(i+1) - '0'));
//
//            String newStr = new StringBuilder(str).deleteCharAt(i).toString();
//            brkPwr = brkPwr + getMinBrkPwr(newStr, len - 1, P - redStrn);
//
//            minBrkPower = Math.min(minBrkPower, brkPwr);
//        }
//
//        Map<Long, Long> map = strVsPowerVsBrkPower.get(str);
//        if (map == null) {
//            map = new TreeMap<>();
//
//            strVsPowerVsBrkPower.put(str, map);
//        }
//
//        wr.println(String.format("putting value P:%s, pwr:%s for str:: %s", P, minBrkPower, str));
//        map.put(P, minBrkPower);
//
//        return minBrkPower;
//    }
//

    static long deleteOneThenMaxStrategy(int N, long P, int[] A) {

        long minBreakPower = Long.MAX_VALUE;

        long brkPower = 0;

        for (int size = N; size > 2; size--) {

            // see if there is a 1
            int oneIdx = -1;
            for (int j = 1; j < size - 1; j++) {
                if (A[j] == 1) {
                    oneIdx = j;
                    break;
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

    static long deleteMaxStrategy(int N, long P, int[] A) {
        long minBreakPower = Long.MAX_VALUE;
        long brkPower = 0;

        for (int size = N; size > 2; size--) {

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

            if (maxVal > P) { // can we break with current val.
                brkPower = brkPower + A[maxValIdx];
                P = 0;
                minBreakPower = Math.min(P + brkPower, minBreakPower);
                break;
            }
            else {
                brkPower = brkPower + A[maxValIdx];
                P = P - maxVal;
                brkIdx = maxValIdx;
            }


            minBreakPower = Math.min(P + brkPower, minBreakPower);

            // shift left
            System.arraycopy(A, brkIdx + 1, A, brkIdx, size - 1 - brkIdx);
        }

        return minBreakPower;
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
