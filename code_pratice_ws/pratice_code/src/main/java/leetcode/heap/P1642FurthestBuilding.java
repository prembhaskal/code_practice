package leetcode.heap;

import java.util.PriorityQueue;

// there is another approach using binary search, check other package.
class P1642FurthestBuilding {

    public static void main(String[] args) {
        var inst = new P1642FurthestBuilding();
        var h = new int[]{14,3,19,3};
        var b = 17;
        var l = 0;

        System.out.println(inst.furthestBuilding(h, b, l));
    }

    //    > use backtracking greedy
//    every jump,
//   if ladder available use it,
//    store jump in min-heap.
//
//   else  if curr_jump > peek(heap) // if ladder not available,
//     prev_jump = extract-min(heap)
//       heap.add(curr_jump)  //    use ladder in current jump
//    curr = curr - peek(heap)
// use brick instead of ladder in prev. jump
//   curr_jump = prev_jump
//   else if bricks > curr_jump
//     use bricks
//
//   else
//     stop
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int i = 0;

        for (; i < heights.length - 1; i++) {
            int jump = heights[i + 1] - heights[i];
            if (jump <= 0) {
                continue;
            }
            if (ladders > 0) {
                ladders--;
                heap.add(jump);
                continue;
            }

            // all ladders used

            if (heap.size() > 0) {
                // check prev ladders
                // check smallest jump used for ladder
                int smallLadJump = heap.peek();
                if (jump > smallLadJump) {
                    heap.poll();
                    heap.add(jump); // replace prev. jump with this new jump
                    // equivalent to using ladder here and jumping in previous try (jump check continues below)
                    jump = smallLadJump;
                }
            }
            if (bricks < jump) {
                break;
            }
            bricks = bricks - jump;
        }
        return i; // note i increments at the end of the loop, so we are safe.
    }
}