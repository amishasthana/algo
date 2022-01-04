/*
1300. Sum of Mutated Array Closest to Target

    User Accepted: 743
    User Tried: 1009
    Total Accepted: 765
    Total Submissions: 2303
    Difficulty: Medium

Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.

In case of a tie, return the minimum such integer.

Notice that the answer is not neccesarilly a number from arr.



Example 1:

Input: arr = [4,9,3], target = 10
Output: 3
Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.

Example 2:

Input: arr = [2,3,5], target = 10
Output: 5

Example 3:

Input: arr = [60864,25176,27249,21296,20204], target = 56803
Output: 11361



Constraints:

    1 <= arr.length <= 10^4
    1 <= arr[i], target <= 10^5


 */
public class MutatedArrayClosestToTarget {
    public int findBestValue(int[] arr, int target) {
       int avgLow = target/arr.length;
        int avgHigh = avgLow+1;
        int minLow = 0; int minHigh = 0;
        int max = Integer.MIN_VALUE;
        int totalSum = 0;
       for(int i : arr) {
           if (i > max) max = i;
           totalSum += i;
           if (i > avgLow)  {
               minLow += avgLow;
           } else {
               minLow += i;
           }
           if (i > avgHigh)  {
               minHigh += avgHigh;
           } else {
               minHigh += i;
           }
       }
       if (totalSum == target) return max;//No change
       if (Math.abs(minLow-target) <= Math.abs(minHigh-target)) {
            return avgLow;
        }
        return avgHigh;
    }
}
