package leetcode.linkedlist;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class P86PartitionList {
    public ListNode partition(ListNode head, int x) {
        // return partitionslow(head, x);
        return partitionWithSentinel(head, x);
    }

    ListNode partitionWithSentinel(ListNode head, int x) {
        var small = new ListNode(0);
        var smallhead = small;
        var big = new ListNode(0);
        var bighead = big;

        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                big.next = head;
                big = big.next;
            }

            head = head.next;
        }

        big.next = null;
        small.next = bighead.next;
        return smallhead.next;
    }

    public ListNode partitionslow(ListNode head, int x) {
        ListNode curr = head;
        if (curr == null) {
            return null;
        }

        // handle next first
        var next = partitionslow(curr.next, x);
        curr.next = next;

        if (next == null) {
            return curr;
        }
        // check if current should be shifted down too.
        if (curr.val >= x && next.val < x) {
            var tmp = next.next;
            curr.next = tmp;

            var newnext = partitionslow(curr, x);
            next.next = newnext;
            return next;
        }
        return curr;
    }
}
