package com.aifunc.leetcode.test.leet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 1/9/17.
 */
public class leet337 {
    //dp[root][0] = Math.max(dp[root.left][0],dp[root.left][1]) + Math.max(dp[root.right][0], dp[root.right][1])
    //0 is for not containing the root
    //dp[root][1] = Math.max(dp[root.left][0], dp[root.right][0]) + root.val

    Map<TreeNode,int[]> mem = new HashMap<>();

    public int rob(TreeNode root) {
      return Math.max(robHelper(root, 0), robHelper(root,1));
    }

    public int robHelper(TreeNode root, int s) {
      if(root == null) return 0;

      if(mem.containsKey(root)) {
        int[] res = mem.get(root);
        if(res[s] != -1) return res[s];
      }

      int retVal = 0;
      if(s == 0) {
        retVal = Math.max(robHelper(root.left, 0), robHelper(root.left, 1)) +
          Math.max(robHelper(root.right, 0), robHelper(root.right, 1));
      } else {
        retVal = robHelper(root.left, 0) + robHelper(root.right, 0) + root.val;
      }

      if(mem.containsKey(root)) {
        int[] res = mem.get(root);
        res[s] = retVal;
        mem.put(root, res);
      } else {
        int[] res = new int[2];
        res[1-s] = -1;
        res[s] = retVal;
        mem.put(root, res);
      }
      return retVal;
    }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.right = new TreeNode(3);
    root.right.right = new TreeNode(1);

    leet337 leet = new leet337();
    int val = leet.rob(root);
    System.out.println(val);
  }
}
