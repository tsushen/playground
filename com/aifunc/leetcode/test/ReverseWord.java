package com.aifunc.leetcode.test;

/**
 * Created by alex on 12/3/16.
 */
public class ReverseWord {
    public String reverseWords(String s) {
      if(s == null) return null;

      StringBuilder sb = new StringBuilder(s);
      sb = sb.reverse();
      String rev = sb.toString();

      sb.setLength(0);
      StringBuilder word = new StringBuilder();
      String[] arr = rev.split(" ");
      for(String w : arr) {
        word.append(w);
        word = word.reverse();
        sb.append(word.toString());
        sb.append(" ");
        word.setLength(0);
      }
      if(sb.length() > 0) sb.setLength(sb.length() - 1);
      return sb.toString().trim();


      // int i = 0;
      // while(i < rev.length && rev.charAt(i) == ' ') {
      //     sb.append(rev.charAt(i++));
      // }

      // StringBuilder word = new StrinbBuilder();
      // while(i < rev.length) {
      //     while(rev.char(i)) {

      //     }
      // }


    }

    public static void main(String [] args) {
      String a = "ab cb dd";
      String b = new ReverseWord().reverseWords(a);
      System.out.println(b);
    }

}
