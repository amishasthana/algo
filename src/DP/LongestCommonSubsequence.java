package DP;
/*
1143. Longest Common Subsequence
Medium

Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.



If there is no common subsequence, return 0.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.

 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        if ( (text1 == null) || (text2 == null) ) return 0;
        if (text2.length() < text1.length() ) return      longestCommonSubsequence(text2,text1);
        if (text1.length() == 0) return 0;

        int n = text1.length(); int m = text2.length();
        int[][] dp = new int[n+1][m+1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if ( text2.charAt(j-1) == text1.charAt(i-1) )  {
                    dp[i][j]  = dp[i-1][j-1]+1;
                } else {
                    dp[i][j]  = Math.max(dp[i-1] [j] , dp[i][j-1] );
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println(lcs.longestCommonSubsequence("abcde","ace"));
    }

}
