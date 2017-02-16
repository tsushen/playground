package com.aifunc.leetcode.test.oa;

import java.util.*;

/**
 * Created by alex on 2/15/17.
 */
public class ValidLocks {

  public static int validLocks(List<String> inputs) {
    Stack<Integer> stack = new Stack<>();
    HashSet<Integer> set = new HashSet<>();

    int len = inputs.size();
    for(int i = 0; i < len; i++) {
      if (!parse(stack, set, inputs.get(i))) return i+1;
    }
    if (stack.size() != 0) return len;
    return 0;
  }

  public static boolean parse(Stack<Integer> stack, HashSet<Integer> set,String str) {
    if (str.startsWith("ACQUIRE ")) {
      Integer num = Integer.valueOf((str.split(" "))[1]);
      if (set.contains(num)) return false;
      stack.push(num);
      set.add(num);
      return true;
    }
    if (str.startsWith("RELEASE ")) {
      Integer num = Integer.valueOf((str.split(" "))[1]);

      if (!set.contains(num)) return false;
      set.remove(num);


      int top = stack.peek();
      if (stack.size() != 0 && stack.peek().equals(num)) {
        stack.pop();
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    List<String> inputs = new ArrayList<>(
      Arrays.asList(
        new String[]{
          "ACQUIRE 364",
          "ACQUIRE 84",
          "RELEASE 84",
          "ACQUIRE 1337",
          "RELEASE 1337",
          "RELEASE 364"
        }));
    int out = validLocks(inputs);
    System.out.println(out);

    inputs = new ArrayList<>(
      Arrays.asList(
        new String[]{
          "ACQUIRE 364",
          "ACQUIRE 84",
          "RELEASE 364",
          "RELEASE 84"
        }));
    out = validLocks(inputs);
    System.out.println(out);

    inputs = new ArrayList<>(
      Arrays.asList(
        new String[]{
          "ACQUIRE 364",
          "ACQUIRE 84",
          "RELEASE 84"
        }));
    out = validLocks(inputs);
    System.out.println(out);

    inputs = new ArrayList<>(
      Arrays.asList(
        new String[]{
          "ACQUIRE 364",
          "RELEASE 84",
          "RELEASE 364",
        }));
    out = validLocks(inputs);
    System.out.println(out);

    inputs = new ArrayList<>(
      Arrays.asList(
        new String[]{
          "ACQUIRE 364",
          "ACQUIRE 84",
          "ACQUIRE 364",
          "RELEASE 364",
        }));
    out = validLocks(inputs);
    System.out.println(out);

  }
}
