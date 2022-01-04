package DP;
/*

647. Palindromic Substrings
Medium

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".



Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".



Note:

    The input string length won't exceed 1000.
      a   a       b
    0 1 2 3  4    5  6


 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if ( (s == null) || (s.length() == 0)) return 0;
        int c = 0;
        for(int i = 0; i < s.length(); i++) {
            c += findPalin(s,i,i); // odd.
            c += findPalin(s,i,i+1); // even.
        }
        return c;
    }

    private int findPalin(String ss, int s, int e) {
        if (e >= ss.length() ) return 0;
        int count = 0;
        while ( (s >= 0) && (e < ss.length() ) && ( ss.charAt(s--)  == ss.charAt(e++) ) ) count++;
        return count;
    }


    public static void main(String[] args) {
        PalindromicSubstrings pd = new PalindromicSubstrings();
        System.out.println(pd.countSubstrings("aaa"));

    }

}
