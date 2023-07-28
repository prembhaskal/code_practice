package leetcode.binsearch;

public class P2141MaxRunTime {
        public long maxRunTime(int n, int[] batteries) {
            long totalsum = 0;
            for (int bat : batteries) {
                totalsum = totalsum + bat;
            }
            long low = 1;
            long high = totalsum / n;

            long ans = -1;
            while(low <= high) {
                long mid = low + (high - low) / 2;
                if (canReachTarget(n, batteries, mid)) {
                    ans = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return ans;
        }

        boolean canReachTarget(int n, int[] batt, long target) {
            long totalavail = 0;
            for (int bat : batt) {
                totalavail = totalavail + min(bat, target);
            }

            long need = (long)n * target;
            return need <= totalavail;
        }

        long min(long a, long b ) {
            return a < b ? a : b;
        }
}
