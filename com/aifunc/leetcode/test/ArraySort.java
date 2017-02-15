package com.aifunc.leetcode.test;

/**
 * Created by alex on 3/17/16.
 */
public class ArraySort{
  //partition
  public static void quickSort(int [] array, int leftBound, int rightBound) {
    int left = leftBound + 1;
    int right = rightBound - 2;

    if(left - right >= 0) return;

    int critIndex = rightBound - 1;
    int critVal = array[critIndex];

    while (left - right <= 0) {
      for(;left < critIndex && array[left] < critVal; left++);
      for(;right > leftBound && array[right] >= critVal; right--);
      if(left - right > 0) break;
      swap(array,left,right);
    }

    swap(array,left,critIndex);
    quickSort(array,leftBound,left);
    quickSort(array,left,rightBound);
  }

  public static void swap(int[] array, int one, int two) {
    int temp = array[one];
    array[one] = array[two];
    array[two] = temp;
  }

  // public static void mergeSort() {

  // }

  // public static


  public static int binarySearch(int number, int[] array, int start, int end){
    int middle = ( start + end ) / 2 ;
    if(array[middle] == number) return middle;
    if(start + 1 == end ) return -1;
    if(array[middle] > number) return ArraySort.binarySearch(number,array,start,middle);
    if(array[middle] < number) return ArraySort.binarySearch(number,array,middle,end);
    return -1 ;
  }

  public static int binarySearch(int number, int[] array) {
    return ArraySort.binarySearch(number, array, 0, array.length);
  }

  public static void main(String[] args) {
    Integer input = Integer.parseInt(args[0]);
    int[] arrays = {1,2,3,5,7,10,34,455};
    int pos = ArraySort.binarySearch(input,arrays);
    System.out.println("Find by BinarySearch: "+ input + " in " + pos);

    arrays[7] = 4;
    arrays[6] = 3;
    arrays[1] = 8;
    ArraySort.quickSort(arrays,-1,arrays.length);

    for(int i = 0; i < arrays.length ; i++) {
      System.out.println(arrays[i]);
    }
  }
}