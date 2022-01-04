/*
Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string.
If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters,
then reverse the first k characters and left the other as original.

Example:

Input: s = "abcdefg", k = 2
Output: "bacdfeg"

Restrictions:

    The string consists of lower English letters only.
    Length of the given string and k will in the range [1, 10000]

 */
public class ReverseStringOne {
    public String reverseStr(String s, int k) {
        if ((s == null) || (s.length() <= 1) || (k <= 1)) return s;
        boolean reverse = true;
        int i = 0;
        char[] c = s.toCharArray();
        while (i < s.length()) {
            if (reverse) {
                int start = i;
                int rev = k;
                if ((s.length() - i ) < k) rev = (s.length() - i );//Less available to reverse.
                for (int j = 0; j < rev / 2; j++) {// i = 4, 8
                    char temp = c[j + start];
                    c[j + start] = c[start + rev - j - 1];
                    c[start + rev - j-1] = temp;
                }
            }
            i = i + k;//Pointed to the one after end.
            reverse = !reverse;
        }
        return new String(c);

    }

    public static void main(String[] args) {
        String s1 = "abcdefg";
        ReverseStringOne rsv = new ReverseStringOne();
        System.out.println(rsv.reverseStr(s1,2));
    }
}
