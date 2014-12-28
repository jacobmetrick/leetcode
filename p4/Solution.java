/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Pointer = l1;
        ListNode l2Pointer = l2;
        ListNode answerHead = null;
        ListNode previous = null;
        ListNode current = null;
        int carry = 0;
        while(l1Pointer != null || l2Pointer != null || carry != 0) {
            final int l1Val = (l1Pointer == null) ? 0 : l1Pointer.val;
            final int l2Val = (l2Pointer == null) ? 0 : l2Pointer.val;
            final int value = l1Val + l2Val + carry;
            final int digit = value % 10;
            carry = value / 10;
            current = new ListNode(digit);
            if (answerHead == null) {
                answerHead = current;
            }
            if (previous != null) {
                previous.next = current;
            }
            previous = current;
            l1Pointer = (l1Pointer == null) ? null : l1Pointer.next;
            l2Pointer = (l2Pointer == null) ? null : l2Pointer.next;
        }
        return answerHead;
    }

    public static void main(String[] args) {
        final Solution sl = new Solution();
        sl.addTwoNumbers(sl.makeReverseNumber(1), sl.makeReverseNumber(99));
    }

    private ListNode makeReverseNumber(int n) {
        final String reversedNumber = new StringBuilder(Integer.toString(n)).reverse().toString();
        final int slen = reversedNumber.length();
        ListNode head = null;
        ListNode previous = null;
        ListNode current = null;
        for (int i = 0; i < slen; i++) {
            final char c = reversedNumber.charAt(i);
            final int value = Character.getNumericValue(c);
            current = new ListNode(value);
            if (head == null) {
                head = current;
            }
            if (previous != null) {
                previous.next = current;
            }
            previous = current;
        }
        return head;
    }
}
