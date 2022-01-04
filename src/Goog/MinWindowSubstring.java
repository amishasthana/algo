package Goog;

import java.util.HashMap;
import java.util.Map;

/*
76. Minimum Window Substring
Hard

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:

    If there is no such window in S that covers all characters in T, return the empty string "".
    If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

Accepted
362,078
Submissions
1,070,383
 */
public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        if ( ( s == null) || (s.length() == 0) || ( t == null) || (t.length() == 0) ) return "";
        Map<Character,Integer> mT = new HashMap<>();
        for(Character c : t.toCharArray()) {
            mT.put(c,mT.getOrDefault(c,0)+1 );
        }
        int disC = mT.size();
        int minD =  s.length()+1; int i = 0;
        int mStart = 0; int mEnd = 0;
        for( int j = 0; j < s.length(); j++) {
            char cChar = s.charAt(j);
            if (mT.get(cChar) != null) {
                mT.put(cChar, mT.get(cChar)-1);
                if (mT.get(cChar) == 0) disC--;
            }

            while (disC == 0) {
                if (( j-i+1) < minD) {
                    minD = j - i+1;
                    mStart = i; mEnd = j;
                }

                char cCharI = s.charAt(i++);
                if (mT.get(cCharI) != null) {
                    mT.put(cCharI, mT.get(cCharI)+1);
                    if (mT.get(cCharI) > 0) disC++;
                }
            }
        }
        if ( minD > s.length() ) return "";
        return s.substring(mStart,mEnd+1);
    }

    public static void main(String[] args) {
        MinWindowSubstring minWindow = new MinWindowSubstring();
        String S = "ADOBECODEBANC"; String T = "ABC";
        System.out.println(minWindow.minWindow(S,T));
    }


}
