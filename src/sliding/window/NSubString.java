package sliding.window;
/*
1358. Number of Substrings Containing All Three Characters
Medium

Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.



Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).

Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".

Example 3:

Input: s = "abc"
Output: 1

 */

public class NSubString {
    /* one occurence of a or b or c */
    public int numberOfSubstrings(String s) {
        return atLeast(s);
    }

    /*
    a,b,c
    abacb
    */
    private int atLeast(String s) {
        int[] f = new int[3];
        int freq = 0;
        int j = 0;
        for(int i = 0;i < s.length(); i++) {
            f[s.charAt(i) - 'a'] += 1;
                while( (f[0] > 0) && ( f[1] > 0) && (f[2] > 0) ) {
                    f[s.charAt(j) - 'a'] -= 1;
                    freq += (s.length()-i);
                    j++;
                }//End of while

        }//End of for.
        return freq;
    }

    public static void main(String[] args) {
        NSubString ns = new NSubString();
        System.out.println(ns.numberOfSubstrings("acbbcac"));
        System.out.println(ns.numberOfSubstrings("abc"));
        System.out.println(ns.numberOfSubstrings("abc"));
        System.out.println(ns.numberOfSubstrings("abcabc"));
        System.out.println(ns.numberOfSubstrings("aaacb"));
    }

}
