package com.aifunc.leetcode.test.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by alex on 1/7/17.
 */
public class leet127 {
  public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    if(wordList.isEmpty() || !wordList.contains(beginWord) || !wordList.contains(endWord))
      return 0;
    return helper(beginWord, endWord, wordList);
  }

  public int helper(String beginWord, String endWord, Set<String> wordList) {
    //bfs
    List<String> level = new ArrayList<String>();
    level.add(beginWord);
    int length = 0;

    while(!level.isEmpty()) {
      length ++;
      if(level.contains(endWord)) return length;

      List<String> nextLevel = new ArrayList<>();
      for(String s : level) {
        char[] begin = s.toCharArray();
        for(int i = 0; i < begin.length; i++) {
          char prev = begin[i];
          for(int j = 0 ; j < 26; j++) {
            begin[i] = (char)('a' + j);
            String temp = String.valueOf(begin);
            if(wordList.contains(temp)) {
              nextLevel.add(temp);
              wordList.remove(temp); //avoid same path...
            }
          }
          begin[i] = prev;
        }
      }
      level = nextLevel;
    }
    return 0;
  }
}
