package Contest;
/*
1802. Maximum Value at a Given Index in a Bounded Array

    User Accepted: 1171
    User Tried: 2213
    Total Accepted: 1219
    Total Submissions: 6573
    Difficulty: Medium

You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

    nums.length == n
    nums[i] is a positive integer where 0 <= i < n.
    abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
    The sum of all the elements of nums does not exceed maxSum.
    nums[index] is maximized.

Return nums[index] of the constructed array.

Note that abs(x) equals x if x >= 0, and -x otherwise.



Example 1:

Input: n = 4, index = 2,  maxSum = 6
Output: 2
Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].

Example 2:

Input: n = 6, index = 1,  maxSum = 10
Output: 3



Constraints:

    1 <= n <= maxSum <= 109
    0 <= index < n


 */
public class MaxValueIndex {
    int index; int len;
    public int maxValue(int n, int index, int maxSum) {
        int ret = maxSum - n;
        len = n;
        if (ret == 0) return 1;
        int L = 0; int R = ret;
        this.index = index;
        int m = 1;
        while ( L <= R) {
            m = L + (R - L)/2;
            if ( (m+ find(m,true) + find(m,false) ) < ret) {
                L = m+1;
            } else {
                R = m -1;
            }

        }
        return m;
    }

    /*
         0,1,2,3,4,5

    */
    private int find(int m,boolean left) {
        int nums = 0;
        if (left) {
            nums = index;
        } else {
            nums =  (len - index -1);
        }
        int rV = getSum(m-1);
        if ( (m -1 ) > nums) {// 4
            rV -=  getSum(m -1 -nums);
        }
        return rV;
    }

    private int getSum(int n) {
        return (n*(n+1))/2;
    }

    public static void main(String[] args) {
        MaxValueIndex mvi = new MaxValueIndex();
        int[] A = {};
        System.out.println(mvi.maxValue(6,1,10));
    }



}
