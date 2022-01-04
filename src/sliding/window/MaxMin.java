package sliding.window;
/*
1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit

    User Accepted: 2703
    User Tried: 4807
    Total Accepted: 2801
    Total Submissions: 10830
    Difficulty: Medium

Given an array of integers nums and an integer limit, return the size of the longest continuous subarray such that the absolute difference between any two elements is less than or equal to limit.

In case there is no subarray satisfying the given condition return 0.



Example 1:

Input: nums = [8,2,4,7], limit = 4
Output: 2
Explanation: All subarrays are:
[8] with maximum absolute diff |8-8| = 0 <= 4.
[8,2] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
[2] with maximum absolute diff |2-2| = 0 <= 4.
[2,4] with maximum absolute diff |2-4| = 2 <= 4.
[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
[4] with maximum absolute diff |4-4| = 0 <= 4.
[4,7] with maximum absolute diff |4-7| = 3 <= 4.
[7] with maximum absolute diff |7-7| = 0 <= 4.
Therefore, the size of the longest subarray is 2.

Example 2:

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
public class MaxMin {
    public int longestSubarray(int[] nums, int limit) {
        int len =  nums.length;
        int mLen = Integer.MIN_VALUE;
        int i = 0; int j = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(;j < len;j++ ) {
            max = Math.max(nums[j],max);
            min = Math.min(nums[j],min);
            if ( ( max - min) <= limit ) {
                mLen = Math.max(mLen,j-i+1);
            } else {
                       /*
                       while ( (i <= j) && (( max - min) > limit) ) {
                                 max = Math.max(nums[i++],max);
                                 min = Math.min(nums[i++],min);
                       }
                       */
                while ( i <= j ) {
                    int v = nums[i++];
                    if ( (v <= min) || (v >= max) ) break;
                }
            }

        }// End of for loop.
        return mLen;
    }

    public static void main(String[] args) {
        int[] A = {8,2,4,7};
        MaxMin mm = new MaxMin();
        System.out.println(mm.longestSubarray(A,4));


    }
}
