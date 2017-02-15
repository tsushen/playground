package com.aifunc.leetcode.test.leet;

/**
 * Created by alex on 1/7/17.
 */
public class leet96 {
  public int numTrees(int n) {
    int [] G = new int[n+1];
    G[0] = G[1] = 1;

    for(int i = 2; i <= n; ++i) {
      for(int j = 0; j <= i - 1; ++j) {
        G[i] += G[j] * G[i - j - 1];
      }
    }
    return G[n];
  }
}
