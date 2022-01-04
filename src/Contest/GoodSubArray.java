package Contest;
/*
1793. Maximum Score of a Good Subarray

    User Accepted: 1151
    User Tried: 2015
    Total Accepted: 1248
    Total Submissions: 3974
    Difficulty: Hard

You are given an array of integers nums (0-indexed) and an integer k.

The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.

Return the maximum possible score of a good subarray.



Example 1:

Input: nums = [1,4,3,7,4,5], k = 3
Output: 15
Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.

Example 2:

Input: nums = [5,5,4,5,4,1,1,1], k = 0
Output: 20
Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.



Constraints:

    1 <= nums.length <= 105
    1 <= nums[i] <= 2 * 104
    0 <= k < nums.length

     public int maximumScore(int[] A, int k) {
        int res = A[k], mini = A[k], i = k, j = k, n = A.length;
        while (i > 0 || j < n - 1) {
            if (i == 0)
           jeod     ++j;
            else if (j == n - 1)
                --i;
            else if (A[i - 1] < A[j + 1])
                ++j;
            else
                --i;
            mini = Math.min(mini, Math.min(A[i], A[j]));
            res = Math.max(res, mini * (j - i + 1));
        }
        return res;
    }



 */
public class GoodSubArray {
    public int maximumScore(int[] nums,int k) {
        int min = nums[k];
        int ret = nums[k];
        int i = k;
        int j = k;
        int l = nums.length;
         /*
              I will track using two pointer the min in interval. Lets say current MIN is :
                 Go till i and j --> such that i+1 or j-1 if they exist are less then MIN.(
                 so ret = (j-i+1)*min
                 now cases:
                     if either side done --> Break. Easier to do one loop. --> Or just set the mon of that side as max.
                     if both side done, break
                     otherwise find max of the two lowest and try to go as f/ar as possible. It should go only one side.
          */
        while ((i > 0) || (j < (l - 1))) {
            while ((i > 0) && (nums[i - 1] >= min)) i--;
            while ((j < (l - 1)) && (nums[j + 1] >= min)) j++;
            ret = Math.max(ret,min*(j-i+1));

            min = Math.max((i == 0)?Integer.MIN_VALUE:nums[i-1],(j == (l-1))?Integer.MIN_VALUE:nums[j+1]);
            if ((i > 0)  && (nums[i-1] == min)) {
                i--;//Only increase on side where we are concerned.
            }
            if ( (j < (l-1)) && (nums[j+1] == min)) {
                j++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] A = {6569,9667,3148,7698,1622,2194,793,9041,1670,1872};
        GoodSubArray gsa = new GoodSubArray();
        System.out.println(gsa.maximumScore(A,5));
    }



}
