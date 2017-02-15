package com.aifunc.leetcode.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 1/4/17.
 */
public class UniqueBinarySearchTrees2 {
  /*
  Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

  For example,
  Given n = 3, your program should return all 5 unique BST's shown below.

    1         3     3      2      1
     \       /     /      / \      \
      3     2     1      1   3      2
     /     /       \                 \
    2     1         2                 3
    */

  public class Solution {
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }

    public List<TreeNode> generateTrees(int n) {
      if(n == 0) return new ArrayList<>();
      return genTrees(1, n);
    }

    // 1, 3
    public List<TreeNode> genTrees (int start, int end) {
      List<TreeNode> list = new ArrayList<>();

      if(start > end) { //(1,0) -> null
        list.add(null);
        return list;
      }
      if(start == end) { //(1,1) -> TreeNode(1)
        list.add(new TreeNode(start));
        return list;
      }
      //
      List<TreeNode> left,right;
      for(int i = start; i <= end;i++) { // i = 1, i = 2, i = 3
        left = genTrees(start, i - 1); // (1,0), (1,1), (1,2)
        right = genTrees(i + 1, end);  // (2,3), (3,3), (4,3)

        //all BST subtree...
        for(TreeNode lnode : left) {
          for(TreeNode rnode : right) {
            TreeNode root = new TreeNode(i);
            root.left = lnode;
            root.right = rnode;
            list.add(root);
          }
        }
      }
      return list;
    }

  }
}
