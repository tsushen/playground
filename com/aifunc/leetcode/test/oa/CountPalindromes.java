package com.aifunc.leetcode.test.oa;

/**
 * Created by alex on 2/15/17.
 */
public class CountPalindromes {
  //hellolle -> ["ellolle", "ll", "lol", "lloll", ...etc]

  public static int countPalidromes(String str) {
    if (str == null || str.length() == 0) return  0;

    char[] arr = str.toCharArray();
    int len = str.length();
    int j = len - 1, cnt = 0;
    for (int i = 0; i < len; i++) {
      j = len - 1;
      while (i <= j) {
        if (isPalidromes(arr, i, j)) cnt++;
        j--;
      }
    }
    return cnt;
  }

  public static boolean isPalidromes (char[] arr, int start, int end) {
    while (start <= end) {
      if (arr[start++] != arr[end--]) return false;
    }
    return true;
  }

  public static int fastCount(String str) {
    if (str == null || str.length() == 0) return  0;

    char[] arr = str.toCharArray();
    int len = str.length();
    int cnt = 0;

    for (int i = 0; i < len; i++) {
      int lo = i;
      int hi = i;

      while (lo >= 0 && hi < len && arr[lo--] == arr[hi++])
        cnt++;

      lo = i - 1;
      hi = i;
      while (lo >= 0 && hi < len && arr[lo--] == arr[hi++])
        cnt ++;
    }
    return cnt;
  }

  public static void main(String[] args) {
    String str1 = "aaaa";
    int cnt1 = countPalidromes(str1);
    int cnt2 = fastCount(str1);

    System.out.println(cnt1);
    System.out.println(cnt2);
  }
}
