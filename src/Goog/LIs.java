package Goog;

import java.util.TreeSet;

/*
SOL -- >
Lets go from back. For each element.
X --> What is the lowest number higher then X(Tree Set) Whatever is that, +1 and store it.
SOL END
300. Longest Increasing Subsequence
Medium

Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4

 */
public class LIs {
/*
    public int lengthOfLIS(int[] nums) {

        TreeMap<Integer,Integer> TS = new TreeMap<>();
        for(int ii = 0;ii < nums.length ; ii++) {
            int i = nums[ii];
            if (TS.containsKey(i)) continue;
            Integer key = TS.higherKey(i);
            if (key == null) {
                TS.put(i,TS.size()+1);
            } else {
                TS.put(i,TS.get(key));
                TS.remove(key);
            }
        }
        return TS.size();
    }

 */

    public int lengthOfLIS(int[] nums) {

        TreeSet<Integer> TS = new TreeSet<>();
        for(int ii = 0;ii < nums.length ; ii++) {
            int i = nums[ii];
            if (TS.contains(i)) continue;
            Integer key = TS.higher(i);
            if (key == null) {
                TS.add(i);
            } else {
                TS.add(i);
                TS.remove(key);
            }
        }
        return TS.size();
    }

    public static void main(String[] args) {
        int[] A = {0,1,0,3,2,3};
        LIs ls = new LIs();
        System.out.println(ls.lengthOfLIS(A));
    }
}
