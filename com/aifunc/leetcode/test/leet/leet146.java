package com.aifunc.leetcode.test.leet;

import java.util.HashMap;

/**
 * Created by alex on 1/7/17.
 */
public class leet146 {
  int capacity;
  int size;
  Node first;
  Node last;

  HashMap<Integer, Node> map;

  public class Node {
    Node prev;
    Node next;
    int key;
    int value;

    public Node(int k, int v) {
      key = k;
      value = v;
    }

    public Node(){}
  }

  public void insertFirst(Node n) {
    Node node = first.next;

    n.next = node;
    n.prev = first;
    node.prev = n;
    first.next = n;
  }

  public void removeNode(Node n) {
    Node node = n.next;
    Node prevNode = n.prev;

    prevNode.next = node;
    node.prev = prevNode;
    n.next = null;
    n.prev = null;
  }

  public Node removeLast() {
    Node node = last.prev;
    last.prev = node.prev;
    node.prev.next = last;

    node.next = null;
    node.prev = null;
    return node;
  }

  public leet146(int capacity) {
    this.capacity = capacity;
    map = new HashMap<>(capacity);

    size = 0;

    first = new Node();
    last = new Node();
    first.next = last;
    first.prev = null;
    last.prev = first;
    last.next = null;
  }

  public int get(int key) {
    Integer keyInteger = new Integer(key);
    if(!map.containsKey(keyInteger)) return -1; // throws error

    Node node = map.get(key);
    removeNode(node);
    insertFirst(node);
    map.put(key, node);

    return node.value;
  }

  public void set(int key, int value) {
    boolean hasKey = map.containsKey(key);

    if(!hasKey && size >= capacity){
      Node lastKey = removeLast();
      map.remove(lastKey.key);
      size --;
    }

    if(hasKey) {
      Node node = map.get(key);
      removeNode(node);
      size --;
    }

    size ++;
    Node node = new Node(key, value);
    insertFirst(node);
    map.put(key, node);
  }
}
