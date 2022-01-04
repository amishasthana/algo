package DP;

import java.util.Arrays;

/*
416. Partition Equal Subset Sum
Medium

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:

    Each of the array element will not exceed 100.
    The array size will not exceed 200.



Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].



Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.

 */
public class PatitionEqualSubset {
    public boolean canPartition(int[] nums) {
        if ( (nums == null) || (nums.length == 0)) return false;
        int target = 0;
        for(int i : nums) {
            target += i;
        }
        if ((target & 1) == 1) return false;
        target /= 2;
        int n = nums.length;
        boolean[] S = new boolean[target+1];
        Arrays.fill(S,false);
           /*
               For i numbers starting from 0. I can get to T
               if yes then I am done.
               S[t] --> (nums[i] == t) || if (t >= nums[i]) then,
                                           S[t-nums[i]]
           */
        for(int i = 0; i < n; i++) {
            for(int j = target; j >= 1 && j >= nums[i]; j--) {
                S[j] = S[j] || (nums[i] == j) || S[j-nums[i]];
            }
            System.out.println(i);
        }// End of outer for
        return S[target];
    }

    public static void main(String[] args) {
        PatitionEqualSubset pes = new PatitionEqualSubset();
        int[] A = {23,13,11,7,6,5,5};
        System.out.println(pes.canPartition(A));
    }

}
