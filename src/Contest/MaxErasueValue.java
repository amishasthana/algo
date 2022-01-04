package Contest;

import java.util.BitSet;

/*
1695. Maximum Erasure Value

    User Accepted: 2828
    User Tried: 3657
    Total Accepted: 2896
    Total Submissions: 6816
    Difficulty: Medium

You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is,
if it is equal to a[l],a[l+1],...,a[r] for some (l,r).



Example 1:

Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].

Example 2:

Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].



Constraints:

    1 <= nums.length <= 105
    1 <= nums[i] <= 104


 */
public class MaxErasueValue {
    public int maximumUniqueSubarray(int[] nums) {
        BitSet bSet = new BitSet(10000);
        int i = 0; int j = 0;
        int len = nums.length;
        int sum = 0;
        int cElem = nums[j];
        int maxSum = Integer.MIN_VALUE;
        while (j < len) {//right counter
            while (!bSet.get(cElem)) { // not in current set
                bSet.flip(cElem);
                sum += cElem;
                if ((++j) >= nums.length) return Math.max(maxSum,sum);
                cElem = nums[j];
            }
            maxSum = Math.max(maxSum,sum);
            while(cElem != nums[i]) {
                sum -= nums[i];
                bSet.flip(nums[i++]);
            }
            sum -= nums[i];
            bSet.flip(nums[i++]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] a = {5,2,1,2,5,2,1,2,5};
        MaxErasueValue mev = new MaxErasueValue();
        System.out.println(mev.maximumUniqueSubarray(a));
    }
}
