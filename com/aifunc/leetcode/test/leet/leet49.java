package com.aifunc.leetcode.test.leet;

import java.util.*;

/**
 * Created by alex on 1/7/17.
 */
public class leet49 {
  public List<List<String>> groupAnagrams(String[] strs) {
    //
    List<List<String>> res = new LinkedList<>();
    if(strs == null || strs.length == 0) return res;

    Map<String,List<String>> map = new HashMap<>();
    for(String s : strs) {
      char[] arr = s.toCharArray();
      Arrays.sort(arr);
      String sorted = String.valueOf(arr);
      if(!map.containsKey(sorted)) {
        List<String> temp = new LinkedList<>();
        temp.add(s);
        map.put(sorted, temp);
      } else {
        map.get(sorted).add(s);
      }
    }

    for(List<String> v : map.values()) {
      res.add(v);
    }

    return res;
  }
}
