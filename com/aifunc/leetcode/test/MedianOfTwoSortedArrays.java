package com.aifunc.leetcode.test;

/**
 * Created by alex on 9/2/16.
 */
public class MedianOfTwoSortedArrays {
  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int[] arr1 = nums1;
    int[] arr2 = nums2;
    if (nums1.length > nums2.length) {
      arr1 = nums2;
      arr2 = nums1;
    }
    int len1 = arr1.length;
    int len2 = arr2.length;
    int lenSum = len1 + len2;
    int lo = 0;
    int hi = arr1.length;

    int i, j;

    while (lo <= hi) {
      i = (lo + hi) / 2;
      j = (lenSum + 1) / 2 - i;

      if (j > 0 && i < len1 && arr2[j - 1] > arr1[i]) {
        lo = i + 1;
      } else if (i > 0 && j < len2 && arr1[i - 1] > arr2[j]) {
        hi = i - 1;
      } else {
        int maxOfLeft, minOfRight;
        if (i == 0) {
          maxOfLeft = arr2[j - 1];
        } else {
          if (j == 0) {
            maxOfLeft = arr1[i - 1];
          } else {
            maxOfLeft = Math.max(arr1[i - 1], arr2[j - 1]);
          }
        }

        if ((len1 + len2) % 2 == 1) return maxOfLeft;

        if (i == len1) {
          minOfRight = arr2[j];
        } else {
          if (j == len2) {
            minOfRight = arr1[i];
          } else {
            minOfRight = Math.min(arr1[i], arr2[j]);
          }
        }
        return (minOfRight + maxOfLeft) / 2.0;
      }
    }
    return 0.0;
  }

  public static void main(String [] args) {
    int [] a = {1, 3};
    int [] b = {2};
    double c = findMedianSortedArrays(a,b);
    System.out.print(c);
  }

}
