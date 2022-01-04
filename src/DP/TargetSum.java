package DP;

import java.util.ArrayList;
import java.util.List;

/*
 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        if ( (nums == null) || (nums.length == 0)) return 0;
        int P = 1 << nums.length;
        int freq = 0;
        if ( nums.length == 1) {
            if ((nums[0] == S) || (-1*nums[0] == S)) return 1;
            return 0;
        }
        for(int i = 0; i < P;i++) {

            int y = i;int sum = 0;int x = 0;

            while (y > 0) {
                if (( y & 1)  == 1)  {
                    sum += nums[x++];
                } else {
                    sum -= nums[x++];
                }
                y = y>>1;
            }
            while (x < nums.length) {
                sum -= nums[x++];
            }
            if (sum == S) {
                freq++;
            }
        }
        return freq;
    }

    int f = 0;
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for(int i : nums) {
            sum += i;
        }
        backTrack(nums,sum-S,0,new ArrayList<>(),0);
        return f;
    }

    public void backTrack(int[] nums, int S, int start,List<Integer> temList,int cSum) {
        if (cSum == S) {
            f++;
        }
        for(int i = start; i < nums.length; i++) {
            temList.add(nums[i]);
            cSum  = cSum+nums[i];
            backTrack(nums,S,i+1,temList,cSum);
            cSum = cSum - temList.get(temList.size() -1);
            temList.remove(temList.size() -1);
        }

    }


    public static void main(String[] args) {
        TargetSum TS = new TargetSum();
        //System.out.println(Integer.toBinaryString(263));
        int[] N = {1, 1, 1, 1, 1};
        int[] N1 = {7,9,3,8,0,2,4,8,3,9};
        int[] N2 = {1,2,3,4,5};
        System.out.println(TS.findTargetSumWays2(N,3));
    }


}
