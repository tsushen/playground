package com.aifunc.leetcode.test.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alex on 1/7/17.
 */
public class leet15 {
  public List<List<Integer>> threeSum(int[] nums) {
    if(nums == null || nums.length < 3) return new LinkedList<List<Integer>>();
    Arrays.sort(nums);

    List<List<Integer>> res = new LinkedList<>();
    int i = 1;
    int j = nums.length - 1;
    for(int k = 0; k < nums.length - 2 ; k ++) {
      i = k + 1;
      j = nums.length - 1;

      int num = nums[k];
      while(i < j) {
        int target = -nums[i] - nums[j];
        if(target == num) res.add(getList(target, nums[i], nums[j]));
        if(num > target) {
          j --;
        }
        if(num <= target) {
          while(i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
          }
          i++;
        }
      }

      while((k + 1 < nums.length ) && nums[k] == nums[k + 1])
        k++;
    }
    return res;
  }

  public List<Integer> getList(int i, int j, int k) {
    List<Integer> res = new ArrayList<>(3);
    res.add(i);
    res.add(j);
    res.add(k);
    return res;
  }
}
