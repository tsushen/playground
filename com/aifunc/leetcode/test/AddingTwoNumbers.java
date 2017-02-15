package com.aifunc.leetcode.test;

/**
 * Created by alex on 4/13/16.
 */


public class AddingTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Long sumOfL1 = getNum(l1);
    Long sumOfL2 = getNum(l2);
    Long sum = sumOfL1 + sumOfL2;
    ListNode head = new ListNode ( (int) (sum % 10) );
    ListNode node = head;
    while(sum / 10 != 0){
      sum /= 10;
      ListNode temp = new ListNode( (int) (sum % 10) );
      node.next = temp;
      node = node.next;
    }
    return head;
  }


  public static Long getNum(ListNode list) {
    Long sum = new Long(0);
    Long digit = new Long(1);
    while(list != null) {
      sum += list.val * digit;
      digit *= 10;
      list = list.next;
    }
    return sum;
  }

    public static ListNode reverse(ListNode l, ListNode head) {
      if(l.next != null) {
        reverse(l.next,head).next = l;
        l.next = null;
      } else {
        head = l;
      }
      return l;
    }

    public static void main(String[] args) {
      AddingTwoNumbers ad = new AddingTwoNumbers();
      ListNode head = new ListNode(2);
      head.next = new ListNode(4);
      head.next.next = new ListNode(3);

      ListNode head2 = new ListNode(5);
      head2.next = new ListNode(6);
      head2.next.next = new ListNode(4);

      ListNode result = ad.addTwoNumbers(head,head2);
      while(result.next != null) {
        System.out.print(result.val + " ");
      }
    }
  }





