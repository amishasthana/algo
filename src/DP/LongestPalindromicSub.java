package DP;
/*
5. Longest Palindromic Substring
Medium

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"


 */
public class LongestPalindromicSub {
    public String longestPalindrome(String s) {
        if ( (s == null) || (s.length() == 0) ) return "";
        String max = "";
        for(int i = 0; i < s.length();  i++) {
            String s1 = solve(s,i,i);
            String s2 = solve(s,i,i+1);
            if  ( (s1.length() > max.length()) && (s1.length() > s2.length()) ) {
                max = s1;
            } else if ( (s2.length() > max.length()) && (s2.length() > s1.length()) )  {
                 max = s2;
            }

        }
        return max;

    }

    private String solve(String str, int s,int e) {
        //StringBuilder strB = new StringBuilder();
        while   ( ( s >= 0) && (e < str.length() ) && (str.charAt(s) == str.charAt(e)) ) {
            s--;e++;
        }
        return str.substring(s+1,e);

    }

    public static void main(String[] args) {
        LongestPalindromicSub lps = new LongestPalindromicSub();
        System.out.println(lps.longestPalindrome("babad"));
    }


}
