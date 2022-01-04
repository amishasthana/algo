package FB;
/*
34. Find First and Last Position of Element in Sorted Array
Medium

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

Follow up: Could you write an algorithm with O(log n) runtime complexity?



Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:

Input: nums = [], target = 0
Output: [-1,-1]



Constraints:

    0 <= nums.length <= 105
    -109 <= nums[i] <= 109
    nums is a non-decreasing array.
    -109 <= target <= 109

Accepted
634,422
Submissions
1,707,554
 */
public class FindIndex {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1,-1};

        if (!findNum(nums,target,0,nums.length,true,ans)) {
            return ans;
        }
        findNum(nums,target,ans[0],nums.length,false,ans);

        return ans;
    }

    private boolean findNum(int[] nums,int v,int l,int r,boolean findL,int[] ans) {
        boolean vFound = false;

        while(l <= r) {
            int m = l + (r-l)/2;
            if ( (m < 0) || (m >= nums.length)) break;
            if (nums[m] > v) {
                r = m-1;
            } else if (nums[m] < v) {
                l = m+1;
            } else {
                vFound = true;
                if (findL) {
                    ans[0] = m;
                    r = m-1;
                } else {
                    ans[1] = m;
                    l = m+1;
                }
            }
        }
        return vFound;
    }
}
