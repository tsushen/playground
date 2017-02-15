package com.aifunc.leetcode.test.leet;

/**
 * Created by alex on 1/7/17.
 */
public class leet101 {
  public boolean isSymmetric(TreeNode root) {
    if(root == null) return true;
    return symmetric(root.left, root.right);
  }

  public boolean symmetric(TreeNode l, TreeNode r) {
    if(l == null && r == null) return true;

    if(l != null && r != null && l.val == r.val) {
      return symmetric(l.left, r.right) && symmetric(l.right, r.left);
    } else {
      return false;
    }
  }
}
