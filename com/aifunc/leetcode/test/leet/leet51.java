package com.aifunc.leetcode.test.leet;

import java.util.*;

/**
 * Created by alex on 1/7/17.
 */
public class leet51 {
  private Set<Integer> col;
  private Set<Integer> in;
  private Set<Integer> out;

  public List<List<String>> solveNQueens(int n) {
    col = new HashSet<>();
    in = new HashSet<>();
    out = new HashSet<>();

    List<List<String>> res = new LinkedList<>();
    queenHelper(res, new ArrayList<>(), n, 0);
    return res;
  }

  public void queenHelper(List<List<String>> res, List<String> temp, int n, int i) {
    if (i == n) {
      res.add(temp);
      return;
    }

    StringBuilder sb = new StringBuilder();
    for(int j = 0; j < n; j++) {
      if(!col.contains(j) && !in.contains(j - i) && !out.contains(i + j)) {
        int k = j;
        sb.append('Q');
        while(++k < n) {
          sb.append('.');
        }
        temp.add(sb.toString());
        col.add(j);
        in.add(j - i);
        out.add(i + j);

        queenHelper(res, new ArrayList<String>(temp), n, i + 1);

        col.remove(j);
        in.remove(j - i);
        out.remove(i + j);
        temp.remove(i);
        sb.setLength(j);
      }
      sb.append('.');
    }
    return;
  }
}
