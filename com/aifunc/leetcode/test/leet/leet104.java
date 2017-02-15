package com.aifunc.leetcode.test.leet;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by alex on 1/7/17.
 */
public class leet104 {
   /*
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    */

  public int maxDepth(TreeNode root) {
    if(root == null) return 0;

    Deque<TreeNode> stack = new LinkedList<>();
    stack.push(root);

    int height = 0;
    while(!stack.isEmpty()) {
      int size = stack.size();
      while(size-- > 0) {
        TreeNode node = stack.pop();
        if(node.right != null) stack.addLast(node.right);
        if(node.left != null) stack.addLast(node.left);
      }
      height ++;
    }
    return height;
  }
}
