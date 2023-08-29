package leetcode.array;

public class P2483MinPenalty {
    public int bestClosingTime(String customers) {
        // return usingPrefixSum(customers);
        // return usingCount(customers.toCharArray());
        return singlePass(customers.toCharArray());
    }

    // calc max score instead of penalty
    int singlePass(char[] chars) {
        int max = 0;
        int score = 0;
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'Y') {
                score++;
            } else {
                score--;
            }
            if (score > max) {
                max = score;
                ans = i + 1;
            }
        }

        return ans;
    }

    int usingCount(char[] chars) {
        // var chars = cust.toCharArray();
        int n = chars.length;
        int totalY = 0;
        for (char ch : chars) {
            if (ch == 'Y') {
                totalY++;
            }
        }

        if (totalY == n) {
            return n;
        }

        int min = 1000_000;
        int ans = n + 1;
        int NCount = 0;
        for (int i = 0; i <= n; i++) {
            int prevN = NCount;
            int nextN = totalY - (i - NCount); // i - NCount gives Y till now.
            if (i < n) {
                if (chars[i] == 'Y') {
                    nextN++;
                } else {
                    NCount++;
                }
            }

            int penalty = prevN + nextN;
            if (penalty < min) {
                min = penalty;
                ans = i;
            }
        }

        return ans;
    }

    int usingPrefixSum(String cust) {
        var chars = cust.toCharArray();
        int n = chars.length;
        int[] RY = new int[n];// rev Y counts
        int prev = 0;
        for (int i = n - 1; i >= 0; i--) {
            RY[i] = prev;
            if (chars[i] == 'Y') {
                RY[i]++;
            }
            prev = RY[i];
        }

        int minPenalty = 1000_000;
        int ans = n + 1;
        int NCount = 0;
        // check shop close at each hour
        for (int i = 0; i <= n; i++) {
            // if shop closes at i
            int NPenal = NCount;
            if (i < n && chars[i] == 'N') {
                NCount++;
            }

            int YPenal = 0;
            if (i < n) {
                YPenal = RY[i];
            }
            int totalPenalty = NPenal + YPenal;
            if (totalPenalty < minPenalty) {
                minPenalty = totalPenalty;
                ans = i;
            }


            // System.out.printf("idx: %d, penalty: %d, ans: %d, min-penalty: %d\n", i, totalPenalty, ans, minPenalty);
        }

        return ans;
    }


}
