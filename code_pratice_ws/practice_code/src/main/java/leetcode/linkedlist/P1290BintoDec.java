package leetcode.linkedlist;


public class P1290BintoDec {
    // intuition
    // 12 = 10 + 2
    // 123 = (((0 * 10)+1)*10 + 2) * 10 + 3
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans = ans * 2 + head.val;
            head = head.next;
        }
        return ans;
    }
}

