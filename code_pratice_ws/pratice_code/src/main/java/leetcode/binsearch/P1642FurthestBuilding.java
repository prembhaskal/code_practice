package leetcode.binsearch;

import java.util.*;

class P1642FurthestBuilding {

    public static void main(String[] args) {
        var inst = new P1642FurthestBuilding();
        var h = new int[]{4,2,7,6,9,14,12};
        var b = 5;
        var l = 1;

        System.out.println(inst.furthestBuilding(h, b, l));
    }
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int start = 0;
        int end = heights.length - 1;

        // 0 1 2
        // start 0 end 2 mid (2+1)/2=1, start 1, end 2, mid (1+2+1)/2 = 2
        while(start < end) {
            int mid = (start + end + 1) / 2; // +1, so that if START and END are both reachable, then mid should truncates towards END.
            boolean canReach = isReachable(mid, heights, bricks, ladders);
            System.out.printf("can reach build %s, answer %s\n", mid, canReach);
            if (canReach) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }



    private boolean isReachable(int x, int[] heights, int bricks, int ladders) {
        List<Integer> climbs = new ArrayList<>();

        for (int i = 1; i <= x; i++) {
            if (heights[i] > heights[i - 1]) {
                climbs.add(heights[i] - heights[i - 1]);
            }
        }

        Collections.sort(climbs, (ob1, ob2) -> ob2 - ob1);

        int total = 0;
        // skip the ladders
        for (int i = ladders; i < climbs.size(); i++) {
            total = total + climbs.get(i);
            if (total > bricks) {
                return false;
            }
        }

        return true;
    }


}