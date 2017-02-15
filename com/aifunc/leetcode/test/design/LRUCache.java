package com.aifunc.leetcode.test.design;

import java.util.HashMap;

/**
 * Created by alex on 12/6/16.
 */
public class LRUCache {
//  int capacity;
//  LinkedList<Integer> cache; //key
//  HashMap<Integer, Integer> map;
//
//  public LRUCache(int capacity) {
//    this.capacity = capacity;
//    cache = new LinkedList<>();
//    map = new HashMap<>(capacity);
//  }
//
//  public int get(int key) {
//    Integer keyInteger = new Integer(key);
//    if(!map.containsKey(keyInteger)) return -1; // throws error
//
//    if(!cache.peek().equals(keyInteger)) {
//      cache.remove(keyInteger);
//      cache.addFirst(keyInteger);
//    }
//    return map.get(keyInteger);
//  }
//
//  public void set(int key, int value) {
//    if(!map.containsKey(key) && map.size() >= capacity){
//      Integer lastKey = cache.removeLast();
//      map.remove(lastKey);
//    } else {
//      Integer keyInteger = new Integer(key);
//      cache.remove(keyInteger);
//    }
//
//    cache.addFirst(key);
//    map.put(key, value);
//  }

  int capacity;
  volatile int size;
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

  public LRUCache(int capacity) {
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

  public synchronized int get(int key) {
    Integer keyInteger = new Integer(key);
    if(!map.containsKey(keyInteger)) return -1; // throws error

    Node node = map.get(key);
    removeNode(node);
    insertFirst(node);
    map.put(key, node);

    return node.value;
  }

  //1st way : synchronized functions.
  //2nd way :

  public synchronized void set(int key, int value) {
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

  public static void main(String[] args) {
    LRUCache lruCache = new LRUCache(2);

    //[set(2,1),set(1,1),get(2),set(4,1),get(1),get(2)]
    lruCache.set(2, 1);
    lruCache.set(1, 1);
    lruCache.get(2);
    lruCache.set(4, 1);
    lruCache.get(1);
    lruCache.get(2);
  }
}
