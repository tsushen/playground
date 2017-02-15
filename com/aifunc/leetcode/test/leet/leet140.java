package com.aifunc.leetcode.test.leet;

import java.util.*;

/**
 * Created by alex on 1/7/17.
 */
public class leet140 {
  public List<String> wordBreak(String s, Set<String> wordDict) {
    if(checkSuffix(s, wordDict))
      return dfsHelper(s, wordDict, new HashMap<String, List<String>>());
    return new ArrayList<String>();
  }

  public boolean checkSuffix(String s, Set<String> wordDict) {
    int len = s.length();
    for(int i = 0; i < len; i++) {
      if(wordDict.contains(s.substring(i))) {
        return true;
      }
    }
    return false;
  }

  //dfs -> {cat, sanddog} -> {sand, dog} -> {dog,""}
  //    -> {cats, anddog} -> {and, dog}  -> {dog,""}

  //"dog" is the same
  //it is possible to save some string into map to speed up the searching
  //"anddog" -> List<String> {"and dog"}

  public List<String> dfsHelper(String s, Set<String> wordDict, Map<String, List<String>> map) {
    if(map.containsKey(s)) return map.get(s);

    int len = s.length();
    List<String> res = new ArrayList<>();
    for(int i = 1; i <= len ; i++) {
      String prefix = s.substring(0, i);
      if(wordDict.contains(prefix)) {
        String suffix = s.substring(i);
        if(!suffix.equals("")) {
          List<String> words = dfsHelper(suffix, wordDict, map);
          for(String word : words)
            res.add(prefix + " " + word);
        } else {
          res.add(prefix);
        }
      }
    }

    if(!map.containsKey(s)) {
      map.put(s, res);
    }

    return res;
  }
}
