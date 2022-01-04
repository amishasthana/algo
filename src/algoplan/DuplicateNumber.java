package algoplan;
/*
287. Find the Duplicate Number
Medium

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.



Example 1:

Input: nums = [1,3,4,2,2]
Output: 2

Example 2:

Input: nums = [3,1,3,4,2]
Output: 3

Example 3:

Input: nums = [1,1]
Output: 1

Example 4:

Input: nums = [1,1,2]
Output: 1



Constraints:

    1 <= n <= 105
    nums.length == n + 1
    1 <= nums[i] <= n
    All the integers in nums appear only once except for precisely one integer which appears two or more times.

http://keithschwarz.com/interesting/code/?dir=find-duplicate
 */
public class DuplicateNumber {
    public int findDuplicate(int[] nums) {
        int S = 0;
        int F = 0;
        do {
            S = nums[S];
            F = nums[nums[F]];
        } while (S != F);
        S = 0;
        while (F != S) {
            S = nums[S];
            F = nums[F];
        }
        return S;
    }
}
