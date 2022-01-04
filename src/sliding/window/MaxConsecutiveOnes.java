package sliding.window;
/*

Max Consecutive Ones III
Medium

Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s.



Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation:
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation:
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.



Note:

    1 <= A.length <= 20000
    0 <= K <= A.length
    A[i] is 0 or 1



*/

public class MaxConsecutiveOnes {

    public int longestOnes(int[] A, int K) {
        int freq = K;
        int i = 0; int maxL = K;int j = 0;
        for(; j < A.length; j++) {
            if (A[j] == 0) freq--;
            while (i < A.length && (freq < 0) ) {
                maxL = Math.max(maxL,(j - i+1));
                if (A[i++] == 0) freq++;
            }

        }
        return maxL;
    }

    public int longestOnesS(int[] A, int K) {
        int i = 0, j;
        for (j = 0; j < A.length; j++) {
            if (A[j] == 0) K--;
            if (K < 0 && A[i++] == 0) K++;
        }
        return j - i;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnes mco = new MaxConsecutiveOnes();
        int[] a = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int[] a1 = {1,1,1,0,0,0,1,1,1,1,0};
        int[] a2 = {1,1,1,0,0,0,1,1,1,1};
        System.out.println(mco.longestOnesS(a2,0));
        //System.out.println(mco.longestOnes(a1,2));
        //System.out.println(mco.longestOnes(a,3));
    }

}
