package com.aifunc.leetcode.test.leet;

import java.util.Set;

/**
 * Created by alex on 1/7/17.
 */
public class leet139 {
  public boolean wordBreak(String s, Set<String> wordDict) {
    if(s == null) return false;
    if(s.equals("") && !wordDict.contains("")) return false;

    int len = s.length();
    boolean dp[] = new boolean[len + 1];
    dp[0] = true;

    //dp[i] = dp[i - j] + wordDict.contains[substring(j, j)];
    for(int i = 1; i <= len; i++) {
      for(int j = 0; j < i; j++) {
        if(dp[j] && wordDict.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[len];
  }
}
