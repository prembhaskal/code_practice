package leetcode.array;

public class P134GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // System.out.printf("new gas: %s, cost: %s\n", Arrays.toString(gas), Arrays.toString(cost));
        // start from valid point and rotate
        int n = gas.length;
        // int[] canreach = new int[n];
        // Arrays.fill(canreach, -1); // -1 mean not sure, 0 - No, 1 - Yes
        for (int i = 0; i < n; i++) {
            // check if we can start from here and finish
            int totalgas = 0;
            boolean possible = true;
            int startIdx = i;

            int stops = doTrip(i, gas, cost);
            // System.out.printf("startIdx: %d, stops: %d\n", i, stops);
            if (stops == n) {
                return i;
            }
            int idx = i + stops;

            // try from next of last failed.
            if (idx >= n) { // means full one round is done
                return -1;
            }
            i = idx;
        }

        return -1;
    }

    int doTrip(int idx, int[] gas, int[] cost) {
        int j = 0;
        int totalgas = 0;
        for (; j < gas.length; j++) {
            // int idx = (startIdx + j) % gas.length;
            if (totalgas + gas[idx] < cost[idx]) {
                break;
            }
            totalgas += (gas[idx] - cost[idx]);
            idx++;
            if (idx == gas.length) {
                idx = 0;
            }
        }
        return j;
    }
}
