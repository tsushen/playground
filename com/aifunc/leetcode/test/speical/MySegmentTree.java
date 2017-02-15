package com.aifunc.leetcode.test.speical;

/**
 * Created by alex on 1/12/17.
 */
public class MySegmentTree {
  int[] tree;

  public MySegmentTree(int[] arr) {
    int len = arr.length;
    int size = 2 * (int)Math.pow(2, Math.ceil(Math.log((double) len) / Math.log(2.0))) - 1;
    tree = new int[size];

    buildTree(arr, 0, len, 0);
  }

  private int buildTree(int[] arr, int s, int e, int i) {
    if (s == e) {
      tree[i] = arr[s];
      return arr[s];
    } else {
      int mid = (s + e) / 2;
      int left = buildTree(arr, s, mid, 2 * i + 1);
      int right = buildTree(arr, mid + 1, e, 2 * i + 2);

      tree[i] = left + right;
      return tree[i];
    }
  }

  public int query(int s, int e, int qs, int qe, int i) {
    if(s > qe || e < qs) {
      return 0;
    }
    if(s > qs && e < qe) {
      return tree[i];
    } else {
      int mid = (s + e) / 2;
      int left = query(s, mid, qs, qe, 2 * i + 1);
      int right = query(mid + 1, e, qs, qe, 2 * i + 2);
      return left + right;
    }
  }

  public void update(int n, int i, int diff) {
    updateValueUtil(0, n, i, diff, 0);
  }

  void updateValueUtil(int s, int e, int i, int diff, int si) {
    // Base Case: If the input index lies outside the range of
    // this segment
    if (i < s || i > e)
      return;

    tree[i] = tree[i] + diff;
    if (e != s) {
      int mid = (s + e) / 2;
      updateValueUtil(s, mid, i, diff, 2 * si + 1);
      updateValueUtil(mid + 1, e, i, diff, 2 * si + 2);
    }
  }

  public static void main(String[] args) {

  }
}
