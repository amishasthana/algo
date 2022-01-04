package Contest;

import java.util.Arrays;

/*
689. Maximum Sum of 3 Non-Overlapping Subarrays
Hard

In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed).
If there are multiple answers, return the lexicographically smallest one.

Example:

Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.



Note:

    nums.length will be between 1 and 20000.
    nums[i] will be between 1 and 65535.
    k will be between 1 and floor(nums.length / 3).


Accepted
50,912
Submissions
107,982
Seen this question in a real interview before?
 */
public class MaxSumThreeSubArray {
    /*
    The basic idea is simple
       Find the maximum sum for an array upto an index. (Lets say it will not include the index so you can got to L.
       Then the problem becomes --> For the remaining find the max sum of remaining arrays(here 2).
       You can maintain another MAX but this one is starting from left side....

       This work for THREE, how to generalize??
     */

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int l = nums.length;
        int[] pSum = new int[l];
        int[] rEnding = new int[l], lEnding = new int[l];// let us just try to store the index.
        Arrays.fill(lEnding,l-k);//Starting from l-k
        pSum[0] = nums[0];
        for(int i = 1; i < l ; i++) {
            pSum[i] = pSum[i-1]+nums[i];// nth contain 0--- n-1
        }
        rEnding[k-1] = 0;
        for(int i = k;i < l;i++) {
            // 0,1,2,3,4,5,6,7
            int sLastIndex = rEnding[i-1];
            if ((pSum[sLastIndex+k-1]-((sLastIndex == 0)?0:pSum[sLastIndex-1])) >= (pSum[i]-pSum[i-k])) {
                rEnding[i] = rEnding[i-1];
            } else {
                rEnding[i] = i-k+1;
            }
        }
        lEnding[l-k] = l-k;// l = 8, 5 --> 5 --> 5,6,7
        for(int i = l-k-1; i >= k;i--) {
            // 0,1,2,3,4,5,6,7
            int sLastIndex = lEnding[i+1];
            if ((pSum[sLastIndex+k-1] - pSum[sLastIndex-1]) > (pSum[i+k-1]-pSum[i-1])) {
                lEnding[i] = lEnding[i+1];
            } else {
                lEnding[i] = i;
            }
        }
        int mSum = Integer.MIN_VALUE;
        int[] ans = new int[3];
        for(int i = k;i <= l-2*k;i++) {// i = 3 middle starting from there.
            int fStart = rEnding[i-1];//2  3,4,5 is middle
            int tStart = lEnding[i+k];//6
            int tempSum = (pSum[fStart+k-1] - ((fStart == 0)?0:pSum[fStart-1]))+(pSum[tStart+k-1]- pSum[tStart-1])+(pSum[i+k-1]- pSum[i-1]);
            if ( tempSum > mSum) {
                ans[0] = fStart;ans[1] = i;ans[2] = tStart;
                mSum = tempSum;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = {7,13,20,19,19,2,10,1,1,19};
        MaxSumThreeSubArray mst = new MaxSumThreeSubArray();
        System.out.println(Arrays.toString(mst.maxSumOfThreeSubarrays(A,3)));
    }


}
