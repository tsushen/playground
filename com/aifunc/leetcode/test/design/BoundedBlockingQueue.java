package com.aifunc.leetcode.test.design;

/**
 * Created by alex on 12/6/16.
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

Q:
By blocking queue it means if the queue is empty, the dequeue thread should be blocked until some other thread enqueue anything.
If the queue is full then the enqueue thread gets blocked until the dequeue thread dequeue anything from the queue.

public interface FixedSizeBlockingQueue<E> {

   // only initialize this queue once and throws Exception if the user is trying to initialize it multiple t times.
   public void init(int capacity) throws Exception;

   // throws Exception if the queue is not initialized
   public void push(E obj) throws Exception;

   // throws Exception if the queue is not initialized
   public E pop() throws Exception;

   // implement an atomic putList function which can put a list of object atomically.
   By atomically it mean the objs in the list should be next to each other in the queue.
   The size of the list could be larger than the queue capacity.
   // throws Exception if the queue is not initialized
   public void pushList(List<E> objs) throws Exception;
}

Here we are using some basic OS and JVM primitives to implement a blocking queue.
Basically it is using monitor and lock. Java exposes those two OS primitives to programmers as Condition and ReentrantLock.

Here is the explanation from Java Doc:
ReentrantLock "A reentrant mutual exclusion Lock with the same basic behavior and semantics as the implicit monitor
lock accessed using synchronized methods and statements, but with extended capabilities."

Condition “Condition factors out the Object monitor methods (wait, notify and notifyAll) into distinct objects to
give the effect of having multiple wait-sets per object, by combining them with the use of arbitrary Lock implementations.
Where a Lock replaces the use of synchronized methods and statements, a Condition replaces the use of the Object monitor methods.
Conditions (also known as condition queues or condition variables) provide a means for one thread to suspend execution (to "wait")
until notified by another thread that some state condition may now be true. Because access to this shared state
information occurs in different threads, it must be protected, so a lock of some form is associated with the condition.
The key property that waiting for a condition provides is that it atomically releases the associated lock and
suspends the current thread, just like Object.wait. A Condition instance is intrinsically bound to a lock.
To obtain a Condition instance for a particular Lock instance use its newCondition() method.”

We use two condition as two waiting queues where we put the suspended thread. One is notFull queue which contains all
producer thread wait for the not full signal. notEmpty queue contains all consumer threads wait for the not empty signal.
I am using another Lock to assure pushList can be finished atomically.


 */

public class BoundedBlockingQueue<E> {

  private int capacity;
  private Queue<E> queue;
  private Lock lock = new ReentrantLock();
  private Lock pushLock = new ReentrantLock();
  private Condition notFull = this.lock.newCondition();
  private Condition notEmpty = this.lock.newCondition();

  // only initialize this queue once and throws Exception if the user is
  // trying to initialize it multiple t times.
  public void init(int capacity) throws Exception {
    this.lock.lock();
    try{
      if(this.queue == null){
        this.queue = new LinkedList<>();
        this.capacity = capacity;
      } else {
        throw new Exception();
      }
    }finally{
      this.lock.unlock();
    }
  }

  // throws Exception if the queue is not initialized
  public void push(E obj) throws Exception {
    this.pushLock.lock();
    this.lock.lock();
    try{
      while(this.capacity == this.queue.size())
        this.notFull.wait();
      this.queue.add(obj);
      this.notEmpty.notifyAll();
    }finally{
      this.lock.unlock();
      this.pushLock.lock();
    }
  }

  // throws Exception if the queue is not initialized
  public E pop() throws Exception {
    this.lock.lock();
    try{
      while(this.capacity==0)
        this.notEmpty.wait();
      E result = this.queue.poll();
      notFull.notifyAll();
      return result;
    }finally{
      this.lock.unlock();
    }
  }

  // implement a atomic putList function which can put a list of object
  // atomically. By atomically i mean the objs in the list should next to each
  // other in the queue. The size of the list could be larger than the queue
  // capacity.
  // throws Exception if the queue is not initialized
  public void pushList(List<E> objs) throws Exception {
    this.pushLock.lock();
    this.lock.lock();
    try{
      for(E obj : objs){
        while(this.queue.size() == this.capacity)
          this.notFull.wait();
        this.queue.add(obj);
        this.notEmpty.notifyAll();
      }
    }finally{
      this.lock.unlock();
      this.pushLock.unlock();
    }
  }
}
