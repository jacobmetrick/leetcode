/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        int listLength = 1;
        ListNode tail = head;
        while (tail.next != null) {
            listLength++;
            tail = tail.next;
        }

        int newTailPos = (listLength - (n % listLength) - 1);
        ListNode newTail = head;
        for (int i = 0; i < newTailPos; i++) {
            newTail = newTail.next;
        }
        final ListNode newHead = newTail.next;
        if (newTail == tail) { // this also captures the case that the newHead points to null
            return head;
        } else {
            newTail.next = null;
            tail.next = head;
            return newHead;
        }
    }

    public static void main(String[] args) {
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
