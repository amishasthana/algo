package algoplan;

import java.util.Set;

/*
10. Regular Expression Matching
Hard

Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

    '.' Matches any single character.​​​​
    '*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".



Constraints:

    1 <= s.length <= 20
    1 <= p.length <= 30
    s contains only lowercase English letters.
    p contains only lowercase English letters, '.', and '*'.
    It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.

 '.' Matches any single character.​​​​
    '*' Matches zero or more of the preceding element.

 */
public class RegexMatching {
    boolean matched = false;
    private static final char STAR = '*';
    private static final char DOT = '.';
    private static final Set<Character> SPECIAL = Set.of(STAR,DOT);
    public boolean isMatch(String s, String p) {
         int strL = s.length();
         int pLen = p.length();
         int[][] M = new int[strL][pLen];//will use to prune branches.
         char[] sA = s.toCharArray();
         char[] pA = p.toCharArray();
         match(sA,pA,0,0,M);

        return matched;
    }

    //start matching from s and p inclusive.
    private boolean match(char[] sA,char[] pA,int s,int p,int[][] M) {

        if ((s < sA.length) && (pA.length == p) ) {
            return false;// pattern consumed.
        } else if ((s == sA.length) && (pA.length == p) ) {
            matched = true;
            return matched;
        } else if ((s == sA.length) && (pA.length > p) ) {

            if  (canPatternConsumed(pA,p)) {
                matched = true; return  matched;
            } else {
                matched = false; return  matched;
            }
        }
        //System.out.println(s+","+p);
        if (matched || (M[s][p] ==1))  return true;

        boolean starP = false;
        if (p < (pA.length-1)) {
            if (pA[p+1] == STAR) starP = true;
        }
        if (starP) {
            char preceding = pA[p];
            /*
                Two cases --> Completely ignore or current char matches and dont move the s.
             */
            if ((preceding != DOT) && (preceding != sA[s])) {//only option to ignore.
                return  match(sA,pA,s,p+2,M);// move ahead of star
            } else if ((preceding == DOT) || (preceding == sA[s])) { // All option
                return   ( match(sA,pA,s,p+2,M) || match(sA,pA,s+1,p,M) );
            }
        } else if (pA[p] == DOT) {
            M[s][p] = 1;
            return match(sA,pA,s+1,p+1,M);
        }else  {
            if (pA[p] == sA[s]) {
                M[s][p] = 1;
                return match(sA,pA,s+1,p+1,M);
            } else {
                return false;
            }
        }
        return matched;
    }

    // pattern need to be <something>*
    private boolean canPatternConsumed(char[] pA,int p) {
        boolean con = true;
        for(int i = pA.length-1;i >= p;) {
            con = con && (pA[i] == STAR);
            if (!con) return con;
            i -= 2;
        }
        return con;
    }

    public static void main(String[] args) {
        RegexMatching rm = new RegexMatching();
        String s3 = "abbcacbbbbbabcbaca";
        String s4 = "a*a*.*a*.*a*.b*a*";
        String s5 = ".*.";

        String s1 = "aabcbcbcaccbcaabc";
        String s2 = ".*a*aa*.*b*.c*.*a*";
        System.out.println(rm.isMatch(s3,s5));
    }



}
