package com.aifunc.leetcode.test.leet;

/**
 * Created by alex on 1/7/17.
 */
public class leet86 {
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode(int x) { val = x; }
   * }
   *
   * public ListNode partition(ListNode head, int x) {
   ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
   ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
   while (head!=null){
   if (head.val<x) {
   curr1.next = head;
   curr1 = head;
   }else {
   curr2.next = head;
   curr2 = head;
   }
   head = head.next;
   }
   curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
   curr1.next = dummy2.next;
   return dummy1.next;
   }
   */
  public class Solution {
    public ListNode partition(ListNode head, int x) {
      if(head == null || head.next == null) return head;

      ListNode rear = head;
      ListNode l = head;
      while(l.next != null) {
        l = l.next;
        if(l.val < x) rear = l;
      }

      ListNode newHead = head;
      l = null;
      ListNode pivot = rear;
      for(ListNode n = head; n != pivot; ) {
        ListNode next = n.next;
        if(n.val >= x) {
          if(n == newHead) newHead = newHead.next;
          if(l != null) {
            l.next = n.next;
          }
          n.next = null;
          append(rear, n);
          rear = n;
        } else {
          l = n;
        }
        n = next;
      }
      return newHead;

      // if(rear.val < x) rear = rear.next;
      // ListNode s = rear;
      // while(s != null) {
      //     if(s.val == x) {
      //         s.val = rear.val;
      //         rear.val = x;
      //     }
      //     s = s.next;
      // }

    }

    public void append(ListNode rear, ListNode n) {
      ListNode next = rear.next;
      rear.next = n;
      n.next = next;
    }
  }
}
