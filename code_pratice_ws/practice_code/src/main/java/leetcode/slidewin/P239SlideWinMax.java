package leetcode.slidewin;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;


public class P239SlideWinMax {

    public static void main(String[] args) {
        var sol = new P239SlideWinMax();
//        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
//        int k = 3;
        int[] nums = new int[]{1, -1};
        int k = 1;
        var ans = sol.maxSlidingWindow(nums, k);
        System.out.printf("ans is %s\n", Arrays.toString(ans));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        // return solWithHeapSlow(nums, k);
        // return solWithHeap(nums, k);
        return solWithQueue(nums, k);
    }

    // TODO - optimize by storing index instead of value in the queue.
    // TODO - write a separate monotonic queue, it would be better.
    int[] solWithQueue(int[] nums, int k) {
        // This is acting more like a monotonic queue, HEAD to TAIL is kept sorted.
        var deq = new ArrayDeque<Pair>();

        var res = new ArrayList<Integer>();
        // add first k elements
        deq.addLast(new Pair(nums[0], 0));
        for (int i = 1; i < k; i++) {
            int num = nums[i];
            while (deq.size() > 0) {
                int last = deq.peekLast().val;
                if (num > last) {
                    deq.removeLast();
                } else {
                    break;
                }
            }
            deq.addLast(new Pair(num, i));

            // System.out.printf("idx: %d, deq: %s\n", i, deq);
        }

        res.add(deq.peekFirst().val);

        // deq - add to tail, remove from head
        for (int i = k; i < nums.length; i++) {
            int num = nums[i];
            while (deq.size() > 0) {
                int last = deq.peekLast().val;
                if (num > last) {
                    deq.removeLast();
                } else {
                    break;
                }
            }
            deq.addLast(new Pair(num, i));

            // check the head
            while (deq.size() > 0) {
                var headElem = deq.peekFirst();
                if (headElem.idx > i - k) {
                    res.add(headElem.val);
                    break;
                } else {
                    deq.removeFirst();
                }
            }

            // System.out.printf("idx: %d, deq: %s\n", i, deq);
        }

        int[] ans = new int[res.size()];
        int i = 0;
        for (int num : res) {
            ans[i++] = num;
        }

        return ans;
    }


    public int[] solWithHeap(int[] nums, int k) {
        // use heap to store top index,
        // we don't try to remove every num which goes out of bound of the window because that makes it slow
        // rather we remove the top if it is out of bounds
        // if top is in bound, then it dictates the answer
        // if top is out of bounds, then remove it.
        var heap = new PriorityQueue<Pair>(
                (a, b) -> Integer.compare(b.val, a.val)
        );

        // first fill 1st k
        for (int i = 0; i < k; i++) {
            heap.add(new Pair(nums[i], i));
        }

        var res = new ArrayList<Integer>();
        res.add(heap.peek().val);

        int currmax = heap.peek().val;
        for (int i = k; i < nums.length; i++) {
            // remove top if it is out of bounds
            while (heap.size() > 0 && heap.peek().idx <= i - k) {
                heap.poll();
            }
            heap.add(new Pair(nums[i], i));
            res.add(heap.peek().val);
        }

        var ans = new int[res.size()];
        int i = 0;
        for (int num : res) {
            ans[i] = num;
            i++;
        }

        return ans;
    }

    public int[] solWithHeapSlow(int[] nums, int k) {

        var heap = new PriorityQueue<Integer>(
                (a, b) -> Integer.compare(b, a)
        );

        // 5

        // first fill 1st k
        for (int i = 0; i < k; i++) {
            heap.add(nums[i]);
        }

        var res = new ArrayList<Integer>();
        res.add(heap.peek());

        int currmax = heap.peek();
        for (int i = k; i < nums.length; i++) {
            if (i - k >= 0) {
                int itemToRemove = nums[i - k];
                heap.remove(itemToRemove);
            }
            heap.add(nums[i]);

            res.add(heap.peek());
        }

        var ans = new int[res.size()];
        int i = 0;
        for (int num : res) {
            ans[i] = num;
            i++;
        }

        return ans;
    }
}

class Pair {
    int val;
    int idx;

    Pair(int val, int idx) {
        this.val = val;
        this.idx = idx;
    }

    public String toString() {
        return String.format("val: %d, idx: %d", val, idx);
    }
}
