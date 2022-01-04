package Contest;

import java.util.*;

/*
1755. Closest Subsequence Sum

    User Accepted: 227
    User Tried: 1248
    Total Accepted: 246
    Total Submissions: 3024
    Difficulty: Hard

You are given an integer array nums and an integer goal.

You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal. That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).

Return the minimum possible value of abs(sum - goal).

Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.



Example 1:

Input: nums = [5,-7,3,5], goal = 6
Output: 0
Explanation: Choose the whole array as a subsequence, with a sum of 6.
This is equal to the goal, so the absolute difference is 0.

Example 2:

Input: nums = [7,-9,15,-2], goal = -5
Output: 1
Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.

Example 3:

Input: nums = [1,2,3], goal = -7
Output: 7



Constraints:

    1 <= nums.length <= 40
    -107 <= nums[i] <= 107
    -109 <= goal <= 109


 */

/*
1755. Closest Subsequence Sum

    User Accepted: 227
    User Tried: 1248
    Total Accepted: 246
    Total Submissions: 3024
    Difficulty: Hard

You are given an integer array nums and an integer goal.

You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal. That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).

Return the minimum possible value of abs(sum - goal).

Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.



Example 1:

Input: nums = [5,-7,3,5], goal = 6
Output: 0
Explanation: Choose the whole array as a subsequence, with a sum of 6.
This is equal to the goal, so the absolute difference is 0.

Example 2:

Input: nums = [7,-9,15,-2], goal = -5
Output: 1
Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.

Example 3:

Input: nums = [1,2,3], goal = -7
Output: 7



Constraints:

    1 <= nums.length <= 40
    -107 <= nums[i] <= 107
    -109 <= goal <= 109


 */
public class MinAbsSum {

    public int minAbsDifference(int[] nums, int goal) {
        int min = Math.abs(goal);
        Set<Integer> TS = new HashSet<>();
        TS.add(0);
        for(int i : nums) {
            Set<Integer> nSet = new HashSet<>();
            nSet.addAll(TS);
            for(int ii : TS) {
                int v = ii+i;

                min = Math.min(min, Math.abs(v - goal));
                nSet.add(v);
            }
            TS = nSet;
        }
        return min;
    }

    public int minAbsDifferenceA(int[] nums, int goal) {
        int minS = Math.abs(goal);
        Set<Integer> L = getAllSet(nums,0,nums.length/2);
        Set<Integer> R = getAllSet(nums,nums.length/2,nums.length);
        List<Integer> RR = new ArrayList<>(R);
        Collections.sort(RR);


        for(int i : L) {
            int rem = goal - i;
            /*
            g = 5, i = 3. So rem= 2
                     -3 rem = 8
               -5  i = 3  , rem  = -8
                   i = -3  rem = -2
            */
            if ( rem < RR.get(0)) {
                minS = Math.min(minS,Math.abs(i+RR.get(0)-goal));
                continue;
            } else if (rem > RR.get(RR.size()-1)) {
                minS = Math.min(minS,Math.abs(RR.get(RR.size()-1)+i-goal));
            } else {
                int p = Collections.binarySearch(RR,rem);
                if ( p >= 0) {
                    return 0;
                } else {
                    int pos = (p+1)*-1; // p is -ceiling+(-1)
                    int floor = pos-1;
                    minS = Math.min(minS,Math.abs(RR.get(pos)+i-goal));
                    minS = Math.min(minS,Math.abs(i+RR.get(floor)-goal));
                }
            }
        }//end of for
        return minS;
    }

    private Set<Integer> getAllSet(int[] nums,int beg,int end) {
        Set<Integer> TS = new HashSet<>();
        TS.add(0);
        for(int j= beg;j< end;j++) {
            int i = nums[j];
            Set<Integer> nSet = new HashSet<>();
            nSet.addAll(TS);
            for(int ii : TS) {
                int v = ii+i;
                nSet.add(v);
            }
            nSet.add(i);
            TS = nSet;
        }
        return TS;
    }

    public static void main(String[] args) {
        MinAbsSum mas = new MinAbsSum();
        int[] A = {-7933,-1642,-6137,6234,4728,5474,2439};

        System.out.println(mas.minAbsDifferenceA(A,-428059487));
    }
}

