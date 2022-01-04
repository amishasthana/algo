/*
318. Minimum Flips to Make a OR b Equal to c

    User Accepted: 2969
    User Tried: 3227
    Total Accepted: 3040
    Total Submissions: 5432
    Difficulty: Medium

Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.



Example 1:


 */
public class MinFlipToBeEqual {
    public int minFlips(int a, int b, int c) {
        String aa = Integer.toBinaryString(a);
        String bb = Integer.toBinaryString(b);
        String cc = Integer.toBinaryString(c);
        int maxLength = (cc.length() >= aa.length())?(((cc.length() >= bb.length()))?cc.length():bb.length()):(aa.length() >= bb.length())? aa.length():bb.length();
        /*
        //int minLength = (cc.length() < aa.length())?(((cc.length() < bb.length()))?cc.length():bb.length()):
         (aa.length() < bb.length())?aa.length():bb.length();
        int maxLength = (cc.length() >= aa.length())?((cc.length() >= bb.length()))?cc.length():bb.length():aa.length();
        aa = pad(aa,maxLength);
        bb = pad(bb,maxLength);
        cc = pad(cc,maxLength);
        */
        int minF = 0;
        //char[] aArray = aa.toCharArray();
        //char[] bArray = bb.toCharArray();
        //char[] cArray = cc.toCharArray();
        for(int i =0; i < maxLength;i++) {
            if (get(cc,i) == '0') {
                if (get(aa,i) == '1') minF++;
                if (get(bb,i)  == '1') minF++;
            } else {
                if ((get(aa,i) == '0') && (get(bb,i) == '0')) minF++;
            }
        }
        return minF+Math.abs(aa.length()-cc.length())+Math.abs(bb.length()-cc.length());

    }

    private char get(String s,int i) {
        if (i >= s.length()) return '0';
        return s.charAt(i);
    }

    private String pad(String s,int mLength) {
       int nPad = mLength - s.length();
       StringBuilder strB = new StringBuilder();
       while (nPad > 0) {
           strB.append('0');
       }
        strB.append(s);
       return strB.toString();
    }
}
