package com.aifunc.leetcode.test;

/**
 * Created by alex on 4/13/16.
 */
import java.util.HashMap;

public class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer,Integer> numMap = new HashMap<>();
    int [] result = new int[2];
    for(int i = 0 ; i < nums.length; i++)
      numMap.put(nums[i],i);
    int indexB = -1;
    for(int i = 0; i < nums.length ; i++) {
      Integer numLeft = target - nums[i];
      Integer tempIndex = numMap.get(numLeft);
      if( numMap.containsKey( numLeft ) && tempIndex != i)
        indexB = tempIndex;
      if( indexB != -1 ) {
        result[0] = i;
        result[1] = indexB;
        break;
      }
    }
    return result;
  }

  public static void main() {
  }

}
