package Contest;
/*
1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit

    User Accepted: 2704
    User Tried: 4807
    Total Accepted: 2802
    Total Submissions: 10830
    Difficulty: Medium

Given an array of integers nums and an integer limit, return the size of the longest continuous subarray such that the

Input: nums = [10,1,2,4,7,2], limit = 5
Output: 4
Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.

Example 3:

Input: nums = [4,2,2,2,4,4,2,2], limit = 0
Output: 3



Constraints:

    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^9
    0 <= limit <= 10^9


 */
import java.util.*;
public class LongestContinousSubarray {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> minQ = new ArrayDeque<>();//From smallest to highest
        Deque<Integer> maxQ = new ArrayDeque<>();//From higest to smallest
        int maxL = Integer.MIN_VALUE;
        int i = 0;
        for(int j = 0; j < nums.length;j++) {
            while ( !minQ.isEmpty() && (nums[j] < nums[minQ.peekLast()])) minQ.pollLast();
            while ( !maxQ.isEmpty() && (nums[j] > nums[maxQ.peekLast()])) maxQ.pollLast();
            minQ.add(j);
            maxQ.add(j);
            //if ()
        }

        return maxL;
    }
}
