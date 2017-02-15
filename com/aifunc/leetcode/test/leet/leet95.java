package com.aifunc.leetcode.test.leet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 1/7/17.
 */
public class leet95 {
  public List<TreeNode> generateTrees(int n) {
    if(n == 0) return new ArrayList<TreeNode>();
    return genTrees(1, n);
  }

  // 1, 3
  public List<TreeNode> genTrees (int start, int end) {
    List<TreeNode> list = new ArrayList<TreeNode>();

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
