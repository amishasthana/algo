package DP;

public class HouseRob {
    public int rob(int[] nums) {
        if ( ( nums == null) || (nums.length == 0) ) return 0;
        int n = nums.length;

        //int[][] dp = new int[n+1][2];// dp --> max value till ith house. 0-> Not robbed, 1 --> robbed.
        int robbed = 0;
        int not_robbed = 0;
        for(int i = 1; i <= n;i++) {
            //dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
            //dp[i][1] = Math.max( dp[i-1][0]+nums[i-1],dp[i][1]);
            int temp = not_robbed+nums[i-1];
            not_robbed = Math.max(not_robbed,robbed);
            robbed = Math.max(temp,robbed);
        }
        //return Math.max(dp[n][0], dp[n][1]);
        return Math.max(not_robbed,robbed);

    }

    public static void main(String[] args) {
        HouseRob hr = new HouseRob();
        int[] A = {2,7,9,3,1};
        System.out.println(hr.rob(A));
    }

}
