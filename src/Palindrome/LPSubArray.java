package Palindrome;

import java.util.Arrays;

public class LPSubArray {
    public int longestPalindromeSubarray(String s) {
        if ( ( s == null) || (s.length() == 0)) return 0;
        int[] dp = new int[s.length()];
// PD length ending at i. As all are one char, so initial value 1.
        Arrays.fill(dp,1);
        int mL = 1;
        for(int i = 1; i < s.length(); i++) {
            // say 2,3,4,5,6  At 5 l = 3.
            int back = i - (dp[i - 1] + 1);
            if ((back >= 0) && (s.charAt(i) == s.charAt(back))) {
                dp[i] = dp[i - 1] + 2;

            } else if (s.charAt(i) == s.charAt(i - 1)) {// Even starting.
                dp[i] = 2;
            }
            mL = Math.max(dp[i], mL);
        }
        return mL;
    }

    public static void main(String[] args) {
        LPSubArray rp = new LPSubArray();
        System.out.println(rp.longestPalindromeSubarray("cbbd"));

    }
}
