package com.aifunc.leetcode.test.design;

import java.util.*;

/**
 * Created by alex on 1/7/17.
 */
public class AllOne {
  Map<String, Node> map;
  //Map<Integer, Segment> segMap;
  Segment head;
  Segment tail;

  public class Segment {
    Segment prev;
    Segment next;
    LinkedHashSet<Node> nodes;
    int v;

    public Segment() {
      v = 0;
    }

    public Segment(int val) {
      v = val;
      nodes = new LinkedHashSet<>();
    }

    public String getKey() {
      return nodes.iterator().next().k;
    }

    public void insertTail() {
      Segment segment = new Segment(1);
      Segment prev = tail.prev;
      tail.prev = segment;
      prev.next = segment;
      segment.prev = prev;
      segment.next = tail;
    }

    public void insert(Segment seg, boolean isFront, Node n) {
      Segment segment = new Segment(n.val);
      segment.nodes.add(n);
      n.parent = segment;
      Segment prev, next;
      if (isFront) {
        prev = seg.prev;
        next = seg;
      } else {
        prev = seg;
        next = seg.next;
      }
      prev.next = segment;
      next.prev = segment;
      segment.next = next;
      segment.prev = prev;
    }

    public void delete(Node n){
      nodes.remove(n);
      if (nodes.size() == 0) {
        Segment prev = this.prev;
        Segment next = this.next;
        prev.next = next;
        next.prev = prev;
        this.prev = null;
        this.next = null;
      }
    }
  }

  public class Node {
    Segment parent;
    int val;
    String k;

    public Node(int v) {
      val = v;
    }

    public void insert(Node n) {
      if (tail.prev.v != 1) {
        tail.insertTail();
      }
      tail.prev.nodes.add(n);
      n.parent = tail.prev;
    }
  }

  /** Initialize your data structure here. */
  public AllOne() {
    head = new Segment();
    tail = new Segment();
    head.prev = null;
    tail.next = null;
    tail.prev = head;
    head.next = tail;

    map = new HashMap<>();
  }

  /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
  public void inc(String key) {
    if (!map.containsKey(key)) {
      Node n = new Node(1);
      n.k = key;
      n.insert(n);
      map.put(key, n);
    } else {
      Node n = map.get(key);
      Segment seg = n.parent;
      n.val ++;
      if (seg.prev.v == n.val) {
        seg.prev.nodes.add(n);
        n.parent = seg.prev;
      } else {
        seg.insert(seg, true, n);
      }
      seg.delete(n);
    }
  }

  /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
  public void dec(String key) {
    if (map.containsKey(key)) {
      Node n = map.get(key);
      n.val --;
      Segment segment = n.parent;
      Segment next = segment.next;
      if (n.val == 0) {
        map.remove(key);
        segment.delete(n);
        return;
      }
      if (next.v == n.val) {
        segment.next.nodes.add(n);
        n.parent = segment.next;
      } else {
        segment.insert(segment, false, n);
      }
      segment.delete(n);
    }
  }

  /** Returns one of the keys with maximal value. */
  public String getMaxKey() {
    if(head.next != tail) return head.next.getKey();
    return "";
  }

  /** Returns one of the keys with Minimal value. */
  public String getMinKey() {
    if(tail.prev != head) return tail.prev.getKey();
    return "";
  }

  public static void main(String[] args) {
    AllOne obj = new AllOne();
    obj.inc("a");
    obj.inc("b");
    obj.inc("c");
    obj.inc("a");
    obj.dec("a");
    obj.dec("a");
    String param_3 = obj.getMaxKey();
    String param_4 = obj.getMinKey();
  }
}
