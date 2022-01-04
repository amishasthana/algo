/*
1262. Greatest Sum Divisible by Three

    User Accepted: 1076
    User Tried: 1967
    Total Accepted: 1108
    Total Submissions: 4341
    Difficulty: Medium

Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.



Example 1:

Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).

Example 2:

Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.

Example 3:

Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).



Constraints:

    1 <= nums.length <= 4 * 10^4
    1 <= nums[i] <= 10^4

Solution
a) Go through each element. If divisible by 3 then just add them.
b) Not divisible two different heap(max heap) --> rem = 2 and rem = 1/
   At end start draining from heap and add as much as possible
   One special case. one side 3 and other side 2 or 1.
     Then it become special cases :
       3 and 1 --> 1,1 or 1,2,3
       3 and 2 --> (1,1) and (2,2) or (1,2.3)

       CASES
       if all equal add all.
       if both are greater then 3--> add one of each till one is less then 3.
       Atleast one is now less then 3.
       Now if one if greater then 3 and other is not zero --> Drain and add each side till that become = 3 (Take care of 4,5 .
                        Now 3 on one side and possibly 0,1,2 --> if 0 -> 3, if 1 and 2 special calculation.
       If both less then 3 2,1 or 1,0

 */
public class GreatestSumDivisibleByThree {

    /*
    public int maxSumDivThree(int[] A) {
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int a : A) {
            int[] dp2 = new int[3];
            for (int i = 0; i < 3; ++i)
                dp2[(i + a) % 3] = Math.max(dp[(i + a) % 3], dp[i] + a);
            dp = dp2;
        }
        return dp[0];
    }
    */

    public int maxSumDivThree(int[] A) {
        int[] dp = new int[]{0,Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int a : A) {
            int d0 = dp[0];
            int d1 = dp[1];
            int d2 = dp[2];
            // All that code is equivalent to :  dp2[(i + a) % 3] = Math.max(dp[(i + a) % 3], dp[i] + a);
            if (a%3 == 0) {// a is divisble by 3.
                dp[0] = d0+a;
                dp[1] = d1+a;
                dp[2] = d2+a;
            } else if (a%3 == 1) {
                dp[0] = Math.max(d0,d2+a);
                dp[1] = Math.max(d1,d0+a);
                dp[2] = Math.max(d2,d1+a);
            } else {//rem = 2
                dp[0] = Math.max(d0,d1+a);
                dp[1] = Math.max(d1,d2+a);
                dp[2] = Math.max(d2,d0+a);
            }
            System.out.println(" Integer is "+a);
            System.out.println(dp[0]+" "+dp[1]+" "+dp[2]+" ");
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[] iA = {3,6,5,1,8};//18
        int[] iA1 = {4};//0
        int[] iA2 = {1,2,3,4,4};//12
        int[] iA3 = {1,1,5,4,7};//18
        int[] iA4 = {13,21,7,27,40,18,37,7,31,5};
        GreatestSumDivisibleByThree gsm = new GreatestSumDivisibleByThree();
        //System.out.println(gsm.maxSumDivThree(iA));
        //System.out.println(gsm.maxSumDivThree(iA1));
        //System.out.println(gsm.maxSumDivThree(iA2));
        //System.out.println(gsm.maxSumDivThree(iA3));
        System.out.println(gsm.maxSumDivThree(iA4));
    }


}
