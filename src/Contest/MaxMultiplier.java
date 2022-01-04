package Contest;

import java.util.Arrays;

/*
1770. Maximum Score from Performing Multiplication Operations

    User Accepted: 827
    User Tried: 3122
    Total Accepted: 892
    Total Submissions: 8295
    Difficulty: Medium

You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.

You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:

    Choose one integer x from either the start or the end of the array nums.
    Add multipliers[i] * x to your score.
    Remove x from the array nums.

Return the maximum score after performing m operations.



Example 1:

Input: nums = [1,2,3], multipliers = [3,2,1]
Output: 14
Explanation: An optimal solution is as follows:
- Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
- Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
- Choose from the end, [1], adding 1 * 1 = 1 to the score.
The total score is 9 + 4 + 1 = 14.

Example 2:

Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
Output: 102
Explanation: An optimal solution is as follows:
- Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
- Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
- Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
- Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
- Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
The total score is 50 + 15 - 9 + 4 + 42 = 102.



Constraints:

    n == nums.length
    m == multipliers.length
    1 <= m <= 103
    m <= n <= 105
    -1000 <= nums[i], multipliers[i] <= 1000


 */
public class MaxMultiplier {
    long mSum = 0;
    int[] M = null;
    int ML;
    int nL;
    int[] N;
    long[][] C = null;
    public int maximumScore(int[] nums, int[] multipliers) {
        M = multipliers;
        N = nums;
        ML = M.length;
        nL = nums.length;
        C = new long[ML][ML];
        for (int i = 0; i < ML;i++) {
                Arrays.fill(C[i],Integer.MIN_VALUE);
        }
        mSum = findSum(0,0);// nS --> Strating index. ML --> number of elements to pick.
        return (int)(mSum % 1_000_000_007);
    }

    // both inclusive.
    private long findSum(int nS,int mS) {
        if (mS == ML) return 0;
        int indexR = nL-1-(mS-nS);
        if ( C[nS][mS] != Integer.MIN_VALUE) return C[nS][mS];

        long left = findSum(nS+1,mS+1)+ (N[nS]*M[mS]);
        long right = findSum(nS,mS+1)+ (N[indexR]*M[mS]);
        C[nS][mS] = Math.max(left,right);
        return C[nS][mS];
    }

    public static void main(String[] args) {
        MaxMultiplier mm = new MaxMultiplier();
        int[] nums = {-5,-3,-3,-2,7,1};
        int[] multi = {-10,-5,3,4,6};
        System.out.println(mm.maximumScore(nums,multi));
    }

}
