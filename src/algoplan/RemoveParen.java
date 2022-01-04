package algoplan;
/*
301. Remove Invalid Parentheses
Hard

Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return all the possible results. You may return the answer in any order.



Example 1:

Input: s = "()())()"
                12121 01
                1010-110
            "())())()"

              10-10-1-2-1-2
            s = "((()())())"
                  0 1 2 34 34 3  121
                  1232321210
Output: ["(())()","()()()"]

Example 2:

Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

Example 3:

Input: s = ")("
Output: [""]



Constraints:

    1 <= s.length <= 25
    s consists of lowercase English letters and parentheses '(' and ')'.
    There will be at most 20 parentheses in s.

Accepted
319,980
Submissions
691
 */

import java.util.*;

public class RemoveParen {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        int len = s.length();
        char[] cArr = s.toCharArray();

        int[] ALPHA = new int[len];
        int left = 0;
        int right = 0;

        for (int i = 0; i < len; i++) {
            char c = cArr[i];
            if (c == '(') {
                 left++;
            } else if (c == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            } else {
                ALPHA[i] = 1;
            }
        }
        findAllC(ans,left,right,s,0,len);
        return ans;
    }
}
