package com.aifunc.leetcode.test;

import java.util.*;

/**
 * Created by alex on 4/13/16.
 */
public class Playground {

  public static void main() {
    int[] nums = {1,2,3,4,5};
    for(Integer num : nums) {
      System.out.println(num);
    }
    Map<Character,Integer> hashMap = new HashMap();
    hashMap.containsKey(2);
    char a = 'a';
    hashMap.get(a);
    String s = "I do something";
    char[] sChar = new char[s.length()];
    for(int i = 0 ; i < s.length() ; i++) {
      sChar[i] = s.charAt(i);
    }
    char[] sschar = s.toCharArray();

    Map<Character,List<Integer>> map = new HashMap<>();
    Set<Character> keyset = map.keySet();

    StringBuilder sBuilder = new StringBuilder(s);
    keyset.clear();

    double c = (double) 5 / 2;

    Stack<String> strStack = new Stack<>();
    Character charA = 'a';
    Set<Character> characterSet = new HashSet<>();
  }


  public int setTest(Integer [] nums) {
    Set<Integer> set = new HashSet<>();
    set.addAll(Arrays.asList(nums));
    return 0 ;
  }

  public int getNumVal(char c) {
    if(c == '*')
      return 10;
    if(c == '#')
      return 11;
    return Character.getNumericValue(c);
  }

  public void listTest() {
    List<List<String>> lines = new ArrayList<List<String>>();
    String a = "";

    List<String> strList = new ArrayList<>();
    strList.subList(0, strList.size());

  }

  public void stringTest() {
    String s = "";
    s.length();

    char[] charArr = s.toCharArray();
    Character a = '1';
    a.isDigit(a);

    StringBuilder sb = new StringBuilder(s);

  }
}
