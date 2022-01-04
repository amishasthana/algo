/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
The idea is very simple:

    last bit of (odd number & even number) is 0.
    when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
    Move m and n rigth a position.

Keep doing step 1,2,3 until m equal to n, use a factor to record the iteration time.

public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if(m == 0){
            return 0;
        }
        int moveFactor = 1;
        while(m != n){
            m >>= 1;
            n >>= 1;
            moveFactor <<= 1;
        }
        return m * moveFactor;
    }
}
 */
public class BitwiseAnd {
    public int rangeBitwiseAnd(int m, int n) {
        int bitWise = m;
        for(int i = m+1; i <= n;i++) {
            bitWise = bitWise & i;
        }
        return bitWise;
    }

    public static void main(String[] args) {
        BitwiseAnd bwa = new BitwiseAnd();
        System.out.println(bwa.rangeBitwiseAnd(0,400));
    }
}
