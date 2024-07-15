package gfg.string;

// GFG https://www.geeksforgeeks.org/problems/minimum-times-a-has-to-be-repeated-such-that-b-is-a-substring-of-it--170631/1?page=1&sortBy=submissions
public class MinRepeat {

    public static void main(String[] args) {
        int minRepeat = minRepeats1("abcd", "cdabcdab");
//        int minRepeat = minRepeats1("ab", "cab");
        System.out.printf("min repeated is %d\n", minRepeat);
    }
    static int minRepeats(String A, String B) {
        // code here
        String Ar = A;
        int repeat = 1;
        while (Ar.length() < B.length()) {
            Ar = Ar + A;
            repeat++;
        }

        if (Ar.contains(B)) {
            return repeat;
        }

        Ar = Ar + A;
        repeat++;
        if (Ar.contains(B)) {
            return repeat;
        }
        return -1;
    }

    // use less memory
    static int minRepeats1(String A, String B) {
        int an = A.length();
        int bn = B.length();
        for (int i = 0; i < an; i++) {
            // try matching A[i...cycle back ...i-1]A[i...cycle ... i-1] with B[0...last]
            int aidx = i;
            int bidx = 0;
            int repeat = 1;
            while (bidx < bn && A.charAt(aidx) == B.charAt(bidx)) {
                if (bidx == bn - 1) {
                    return repeat;
                }
                aidx++;
                aidx = aidx % an;
                if (aidx == 0) {
                    repeat++; // A is repeated
                }
                bidx++;
            }
        }

        return -1;
    }
}
