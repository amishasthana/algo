/*
238. Product of Array Except Self
Medium

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]

Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

 */
public class ProductArray {
    public int[] productExceptSelf(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return nums;
        int[] res = new int[nums.length];
        res[0] = 1;//Nothing to its left
        for(int i = 1;i < nums.length;i++) {//Toward left.
            res[i] = res[i-1]*nums[i-1];
            // 1,1,2,6
        }
        int rTemp  = 1;
        for(int i = (nums.length-1);i >= 0;i--) {//Toward right.
            res[i] = res[i]*rTemp;
            rTemp = rTemp*nums[i];
        }
        return res;
    }
}
