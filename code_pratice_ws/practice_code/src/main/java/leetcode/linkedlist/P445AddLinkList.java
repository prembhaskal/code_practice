package leetcode.linkedlist;

public class P445AddLinkList {

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1rev = reverseLinkList(l1);
        ListNode l2rev = reverseLinkList(l2);
        ListNode ans = null;
        int carry = 0;

        while (l1rev != null || l2rev != null) {
            int num1 = 0;
            if (l1rev != null) {
                num1 = l1rev.val;
            }

            int num2 = 0;
            if (l2rev != null) {
                num2 = l2rev.val;
            }

            int sum = num1 + num2 + carry;
            carry = sum / 10;
            sum = sum % 10;

            ListNode newnode = new ListNode(sum, ans);

            ans = newnode;

            if (l1rev != null) {
                l1rev = l1rev.next;
            }
            if (l2rev != null) {
                l2rev = l2rev.next;
            }
        }

        if (carry > 0) {
            ListNode newnode = new ListNode(carry, ans);
            ans = newnode;
        }

        return ans;
    }

    ListNode reverseLinkList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        // prev   curr    next

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }
}
