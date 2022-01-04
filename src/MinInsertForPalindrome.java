import java.util.HashMap;
import java.util.Map;

/*
1312. Minimum Insertion Steps to Make a String Palindrome

    User Accepted: 1121
    User Tried: 1493
    Total Accepted: 1198
    Total Submissions: 2539
    Difficulty: Hard

Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.



Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we don't need any insertions.

Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".

Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".

Example 4:

Input: s = "g"
Output: 0

Example 5:

Input: s = "no"
Output: 1



Constraints:

    1 <= s.length <= 500
    All characters of s are lower case English letters.


SOLUTION :

Let's imagine matching the characters of the string like a palindrome, from the begining and the end with 2 pointers i and j.
We may encounter 2 scenarios:The character at i matches character at j.The characters don't match each otherIn case of 1 we just increase the pointer i and decrease the pointer j, i++ and j-- respectively.In the second case we have 2 options:Insert one character at j to match the character at i.OrInsert one character at i to match the character at j.Since we are not actually adding the characters in the string but just calculating the cost,
In case 1 we increase the pointer i by 1 as we still need a character to match at j
and in case 2 we decrease the pointer j by 1 as we still need a character to match at i.
both the cases adds cost 1.We can then use these two different pairs of new i and j values (i+1, j and i, j-1) to again repeat the process and use the minimum result of these as our result for current pair of i, j.
We can see that this is recursive and thus we can use recursion with caching to store the repeated values.

 */
public class MinInsertForPalindrome {

    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1]; // Stores number of max equal character.
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                // Reverse : s.charAt(n - 1 - j)
                dp[i + 1][j + 1] = (s.charAt(i) == s.charAt(n - 1 - j)) ? dp[i][j] + 1 : Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }
        return n - dp[n][n];
    }

    Map<String,Integer> calcValue = new HashMap<>();

    public int minInsertionsSecond(String s) {
        char[] c = s.toCharArray();

        return minInsertionsSuingRecursion(c,0,c.length-1);
    }



    public int minInsertionsSuingRecursion(char[] c,int i,int j) {
       if (i >= j) return 0;
       String key = i+","+j;
       if (calcValue.get(key) != null) return calcValue.get(key);
       if (c[i] == c[j])  {
           calcValue.put(key,minInsertionsSuingRecursion(c,i+1,j-1));
       }  else {
           calcValue.put(key,1+Math.min(minInsertionsSuingRecursion(c,i+1,j),minInsertionsSuingRecursion(c,i,j-1)));
       }
       return calcValue.get(key);
    }

    public static void main(String[] args) {
        String s = "abavv";
        String s1 = "tldjbqjdogipebqsohdypcxjqkrqltpgviqtqz";
        MinInsertForPalindrome mis = new MinInsertForPalindrome();
        System.out.println(mis.minInsertionsSecond(s1));
    }
}
