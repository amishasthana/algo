package Contest;
/*
1712. Ways to Split Array Into Three Subarrays

    User Accepted: 664
    User Tried: 1866
    Total Accepted: 697
    Total Submissions: 5103
    Difficulty: Medium

A split of an integer array is good if:

    The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
    The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.

Given nums, an array of non-negative integers, return the number of good ways to split nums.
 As the number may be too large, return it modulo 109 + 7.



Example 1:

Input: nums = [1,1,1]
Output: 1
Explanation: The only good way to split nums is [1] [1] [1].

Example 2:

Input: nums = [1,2,2,2,5,0]
Output: 3
Explanation: There are three good ways of splitting nums:
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]

Example 3:

Input: nums = [3,2,1]
Output: 0
Explanation: There is no good way to split nums.



Constraints:

    3 <= nums.length <= 105
    0 <= nums[i] <= 104


 */
public class ThreeWaySplit {
    public int waysToSplit(int[] nums) {
            int mod = (int) ( 1e9+7);
            int L = nums.length;
            int[] pA = new int[L]; pA[0] = nums[0];
            for(int i = 1; i < L;i++) pA[i] = pA[i-1] + nums[i];
            long tWays = 0;
            for(int i = 1; i < L;i++) {
                if (pA[i-1] > (pA[L-2]- pA[i-1])) break; //can not be greater then 1/3
                long left = find(pA,i,true);
                long right = find(pA,i,false);
                if ((right != -1) && (left != -1)) {
                    tWays += (right-left+1);
                }
            }
            return (int)(tWays%mod);
    }

    private long find(int[] pA,int i,boolean findLeft) {// you start from i to N-2
        int l = i;
        int L = pA.length;
        int r = L-2;
        int f = -1;
        while (l <= r) {
            int m = (l+r)/2;
            int mSum = pA[m] - pA[i-1];
            int rSum = pA[L-1] - pA[m];
            if ( (mSum >= pA[i-1]) && (rSum >= mSum)) {//candidate.
                f = m;
                if (findLeft) {
                    r = m-1;
                } else {
                    l = m+1;
                }
            } else if (mSum < pA[i-1]) {
                l = m+1;
            } else {
                r = m-1;
            }
        }
        return f;
    }

    public static void main(String[] args) {
        ThreeWaySplit tws = new ThreeWaySplit();
        int[] A = {1,1,1};
        System.out.println(tws.waysToSplit(A));
    }
}
