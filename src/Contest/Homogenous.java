package Contest;
/*
iven a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it modulo 109 + 7.

A string is homogenous if all the characters of the string are the same.

A substring is a contiguous sequence of characters within a string.



Example 1:

Input: s = "abbcccaa"
Output: 13
Explanation: The homogenous substrings are listed as below:
"a"   appears 3 times.
"aa"  appears 1 time.
"b"   appears 2 times.
"bb"  appears 1 time.
"c"   appears 3 times.
"cc"  appears 2 times.
"ccc" appears 1 time.
3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.

Example 2:

Input: s = "xy"
Output: 2
Explanation: The homogenous substrings are "x" and "y".

Example 3:

Input: s = "zzzzz"
Output: 15



Constraints:

    1 <= s.length <= 105
    s consists of lowercase letters.

 */
public class Homogenous {
    public int countHomogenous(String s) {
        char[] cA = s.toCharArray();
        long T = 0; long cS = 1; char cChar = cA[0];
        int mod = (int) (1e9+7);
        for(int i = 1;i < cA.length;i++) {
            if (cA[i] == cChar) {
                cS++;
            } else {
                T = (T + ((cS+1)*cS) - ((cS * (cS+1))/2));
                cS = 1; cChar = cA[i];
            }
        }// End of for loop
        T = (T + ((cS+1)*cS) - ((cS * (cS+1))/2));

        T = T%mod;
        return (int)T;
    }

    /*
    5 --> (5 - 5 + 1)
    4  --> (5 - 4 + 1)
    3 --> (5 - 3 + 1)
    2 -- > ( 5 - 2 + 1)
    1 --> ( 5 - 1 + 1)
      (l+1)*(l) - l*l+1/2
     */
    public static void main(String[] args) {
        Homogenous hg = new Homogenous();
        System.out.println(hg.countHomogenous("zzzzz"));
    }
}
