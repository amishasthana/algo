package googleAss;

import java.text.NumberFormat;

/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"

Example 4:

Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"



Constraints:

    1 <= s.length <= 30
    s consists of lowercase English letters, digits, and square brackets '[]'.
    s is guaranteed to be a valid input.
    All the integers in s are in the range [1, 300].


  S --> Current String
  FS --> STACK
  Ssmall --> small
  if R --> pop last one --> N*small --> FS


  Example 3[a2[c]dd]
  SN --> 2,3
     --> c,a
         --> 2[c]
         --> cc --> SF
         SN --> 2
          --> caa
         --> dd -> extracted
         --> caadd
         --> 3,


 */
import java.util.*;
public class DecodeString {

    StringBuilder strB = new StringBuilder();
    int l;
    char[] cA = null;
    int pos = 0;
    int b = 0;
    Stack<Integer> SI = new Stack<>();
    Stack<String> SS = new Stack<>();

    private String extractString() {
        StringBuilder ss = new StringBuilder();
        while (pos < l) {
            Character cc = cA[pos];
            if (Character.isAlphabetic(cc)) {
                ss.append(cc);
                pos++;
            } else {
                break;
            }
        }
        return ss.toString();
    }

    private int extractNumber() {
        StringBuilder ss = new StringBuilder();
        while (pos < l) {
            Character cc = cA[pos];
            if (Character.isDigit(cc)) {
                ss.append(cc);
                pos++;
            } else {
                break;
            }
        }
        try {
            Number n = NumberFormat.getInstance().parse(ss.toString());
            return n.intValue();
        } catch (Exception e1) {

        }
        return 1;
    }

    private String getSR(String ss, int c) {
        StringBuilder strB = new StringBuilder();
        while (c-- > 0) {
            strB.append(ss);
        }
        return strB.toString();
    }

    public String decodeString(String s) {
        cA = s.toCharArray();
        l = cA.length;
        StringBuilder strB = new StringBuilder();
        while (pos < l) {
            char c = cA[pos];
            String sCurr = "";
            if (c == '[') {
                pos++;
                b++;
            } else if (c == ']') {
                b--;pos++;
                int i = SI.pop();
                sCurr = SS.pop();
                sCurr = getSR(sCurr, i);
            } else if (Character.isDigit(c)) {
                SI.push(extractNumber());
                SS.push("");
            } else if (Character.isAlphabetic(c)) {
                sCurr = extractString();
            }
            if (!SI.empty()) {
                sCurr = SS.pop() + sCurr;
                SS.push(sCurr);
            } else {
                strB.append(sCurr);
            }
        }
        while (!SS.empty()) {
            strB.append(SS.pop());
        }
        return strB.toString();
    }

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        String s = "3[a2[c]]";
        System.out.println(ds.decodeString(s));
    }

}



