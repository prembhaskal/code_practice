package leetcode.misc;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val){
        this.val = val;
    }
}
public class P2AddNum {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int ans = 0;

        ListNode head = new ListNode();
        ListNode curr = head;
        while (l1 != null || l2 != null) {
            int v1 = 0;
            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }
            int v2 = 0;
            if (l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }
            ans = v1 + v2 + carry;
            carry = ans / 10;
            ans = ans % 10;

            ListNode res = new ListNode(ans);
            curr.next = res;
            curr = res;
        }
        if (carry > 0) {
            ListNode newres = new ListNode(carry);
            curr.next = newres;
        }
        return head.next;
    }
}