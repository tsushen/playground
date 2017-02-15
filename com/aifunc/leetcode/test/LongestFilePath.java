package com.aifunc.leetcode.test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 11/27/16.
 */
public class LongestFilePath {
  /*
  public static class Solution {
    //dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext
    //a\n\taa\n\t\t\aaa\n\t\t\tfile.txt -> a/aa/aaa/file1.txt
    //Question: is there any \r?

    //is a tree -> DFS is better -> organized like this way
    //stack push directyory
    //
    public int lengthLongestPath(String input) {
      if(input == null) return 0;

      String[] strs = input.split("\n");
      List<String> prev = new ArrayList<>();
      return helper(prev, strs);
    }

    public int helper(List<String> dir, String[] lines) {
      String suffix = ".";

      StringBuilder sb = new StringBuilder();
      int len = lines.length;
      int longest = 0;
      int i = 0;
      while(i < len) {
        String line = lines[i];
        int tab = countTab(line);
        line = line.trim();
        System.out.println(line);
        if(line.contains(suffix)) {
          int prev = sb.length();
          if(sb.length() > 0) sb.append("\\");
          sb.append(line);
          System.out.println(sb.toString());
          longest = Math.max(sb.toString().length(), longest);
          sb.setLength(prev);
        } else {
          //\t\taaa \t\tbbb
          int index = dir.size() - 1;
          while(dir.size() > tab) {
            String prevDir = dir.get(index);
            dir.remove(index--);
            sb.setLength(sb.length() - prevDir.length());

            int sbLength = sb.length();
            if(sbLength > 0 && sb.charAt(sbLength - 1) == '\\') {
              sb.setLength(sbLength - 1);
            }
          }

          int sbLength = sb.length();
          System.out.println(sb.toString());

          dir.add(line);
          if (sbLength > 0) sb.append("\\");
          sb.append(line);
        }
        i++;
      }

      return longest;
    }

    public int countTab(String s) {
      int i = 0;
      int tab = 0;
      int len = s.length();
      while(i < len ) {
        if (s.charAt(i++) == '\t') tab ++;
//        if(s.charAt(i) == '\\' && s.charAt(i + 1) == 't') tab ++;
        else break;
      }
      return tab;
    }
  }
  */

  public static class Solution {
    public int lengthLongestPath(String input) {
      Deque<Integer> stack = new LinkedList<>();
      stack.push(0); // "dummy" length
      String[] strs = input.split("\n");

      int maxLen = 0;
      for(String s : strs) {
        int lev = s.lastIndexOf("\t") + 1; // number of "\t"
        while(lev + 1 < stack.size()) stack.pop();
        int len = stack.peek() + s.length() - lev + 1; // remove "/t", add"/"

        if(s.contains(".")) maxLen = Math.max(maxLen, len - 1);
        else stack.push(len);
      }
      return maxLen;
    }
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    String line = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
    int size = s.lengthLongestPath(line);
    System.out.println(line);
    System.out.println(size);
  }
}
