package com.aifunc.leetcode.test.leet;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by alex on 1/10/17.
 */
public class leet373 {
  public class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      Comparator com = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
          return - (o1[0] + o1[1] - o2[0] - o2[1]);
        }
      };

      PriorityQueue<int[]> heap = new PriorityQueue<int[]> (k, com);

      for(int i = 0; i < nums1.length; i++) {
        boolean flag = false;
        for(int j = 0; j < nums2.length; j++) {
          int[] pair = new int[]{nums1[i],nums2[j]};
          if(heap.size() < k) {
            heap.offer(pair);
            flag = true;
          } else {
            if (com.compare(pair, heap.peek()) > 0) {
              heap.poll();
              heap.offer(pair);
              flag = true;
            } else {
              break;
            }
          }
        }
        if (!flag) break;
      }

      LinkedList<int[]> list = new LinkedList<>();
      while (heap.size() > 0) {
        list.addFirst(heap.poll());
      }
      return list;
    }
  }
}
