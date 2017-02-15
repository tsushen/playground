package com.aifunc.leetcode.test;

/**
 * Created by alex on 3/18/16.
 */
public class HeapSort {

  public int height;

  public static void maxHeapify(int[] heap, int index, int length) {
    int biggest = heap[index];
    int next = index;
    if (2 * index + 1 < length && heap[index] < left(heap, index)) {
      next = 2 * index + 1;
      biggest = heap[next];

    }
    if (2 * index + 2 < length && biggest < right(heap, index)) {
      next = 2 * index + 2;
    }
    if(next == index) return;
    swap(heap,index,next);
    maxHeapify(heap, next, length);
  }

  public static void makeHeap(int[] heap, int length) {
    for(int i = length / 2 - 1; i >= 0 ; i--) {
      maxHeapify(heap,i,length);
    }
  }

  public static void sort(int []heap, int length) {
    makeHeap(heap,length);
    for(int i = 0; i < length --; ) {
      swap(heap,length,i);
      maxHeapify(heap,i,length);
    }
  }

  public static int left(int[] heap, int index) {
    return heap[2 * index + 1];
  }

  public static int right(int[] heap, int index) {
    return heap[2 * index + 2];
  }

  public static int parent(int[] heap, int index) {
    return heap[(index - 1) / 2];
  }


  public static void swap(int[] array, int one, int two) {
    int temp = array[one];
    array[one] = array[two];
    array[two] = temp;
  }

  public static void main(String args[]){
    int[] arrays = {1,2,3,5,7,10,34,455};
    arrays[7] = 4;
    arrays[6] = 3;
    arrays[1] = 8;
    HeapSort.sort(arrays,arrays.length);
    for(int i = 0; i < arrays.length ; i++) {
      System.out.println(arrays[i]);
    }
  }

}



