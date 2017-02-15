package com.aifunc.leetcode.test.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 interface是这个 Iterable<Integer> mergeKSortedIterators(Iterators[] iters)
 也就是给每个array的iterator,然后merge.

 Solution:
 The problem looks straight-forward: just to a minHeap and continuously poll and add elements into the heap.

 The only pitfall for this problem is the input is Iterators.
 When we compare values in the heap, if we use next(), we might lose the next() value.
 Thus we need to defined a customized class to store the value.

 */
public class KSortedIterator {
    public static Iterable<Integer> mergeKSortedIterators(
      List<Iterator<Integer>> iterators) {

      List<Integer> result = new ArrayList<>();
      if (iterators == null || iterators.size() == 0) {
        return result;
      }

      PriorityQueue<MyIterator> pq = new PriorityQueue<>(iterators.size());

      for (Iterator<Integer> iterator : iterators) {
        if (iterator.hasNext()) {
          pq.add(new MyIterator(iterator.next(), iterator));
        }
      }

      while (!pq.isEmpty()) {
        MyIterator curr = pq.poll();
        result.add(curr.val);
        if (curr.hasNext()) {
          pq.add(curr);
        }
      }

      return result;
    }

    private static class MyIterator implements Comparable<MyIterator> {
      private Integer val;
      private Iterator<Integer> iterator;

      public MyIterator(Integer val, Iterator<Integer> iterator) {
        this.val = val;
        this.iterator = iterator;
      }

      public boolean hasNext() {
        if (iterator.hasNext()) {
          val = iterator.next();
          return true;
        }

        return false;
      }

      public int compareTo(MyIterator that) {
        return this.val - that.val;
      }
    }

    public static void main(String[] args) {
      List<Integer> a = new ArrayList<>();
      a.add(1);
      a.add(3);
      a.add(5);

      List<Integer> b = new ArrayList<>();
      b.add(2);
      b.add(4);

      List<Iterator<Integer>> iterators = new ArrayList<>();
      iterators.add(a.iterator());
      iterators.add(b.iterator());

      Iterable<Integer> result = mergeKSortedIterators(iterators);

      for (Integer num : result) {
        System.out.println(num);
      }
    }

}
