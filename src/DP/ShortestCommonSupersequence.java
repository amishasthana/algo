package DP;
/*


Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)



Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.



Note:

    1 <= str1.length, str2.length <= 1000
    str1 and str2 consist of lowercase English letters.

    cab
       abac

       Lets say i find the set of unique substring which are common to each other.

 */

import java.util.Arrays;

public class ShortestCommonSupersequence {
    public int shortestCommonSupersequenceL(String str1, String str2) {
        int n = str1.length(); int m = str2.length();
        int[][] DP = new int[n+1][m+1];
        for( int i = 1 ; i <=  n; i++) {//DP array index.
            for( int j = 1; j <= m; j++ ) {
                if ( str1.charAt(i-1) == str2.charAt(j-1) ) {
                    DP[i][j] = 1 + DP[i-1][j-1];
                } else {
                    DP[i][j] = Math.max(  DP[i][j-1]  , DP[i-1][j] );
                }
            }
        }
        return ( str1.length()+str2.length() - DP[n][m]);
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        if ( (str1 == null) || (str2 == null) || (str1.length() == 0) || (str2.length() == 0)) return "";
        int n = str1.length(); int m = str2.length();
        String[][] DP = new String[n+1][m+1];
        for(String[] a: DP ) Arrays.fill(a,"");
        for( int i = 1 ; i <=  n; i++) {//DP array index.
            for( int j = 1; j <= m; j++ ) {
                if ( str1.charAt(i-1) == str2.charAt(j-1) ) {
                    DP[i][j] =  DP[i-1][j-1]+str1.charAt(i-1);
                } else {
                    if (DP[i][j-1].length() >= DP[i-1][j].length()) {
                        DP[i][j] =DP[i][j-1];
                    } else {
                        DP[i][j] =  DP[i-1][j];
                    }
                }
            }
        }
        String cS = DP[n][m];
        int i = 0; int j = 0;
        StringBuilder strB = new StringBuilder();
        for(char c : DP[n][m].toCharArray() ) {
            while (c != str1.charAt(i)) {
                strB.append(str1.charAt(i++));
            }
            while (c != str2.charAt(j)) {
                strB.append(str2.charAt(j++));
            }
            strB.append(c);i++;j++;
        }
        strB.append(str1.substring(i));
        strB.append(str2.substring(j));

        return strB.toString();
    }

    private void populate(String s,String[] sB,String cS) {
        StringBuilder strB = new StringBuilder();
        int j = 0;// the cs string.
        int i = 0;//main string
        for(; i < sB.length  && (j < cS.length());i++ ) {
            while ((i < s.length()) && (s.charAt(i) != cS.charAt(j))) {
                strB.append(s.charAt(i++));
            }
            sB[j] = strB.toString();
            strB.delete(0,strB.length());
            j++;
        }
        for(;i < s.length();i++) {
            strB.append(s.charAt(i));
        }
        sB[j] = strB.toString();
    }

/*
ab


    */
    public static void main(String[] args) {
        ShortestCommonSupersequence scs = new ShortestCommonSupersequence();
        System.out.println(scs.shortestCommonSupersequence("abac","cab"));
    }

}
