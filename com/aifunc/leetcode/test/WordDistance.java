package com.aifunc.leetcode.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 11/8/16.
 */
public class WordDistance {
  private Map<String, List<Integer>> map = new HashMap<>();

  public WordDistance(String[] words) {
    int i = 0;
    for(String w : words) {
      if(!map.containsKey(w)) {
        map.put(w, new ArrayList<Integer>());
      }
      List<Integer> list = map.get(w);
      list.add(i++);
    }
  }

  public int shortest(String word1, String word2) {
    List<Integer> list1 = map.get(word1);
    List<Integer> list2 = map.get(word2);

    //try to find the smallest distance
    int i = -1;
    int j = -1;
    int size1 = list1.size();
    int size2 = list2.size();

    int min = Integer.MAX_VALUE;
    while (i < size1 - 1 && j < size2 - 1) {
      if (i == -1 && j == -1) i = j = 0;
      Integer temp1 = list1.get(i);
      Integer temp2 = list2.get(j);

      if (Math.abs(temp1 - temp2) < min) min = Math.abs(temp1 - temp2);
      if (temp1 < temp2 ) j++;
      else if(temp2 < size2) j++;
    }

    return min;
  }


  public static void main(String[] args) {
    String[] str1 = {"a","a","b","b"};
    WordDistance wd = new WordDistance(str1);
    int min = wd.shortest("a", "b");

  }
}
