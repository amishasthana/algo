/*
516. Longest Palindromic Subsequence
Medium

Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"

Output:

4

One possible longest palindromic subsequence is "bbbb".

Example 2:
Input:

"cbbd"

Output:

2

One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubseq {
    public int longestPalindromeSubseq(String s) {
        int l = s.length();
        int[][] a = new int[l][l];
        for(int i = 0; i < l; i++) a[i][i] = 1;

        for(int i = l-1; i >= 0;i--) {//left most
            for(int j = i+1; j < s.length();j++) {//right most
                if ( s.charAt(i) == s.charAt(j) ) {
                    a[i][j] = a[i+1][j-1] +2;
                } else {
                    a[i][j] = Math.max(a[i][j-1],a[i+1][j]);
                }
            }// end of inner for
        }// end of outer for.
        return a[0][s.length()-1];
    }

    public static void main(String[] args) {
        LongestPalindromicSubseq lps = new LongestPalindromicSubseq();
        System.out.println(lps.longestPalindromeSubseq("aebcbda"));
    }
}
