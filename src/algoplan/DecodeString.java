package algoplan;

import java.text.NumberFormat;
import java.util.Stack;

/*
394. Decode String
Medium

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"



Constraints:

    1 <= s.length <= 30
    s consists of lowercase English letters, digits, and square brackets '[]'.
    s is guaranteed to be a valid input.
    All the integers in s are in the range [1, 300].


 */
public class DecodeString {
    public static char LB = '[';
    public static char RB = ']';
    int end = 0;

    public String decodeString(String s) {
        if (s.length() == 1) return s;
        Stack<String> S = new Stack<>();
        int count = 0;// Number of open brackets.
        char[] cArray = s.toCharArray();
        for(int i = 0; i < cArray.length;i++) {
            char currentC = cArray[i];
            S.push(currentC+"");
            if (currentC == LB)  { count++; continue;}
            if (currentC == RB) {
                S.pop();//discard right bracket
                StringBuilder pattern = new StringBuilder();
                while(isAlpha(S.peek().charAt(0))) {
                    pattern.append(S.pop());
                }
                S.pop();//discard LB.
                //int rep = S.pop().charAt(0) - '0';
                int rep = extractNumber(S);
                StringBuilder newStr = new StringBuilder();
                while (rep-- > 0) newStr.append(pattern.toString());
                count--;
                S.push(newStr.toString());
            }

        }
        StringBuilder strB = new StringBuilder();
        while (!S.empty()) {
            strB.append(S.pop());
        }

        return strB.reverse().toString();
    }

    private int extractNumber(Stack<String> S) {
        if (S.empty()) return 1;
        StringBuilder ss = new StringBuilder();
        while (!S.empty() && isDigit(S.peek().charAt(0))) {
            ss.append(S.pop());
        }
        try {
            Number n = NumberFormat.getInstance().parse(ss.reverse().toString());
            return n.intValue();
        } catch (Exception e1) {

        }
        return 1;
    }

    private boolean isAlpha(char c) {
        return ((c >= 'a') && (c <= 'z'));
    }

    private boolean isDigit(char c) {
        return ((c >= '0') && (c <= '9'));
    }

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        System.out.println(ds.decodeString("2[abc]3[cd]ef"));
        System.out.println(ds.decodeString("3[a2[c]]"));
    }
}
