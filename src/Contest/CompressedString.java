package Contest;

/*
1531. String Compression II

    User Accepted: 112
    User Tried: 999
    Total Accepted: 121
    Total Submissions: 2563
    Difficulty: Hard

Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".

Notice that in this problem, we are not adding '1' after single characters.

Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.

Find the minimum length of the run-length encoded version of s after deleting at most k characters.



Example 1:

Input: s = "aaabcccd", k = 2
Output: 4
Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.

Example 2:

Input: s = "aabbaa", k = 2
Output: 2
Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.

Example 3:

Input: s = "aaaaaaaaaaa", k = 0
Output: 3
Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.



Constraints:

    1 <= s.length <= 100
    0 <= k <= s.length
    s contains only lowercase English letters.

Last executed input: "bbbcbbccaaacabacccaccbabcaacab"
15
 */
public class CompressedString {
    String s = null;

    //Return what it will contribute to length.
    private int getLeastCompression(int pos,int kLeft,char pChar,int nChar) {
        //if (kLeft < 0) return Integer.MAX_VALUE;
        if (pos >= s.length()) return 0;
        char cChar = s.charAt(pos);
        int minLength  = Integer.MAX_VALUE;
        int skipThisChar = Integer.MAX_VALUE;
        int useThisChar = Integer.MAX_VALUE;
        if (kLeft > 0) {
            skipThisChar = getLeastCompression(pos + 1, kLeft - 1, pChar, nChar);
        }
        if (cChar != pChar) {
            useThisChar = getLeastCompression(pos+1,kLeft,cChar,1)+1;
        } else {//same char
            //System.out.println("nchar "+nChar);
            if ( getLength(nChar) == getLength(nChar+1) ) {//no change in length so just use this.
                useThisChar = getLeastCompression(pos+1,kLeft,cChar,nChar+1);
            } else {
                useThisChar =  getLeastCompression(pos+1,kLeft,cChar,nChar+1)+1;
            }
        }
        minLength = Math.min(skipThisChar,useThisChar);
        return minLength;
    }

    private int getLength(int size) {
        if (size <= 1) return 0;
        String s = size+"";
        //System.out.print(" "+   s.length());
        return s.length();
    }


    public int getLengthOfOptimalCompression(String s, int k) {
        int l = s.length();
        this.s = s;
        return getLeastCompression(0,k,'A',0);
    }



    public static void main(String[] args) {
        CompressedString cs = new CompressedString();
        System.out.println(cs.getLengthOfOptimalCompression("aaabcccd",2));
        //System.out.println(cs.getLengthOfOptimalCompression("a",0));
    }
}
