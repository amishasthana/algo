package Contest;

import java.util.ArrayList;
import java.util.List;

/*
1703. Minimum Adjacent Swaps for K Consecutive Ones

    User Accepted: 108
    User Tried: 532
    Total Accepted: 115
    Total Submissions: 1060
    Difficulty: Hard

You are given an integer array, nums, and an integer k. nums comprises of only 0's and 1's.
 In one move, you can choose two adjacent indices and swap their values.

Return the minimum number of moves required so that nums has k consecutive 1's.



Example 1:

Input: nums = [1,0,0,1,0,1], k = 2
Output: 1
Explanation: In 1 move, nums could be [1,0,0,0,1,1] and have 2 consecutive 1's.

Example 2:

Input: nums = [1,0,0,0,0,0,1,1], k = 3
Output: 5
Explanation: In 5 moves, the leftmost 1 can be shifted right until nums = [0,0,0,0,0,1,1,1].

Example 3:

Input: nums = [1,1,0,1], k = 2
Output: 0
Explanation: nums already has 2 consecutive 1's.



Constraints:

    1 <= nums.length <= 105
    nums[i] is 0 or 1.
    1 <= k <= sum(nums)


 */
public class KSwap {
    public int minMovIes(int[] nums, int k) {
        int min = nums.length;
        List<Integer> lis = new ArrayList<>();
        int nMove = 0;
        int i = 0;
        while (nums[i] == 0) i++; //Discard initial zeros.
        for (; i < nums.length; i++) {
            int elem = nums[i];
            if (elem == 0) {
                nMove++;
            } else {
                lis.add(nMove);
            }
        }
        int l = 0;
        int r = l + k - 1;
        while (r < lis.size()) {
            min = Math.min((lis.get(r++) - lis.get(l++)), min);
        }
        return min;
    }

    public static void main(String[] args) {
        KSwap ks = new KSwap();
        int[] A =
                {1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1};
        System.out.println(ks.minMovIes(A, 7));
    }
}
